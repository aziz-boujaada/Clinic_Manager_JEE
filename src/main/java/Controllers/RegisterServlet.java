package Controllers;

import Enums.BloodGroup;
import Enums.Gender;
import Enums.Role;
import Helpers.RequestsValidator;
import Models.Department;
import Models.Doctor;
import Models.Patient;
import Models.Specialty;
import Services.AuthService;
import Services.DepartmentService;
import Services.SpecialtyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet("/users/create")
public class RegisterServlet extends HttpServlet {

    private final AuthService authService = new AuthService();
    private final DepartmentService departmentService = new DepartmentService();
    private final SpecialtyService specialtyService = new SpecialtyService();

    private void showRegisterForm(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        List<Specialty> specialties = specialtyService.findAll();

        System.out.println("specialities :" + specialties);
        System.out.println("specialities count :" + specialties.size());

        request.setAttribute("specialties", specialties);

        request.getRequestDispatcher("/views/auth/register.jsp")
                .forward(request, response);
    }

    @Override
    protected  void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )throws ServletException,IOException {
        showRegisterForm(request , response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )throws ServletException , IOException{
        try {
            String firstName = RequestsValidator.required(request.getParameter("firstName"), "First Name");
            String lastName = RequestsValidator.required(request.getParameter("lastName"), "Last Name");
            String email = RequestsValidator.emailsValidate(request.getParameter("email"), "Email");
            String phone = RequestsValidator.phonesValidate(request.getParameter("phone"),"phone");
            String password = RequestsValidator.passwordsValidate(request.getParameter("password"),"password");
            Role  role = RequestsValidator.enumValuesValidate(request.getParameter("role"), Role.class , "role");

            // validate other inputs based on role
            if(role == Role.PATIENT){
               String cin = RequestsValidator.required(request.getParameter("cin"), "CIN");
               LocalDate birthDay = RequestsValidator.birthDate(request.getParameter("birthDay"));
               Gender gender = RequestsValidator.enumValuesValidate(request.getParameter("gender"), Gender.class , "gender");
               String address = RequestsValidator.required(request.getParameter("address"), "address");
               BloodGroup bloodGroup = RequestsValidator.enumValuesValidate(request.getParameter("bloodGroup"), BloodGroup.class , "Blood Group");

               Patient patient = new Patient(lastName , firstName , email , phone , password , role , true , cin , birthDay , gender , address , bloodGroup);
               authService.register(patient);

            }else if(role == Role.DOCTOR){
                String title = RequestsValidator.required(request.getParameter("title") , "Title");
                int specialty_id = RequestsValidator.positiveInt(request.getParameter("specialty_id"), "specialty ID");

                // find speciality
                Specialty specialty = specialtyService.findById(specialty_id);

                Doctor doctor = new Doctor(lastName , firstName , email , phone , password , role , true ,null,title , specialty);
                authService.register(doctor);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
