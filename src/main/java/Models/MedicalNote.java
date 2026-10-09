package Models;

import Enums.NoteStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_notes")
public class MedicalNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false, length = 10000)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NoteStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", unique = true)
    private Appointment appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    public MedicalNote() {
    }

    public MedicalNote(String diagnosis, String content, LocalDateTime createdAt, NoteStatus status, Appointment appointment, Doctor doctor) {
        this.diagnosis = diagnosis;
        this.content = content;
        this.createdAt = createdAt;
        this.status = status;
        this.appointment = appointment;
        this.doctor = doctor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public NoteStatus getStatus() { return status; }
    public void setStatus(NoteStatus status) { this.status = status; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public void validate() {
        if (diagnosis == null || diagnosis.trim().isEmpty()) {
            throw new IllegalStateException("A diagnosis is required to validate a medical note.");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalStateException("Content is required to validate a medical note.");
        }
        this.status = NoteStatus.VALIDATED;
    }

    public boolean isLocked() {
        return status == NoteStatus.VALIDATED;
    }

}
