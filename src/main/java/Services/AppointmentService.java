package Services;

import Enums.AppointmentStatus;
import Enums.AppointmentType;
import Enums.AvailabilityStatus;
import Models.Absence;
import Models.Appointment;
import Models.AppointmentSlot;
import Models.Availability;
import Models.Doctor;
import Models.Patient;
import Repositories.ipml.AbsenceRepoImpl;
import Repositories.ipml.AppointmentRepoImpl;
import Repositories.ipml.AvailabilityRepoImplement;
import Repositories.ipml.DoctorRepoImpl;
import Repositories.ipml.UserRepoImplement;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private static final int APPOINTMENT_DURATION_MINUTES = 30;
    private static final int BUFFER_MINUTES = 5;
    private static final int SLOT_INTERVAL_MINUTES = APPOINTMENT_DURATION_MINUTES + BUFFER_MINUTES;
    private static final int LEAD_TIME_HOURS = 2;
    private static final LocalTime LUNCH_START = LocalTime.of(12, 0);
    private static final LocalTime LUNCH_END = LocalTime.of(13, 0);

    private final AppointmentRepoImpl appointmentRepo = new AppointmentRepoImpl();
    private final AvailabilityRepoImplement availabilityRepo = new AvailabilityRepoImplement();
    private final AbsenceRepoImpl absenceRepo = new AbsenceRepoImpl();
    private final DoctorRepoImpl doctorRepo = new DoctorRepoImpl();
    private final UserRepoImplement userRepo = new UserRepoImplement();

    public List<Appointment> findByPatientId(long patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }

    public List<Doctor> findActiveDoctors() {
        return doctorRepo.findAllActive();
    }

    public List<AppointmentSlot> findAvailableSlots(long doctorId, Patient patient, LocalDate date) {
        Patient savedPatient = findPatient(patient);
        Doctor doctor = findDoctor(doctorId);
        validateActiveDoctorAndPatient(doctor, savedPatient);
        validateDate(date);

        List<Availability> availabilities = availabilityRepo.findAll();
        List<Absence> absences = absenceRepo.findAll();
        List<Appointment> doctorAppointments = appointmentRepo.findByDoctorId(doctorId);
        List<Appointment> patientAppointments = appointmentRepo.findByPatientId(savedPatient.getId());
        List<AppointmentSlot> availableSlots = new ArrayList<>();

        if (hasPatientAppointmentOnDate(patientAppointments, date)) {
            return availableSlots;
        }

        LocalDateTime earliestStart = LocalDateTime.now().plusHours(LEAD_TIME_HOURS);

        for (Availability availability : availabilities) {
            if (!isAvailabilityForDate(availability, doctorId, date)) continue;

            LocalDateTime slotStart = date.atTime(availability.getStartTime());
            LocalDateTime availabilityEnd = date.atTime(availability.getEndTime());
            while (!slotStart.plusMinutes(APPOINTMENT_DURATION_MINUTES).isAfter(availabilityEnd)) {
                LocalDateTime slotEnd = slotStart.plusMinutes(APPOINTMENT_DURATION_MINUTES);
                if (!slotStart.isBefore(earliestStart)
                        && isSlotAllowed(doctorId, slotStart, slotEnd, absences,
                        doctorAppointments, patientAppointments)) {
                    availableSlots.add(new AppointmentSlot(slotStart, slotEnd));
                }
                slotStart = slotStart.plusMinutes(SLOT_INTERVAL_MINUTES);
            }
        }

        return availableSlots;
    }

    public Appointment createAppointment(
            long doctorId,
            Patient patient,
            LocalDateTime startDateTime,
            AppointmentType type,
            String reason
    ) {
        Patient savedPatient = findPatient(patient);
        Doctor doctor = findDoctor(doctorId);
        validateActiveDoctorAndPatient(doctor, savedPatient);

        if (startDateTime == null) {
            throw new IllegalArgumentException("Appointment start time is required");
        }
        if (startDateTime.isBefore(LocalDateTime.now().plusHours(LEAD_TIME_HOURS))) {
            throw new IllegalArgumentException("Appointment must be booked at least 2 hours in advance");
        }

        LocalDate date = startDateTime.toLocalDate();
        validateDate(date);
        LocalDateTime endDateTime = startDateTime.plusMinutes(APPOINTMENT_DURATION_MINUTES);

        List<Availability> availabilities = availabilityRepo.findAll();
        boolean validSlotStart = availabilities.stream().anyMatch(availability ->
                isAvailabilityForDate(availability, doctorId, date)
                        && !startDateTime.toLocalTime().isBefore(availability.getStartTime())
                        && !endDateTime.toLocalTime().isAfter(availability.getEndTime())
                        && ChronoUnit.MINUTES.between(
                        date.atTime(availability.getStartTime()), startDateTime) % SLOT_INTERVAL_MINUTES == 0
        );
        if (!validSlotStart) {
            throw new IllegalArgumentException("Appointment is not a generated slot in the doctor's active availability");
        }

        List<Absence> absences = absenceRepo.findAll();
        List<Appointment> doctorAppointments = appointmentRepo.findByDoctorId(doctorId);
        List<Appointment> patientAppointments = appointmentRepo.findByPatientId(savedPatient.getId());
        if (hasPatientAppointmentOnDate(patientAppointments, date)) {
            throw new IllegalArgumentException("You can book only one appointment per day");
        }

        if (!isSlotAllowed(doctorId, startDateTime, endDateTime, absences,
                doctorAppointments, patientAppointments)) {
            throw new IllegalArgumentException("This appointment slot is no longer available");
        }

        Appointment appointment = new Appointment(
                startDateTime,
                endDateTime,
                type,
                AppointmentStatus.PLANNED,
                reason,
                LocalDateTime.now(),
                null,
                savedPatient,
                doctor,
                null
        );
        return appointmentRepo.save(appointment);
    }

    private Patient findPatient(Patient patient) {
        if (patient == null || patient.getId() == null) {
            throw new IllegalArgumentException("Patient does not exist");
        }
        return userRepo.findById(patient.getId())
                .filter(Patient.class::isInstance)
                .map(Patient.class::cast)
                .orElseThrow(() -> new IllegalArgumentException("Patient does not exist"));
    }

    private Doctor findDoctor(long doctorId) {
        return doctorRepo.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor does not exist"));
    }

    private void validateActiveDoctorAndPatient(Doctor doctor, Patient patient) {
        if (!doctor.isActive()) throw new IllegalArgumentException("Doctor is not active");
        if (!patient.isActive()) throw new IllegalArgumentException("Patient is not active");
    }

    private void validateDate(LocalDate date) {
        if (date == null) throw new IllegalArgumentException("Appointment date is required");
        if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("Sunday is closed");
        }
    }

    private boolean isAvailabilityForDate(Availability availability, long doctorId, LocalDate date) {
        return availability.getDoctor().getId().equals(doctorId)
                && availability.getStatus() == AvailabilityStatus.ACTIVE
                && availability.getValidFrom() != null
                && availability.getValidTo() != null
                && availability.getStartTime() != null
                && availability.getEndTime() != null
                && availability.getDayOfWeek() == date.getDayOfWeek()
                && !date.isBefore(availability.getValidFrom())
                && !date.isAfter(availability.getValidTo());
    }

    private boolean isSlotAllowed(
            long doctorId,
            LocalDateTime start,
            LocalDateTime end,
            List<Absence> absences,
            List<Appointment> doctorAppointments,
            List<Appointment> patientAppointments
    ) {
        boolean duringLunch = start.toLocalDate().equals(end.toLocalDate())
                && start.toLocalTime().isBefore(LUNCH_END)
                && end.toLocalTime().isAfter(LUNCH_START);
        if (duringLunch) return false;

        boolean overlapsAbsence = absences.stream().anyMatch(absence ->
                absence.getDoctor().getId().equals(doctorId)
                        && start.isBefore(absence.getEndDateTime())
                        && end.isAfter(absence.getStartDateTime())
        );
        if (overlapsAbsence) return false;

        return !hasAppointmentConflict(start, end, doctorAppointments)
                && !hasAppointmentConflict(start, end, patientAppointments);
    }

    private boolean hasAppointmentConflict(
            LocalDateTime start,
            LocalDateTime end,
            List<Appointment> appointments
    ) {
        return appointments.stream().anyMatch(existing ->
                existing.getStatus() != AppointmentStatus.CANCELED
                        && start.isBefore(existing.getEndDateTime().plusMinutes(BUFFER_MINUTES))
                        && end.plusMinutes(BUFFER_MINUTES).isAfter(existing.getStartDateTime())
        );
    }

    private boolean hasPatientAppointmentOnDate(List<Appointment> appointments, LocalDate date) {
        return appointments.stream().anyMatch(existing ->
                existing.getStatus() != AppointmentStatus.CANCELED
                        && existing.getStartDateTime().toLocalDate().equals(date)
        );
    }
}
