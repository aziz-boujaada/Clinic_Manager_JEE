package Services;

import Enums.AbsenceType;
import Models.Absence;
import Models.Doctor;
import Repositories.ipml.AbsenceRepoImpl;

import java.time.LocalDateTime;

public class AbsenceService {

    private final AbsenceRepoImpl absenceRepo = new AbsenceRepoImpl();
    public Absence createAbsence(AbsenceType absenceType , LocalDateTime startDateTime ,LocalDateTime endDateTime  , String reason, Doctor doctor){

       Absence absence = new  Absence(absenceType , startDateTime , endDateTime , reason , doctor);

        if (doctor == null) {
            throw new IllegalArgumentException(
                    "Doctor is null: check the logged-in session."
            );
        }
       return absenceRepo.save(absence);
    }
}
