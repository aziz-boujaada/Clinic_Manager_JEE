package Controllers;

import Helpers.RequestsValidator;
import Models.Department;
import Services.DepartmentService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/departments/*")
public class DepartmentServlet extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String path = request.getPathInfo();

        if (path == null || path.equals("/")) {
            List<Department> departments = departmentService.findAll();

            request.setAttribute("departments", departments);

            request.getRequestDispatcher("/views/departments/departments.jsp")
                .forward(request, response);

            return;
        }

        if (path.equals("/new")) {
            request.getRequestDispatcher("/views/departments/create.jsp")
                .forward(request, response);

            return;
        }

        if (path.equals("/edit")) {
            long id = RequestsValidator.positiveInt(request.getParameter("id"), "Department Id");

            request.setAttribute("department", departmentService.findByID(id));

            request.getRequestDispatcher("/views/departments/create.jsp")
                .forward(request, response);

            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String action = RequestsValidator.required(request.getParameter("action"), "action");

        switch (action.toLowerCase()) {
            case "create" -> createDepartment(request, response);
            case "update" -> updateDepartment(request, response);
            case "delete" -> deleteDepartment(request, response);
            default -> response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Unknown action" + " " + action
            );
        }

    }

    private void createDepartment(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String name = RequestsValidator.required(request.getParameter("name"), "Department name");
        String description = request.getParameter("description");

        departmentService.create(name, description);
        response.sendRedirect(request.getContextPath() + "/departments");
    }

    // update
    private void updateDepartment(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String name = RequestsValidator.required(request.getParameter("name"), "Department name");
        String description = request.getParameter("description");
        long id = RequestsValidator.positiveInt(request.getParameter("department_id"), "Department Id");
        departmentService.update(id, name, description);
        response.sendRedirect(request.getContextPath() + "/departments");

    }

    // delete
    private void deleteDepartment(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        long id = RequestsValidator.positiveInt(request.getParameter("department_id"), "Department Id");
        departmentService.delete(id);
        response.sendRedirect(request.getContextPath() + "/departments");
    }
}
