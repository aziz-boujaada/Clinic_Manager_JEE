package Models;

import Enums.AppointmentStatus;
import Enums.AppointmentType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @Column
    private String reason;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime canceledAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private MedicalNote medicalNote;

    public Appointment() {
    }

    public Appointment(LocalDateTime startDateTime, LocalDateTime endDateTime, AppointmentType type, AppointmentStatus status, String reason, LocalDateTime createdAt, LocalDateTime canceledAt, Patient patient, Doctor doctor, MedicalNote medicalNote) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.type = type;
        this.status = status;
        this.reason = reason;
        this.createdAt = createdAt;
        this.canceledAt = canceledAt;
        this.patient = patient;
        this.doctor = doctor;
        this.medicalNote = medicalNote;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getStartDateTime() { return startDateTime; }
    public void setStartDateTime(LocalDateTime startDateTime) { this.startDateTime = startDateTime; }

    public LocalDateTime getEndDateTime() { return endDateTime; }
    public void setEndDateTime(LocalDateTime endDateTime) { this.endDateTime = endDateTime; }

    public AppointmentType getType() { return type; }
    public void setType(AppointmentType type) { this.type = type; }

    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getCanceledAt() { return canceledAt; }
    public void setCanceledAt(LocalDateTime canceledAt) { this.canceledAt = canceledAt; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public MedicalNote getMedicalNote() { return medicalNote; }
    public void setMedicalNote(MedicalNote medicalNote) { this.medicalNote = medicalNote; }

    public boolean canBeCanceled(LocalDateTime now) {
        return status == AppointmentStatus.PLANNED && canceledAt == null && now != null && now.isBefore(startDateTime);
    }

    public boolean overlaps(Appointment other) {
        return other != null
                && startDateTime != null && endDateTime != null
                && other.startDateTime != null && other.endDateTime != null
                && startDateTime.isBefore(other.endDateTime)
                && other.startDateTime.isBefore(endDateTime);
    }

}
