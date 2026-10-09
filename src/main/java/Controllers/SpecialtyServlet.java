package Controllers;

import Helpers.RequestsValidator;
import Models.Department;
import Models.Specialty;
import Services.DepartmentService;
import Services.SpecialtyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/specialties", "/specialties/*"})
public class SpecialtyServlet extends HttpServlet {
    private final SpecialtyService specialtyService = new SpecialtyService();
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String path = request.getPathInfo();

        if (path == null || path.equals("/")) {
            System.out.println("path test "  + path);
            listSpecialties(request, response);

            return;
        }

        if (path.equals("/create")) {
            showCreateForm(request, response);
            return;
        }

        if (path.equals("/editSpeciality")) {
            showEditForm(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
    // Specialties  List

    private void listSpecialties(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        List<Specialty> specialties = specialtyService.findAll();

        request.setAttribute("specialties", specialties);

        request.getRequestDispatcher("views/specialties/specialties.jsp").forward(request, response);
    }

    // show create form
    private void showCreateForm(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        List<Department> departments = departmentService.findAll();

        request.setAttribute("departments", departments);

        request.getRequestDispatcher(
                "/views/specialties/create.jsp"
        ).forward(request, response);
    }
    // show edit from

        private void showEditForm(
            HttpServletRequest request,
            HttpServletResponse response
        ) throws ServletException, IOException {

        long id = RequestsValidator.positiveInt(request.getParameter("id"), "Speciality Id");
        Specialty specialty = specialtyService.findById(id);
        List<Department> departments = departmentService.findAll();

        request.setAttribute("specialty", specialty);
        request.setAttribute("departments", departments);

        request.getRequestDispatcher(
            "/views/specialties/editSpeciality.jsp"
        ).forward(request, response);
        }
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String action = RequestsValidator.required(request.getParameter("action"), "action");

        switch (action.toLowerCase()) {
            case "create" -> createSpeciality(request, response);
            case "update" -> updateSpeciality(request, response);
            case "delete" -> deleteSpeciality(request, response);
            default -> response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Unknown action" + " " + action
            );
        }

    }

    private void createSpeciality(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String name = RequestsValidator.required(request.getParameter("name"), "Speciality name");
        String description = request.getParameter("description");
        long department_id = RequestsValidator.positiveInt(request.getParameter("department_id"), "Department Id");
        specialtyService.create(name, description,department_id);
        response.sendRedirect(request.getContextPath() + "/specialties");
    }

    // update
    private void updateSpeciality(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String name = RequestsValidator.required(request.getParameter("name"), "Speciality name");
        String description = request.getParameter("description");
        long id = RequestsValidator.positiveInt(request.getParameter("specialty_id"), "Speciality Id");
        long department_id = RequestsValidator.positiveInt(request.getParameter("department_id"), "Department Id");
        specialtyService.update(id, name, description,department_id);
        response.sendRedirect(request.getContextPath() + "/specialties");

    }

    // delete
    private void deleteSpeciality(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        long id = RequestsValidator.positiveInt(
            request.getParameter("speciality_id") != null
                ? request.getParameter("speciality_id")
                : request.getParameter("id"),
            "Speciality Id"
        );
        specialtyService.delete(id);
        response.sendRedirect(request.getContextPath() + "/specialties");
    }


}
