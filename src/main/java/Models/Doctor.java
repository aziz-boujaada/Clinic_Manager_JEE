package Models;

import Enums.Role;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "doctors")
public class Doctor extends User {

    @Column(nullable = false, unique = true)
    private String matricule;

    @Column
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(
            mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Availability> availabilities = new HashSet<>();

    @OneToMany(
            mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Absence> absences = new HashSet<>();

    @OneToMany(mappedBy = "doctor")
    private Set<MedicalNote> medicalNotes = new HashSet<>();

    public Doctor() {
    }

    public Doctor(
            String lastName,
            String firstName,
            String email,
            String phone,
            String passwordHash,
            Role role,
            boolean active,
            String matricule,
            String title,
            Specialty specialty
    ) {
        super(
                lastName,
                firstName,
                email,
                phone,
                passwordHash,
                role,
                active
        );

        this.matricule = generateMatricule();
        this.title = title;
        this.specialty = specialty;
    }

    public static int counter = 0;

    public String generateMatricule() {
        UUID matricule = UUID.randomUUID();

        return "DOC-"
                + matricule.toString().substring(0, 4)
                + "-"
                + String.format("%04d", counter++);
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Set<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public Set<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(Set<Absence> absences) {
        this.absences = absences;
    }

    public Set<MedicalNote> getMedicalNotes() {
        return medicalNotes;
    }

    public void setMedicalNotes(Set<MedicalNote> medicalNotes) {
        this.medicalNotes = medicalNotes;
    }
}