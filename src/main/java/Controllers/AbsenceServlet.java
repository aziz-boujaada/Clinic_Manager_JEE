package Controllers;

import Enums.AbsenceType;
import Helpers.RequestsValidator;
import Models.Doctor;
import Models.User;
import Services.AbsenceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;

@WebServlet("/absences/*")
public class AbsenceServlet extends HttpServlet {

    private final AbsenceService absenceService = new AbsenceService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String path = request.getPathInfo();

        if (path.equals("/create")) {
            request.getRequestDispatcher("/views/absences/create.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String path = request.getPathInfo();

        if (path.equals("/create")) {
            createAbsence(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND, "page not found ");
    }

    private void createAbsence(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

     AbsenceType absenceType  = RequestsValidator.enumValuesValidate(request.getParameter("absenceType"), AbsenceType.class , "Absence Type");
     LocalDateTime startDateTime = RequestsValidator.parseDateTime(request.getParameter("startDateTime"), "start Date Time");
     LocalDateTime endDateTime = RequestsValidator.parseDateTime(request.getParameter("startEndTime"), "End Date Time");
     String reason = RequestsValidator.required(request.getParameter("reason"), "reason");
     Doctor doctor = getLoggedDoctor(request , response);

     absenceService.createAbsence(absenceType , startDateTime , endDateTime , reason , doctor);
    }

    // get logged doctor
    private Doctor getLoggedDoctor(HttpServletRequest request , HttpServletResponse response)throws ServletException , IOException{
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect(request.getContextPath() + "/views/auth/login.jsp");
            return null ;
        }

        User user = (User) session.getAttribute("user");

        if(!(user instanceof  Doctor doctor)){
            response.sendError(HttpServletResponse.SC_FORBIDDEN , "only doctors can create availability");
            return null;
        }
        return doctor  ;
    }
}
