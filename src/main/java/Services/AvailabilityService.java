package Services;

import Enums.AvailabilityStatus;
import Models.Availability;
import Models.Doctor;
import Repositories.ipml.AvailabilityRepoImplement;
import Repositories.ipml.DoctorRepoImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class AvailabilityService {
    private final AvailabilityRepoImplement availabilityRepo = new AvailabilityRepoImplement();
    private final DoctorRepoImpl doctorRepo = new DoctorRepoImpl();

    public Availability createAvailability(String day, LocalTime startTime, LocalTime endTime, LocalDate validatFrom, LocalDate validatTo, Doctor doctor) {

        long doctorId = doctor.getId();
        Optional<Doctor> doctorOptional = doctorRepo.findById(doctorId);
        List<Availability> availabilities = availabilityRepo.findAll();

        validateDoctor(doctor);

        validateTimes(startTime, endTime);
        validateDates(validatFrom, validatTo);

        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day);

        if (DayOfWeek.valueOf(day.toUpperCase()) == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("Sunday is closed chose other day");
        }

        existAvailability(availabilities, doctorId, validatFrom, validatTo, startTime, endTime, dayOfWeek);

        Availability availability = new Availability(dayOfWeek, startTime, endTime, AvailabilityStatus.ACTIVE, validatFrom, validatTo, doctor);
        return availabilityRepo.save(availability);
    }

    public void validateDoctor(Doctor doctor) {

        if (doctor == null) {
            throw new IllegalArgumentException("Doctor does not exist");
        }

        if (!doctor.isActive()) {
            throw new IllegalArgumentException("Doctor is not active");
        }
    }

    public void validateTimes(LocalTime startTime, LocalTime endTime) {
        if (!startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("start time must before end time");
        }
    }

    public void validateDates(LocalDate validFrom, LocalDate validTo) {
        if (validFrom.isAfter(validTo)) {
            throw new IllegalArgumentException("valid From tima must before valid To");
        }
    }

    public void existAvailability(
            List<Availability> availabilities,
            long doctorId,
            LocalDate validatFrom,
            LocalDate validatTo,
            LocalTime startTime,
            LocalTime endTime,
            DayOfWeek day

    ) {

        for (Availability existing : availabilities) {
            if (!existing.getDoctor().getId().equals(doctorId)) {
                continue;
            }

            if (existing.getDayOfWeek() != day) {
                continue;
            }

            boolean dateOverlap = !validatFrom.isAfter(existing.getValidTo())
                    && !validatTo.isBefore(existing.getValidFrom());

            boolean timeOverlap = startTime.isBefore(existing.getEndTime())
                    && endTime.isAfter(existing.getStartTime());

            if (timeOverlap && dateOverlap) {
                throw new IllegalArgumentException("doctor already add availability in this period");
            }
        }
    }

}
