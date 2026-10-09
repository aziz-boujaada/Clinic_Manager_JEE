package Models;

import Enums.Role;
import Enums.BloodGroup;
import Enums.Gender;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient extends User {
    @Column(unique = true)
    private String cin;

    @Column
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    public Patient() {
    }

    public Patient(String lastName, String firstName, String email, String phone, String passwordHash, Role role, boolean active, String cin, LocalDate birthDate, Gender gender, String address, BloodGroup bloodGroup) {
        super(lastName, firstName, email, phone, passwordHash, role, active);
        this.cin = cin;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public BloodGroup getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(BloodGroup bloodGroup) { this.bloodGroup = bloodGroup; }

}
