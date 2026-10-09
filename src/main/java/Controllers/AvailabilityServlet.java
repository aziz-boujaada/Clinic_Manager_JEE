package Controllers;

import Helpers.RequestsValidator;
import Models.Doctor;
import Models.User;
import Services.AvailabilityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/availabilities/*")
public class AvailabilityServlet extends HttpServlet {

        AvailabilityService availabilityService = new AvailabilityService();


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String path = request.getPathInfo();

        if (path.equals("/create")) {
            request.getRequestDispatcher("/views/avialability/create.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String path = request.getPathInfo();

        if ("/create".equals(path)) {
            createAvailability(request, response);
            return;
        }

        response.sendError(
                HttpServletResponse.SC_NOT_FOUND,
                "Action not found"
        );
    }

    private void createAvailability(
            HttpServletRequest request ,
            HttpServletResponse response
    )throws ServletException , IOException
    {

        String day = RequestsValidator.required(request.getParameter("day") , "Day");
        LocalTime startTim = RequestsValidator.parseTime(request.getParameter("startTime"), "start time") ;
        LocalTime endTime = RequestsValidator.parseTime(request.getParameter("endTime"), "end time");
        LocalDate validFrom = RequestsValidator.parseDate(request.getParameter("validateFrom"), "Validate from date ");
        LocalDate validTo = RequestsValidator.parseDate(request.getParameter("validateTo"), "Validate To date ");
        Doctor doctor =  getLoggedDoctor(request , response);


        if(doctor == null ) return;

        availabilityService.createAvailability(day , startTim, endTime , validFrom , validTo , doctor);

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


