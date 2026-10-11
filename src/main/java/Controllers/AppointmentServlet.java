package Controllers;

import Enums.AppointmentType;
import Helpers.RequestsValidator;
import Models.AppointmentSlot;
import Models.Patient;
import Models.User;
import Services.AppointmentService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/appointments/*")
public class AppointmentServlet extends HttpServlet {

    private final AppointmentService appointmentService = new AppointmentService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path == null || path.equals("/") || path.equals("/create")) {
            Patient patient = getLoggedPatient(request, response);
            if (patient == null) return;
            request.setAttribute("doctors", appointmentService.findActiveDoctors());

            if (path == null || path.equals("/")) {
                request.setAttribute("appointments", appointmentService.findByPatientId(patient.getId()));
            }

            String doctorIdValue = request.getParameter("doctorId");
            String dateValue = request.getParameter("date");
            if (doctorIdValue != null && dateValue != null) {
                try {
                    long doctorId = RequestsValidator.positiveInt(doctorIdValue, "Doctor Id");
                    LocalDate date = RequestsValidator.parseDate(dateValue, "Appointment date");
                    request.setAttribute("doctorId", doctorId);
                    request.setAttribute("appointmentDate", date);
                    List<AppointmentSlot> slots = appointmentService.findAvailableSlots(doctorId, patient, date);
                    request.setAttribute("availableSlots", slots);
                } catch (IllegalArgumentException | DateTimeParseException e) {
                    request.setAttribute("error", e.getMessage());
                }
            }
            request.getRequestDispatcher("/views/appointments/create.jsp")
                    .forward(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String path = request.getPathInfo();

        if ("/create".equals(path)) {
            createAppointment(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found");
    }

    private void createAppointment(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        Patient patient = getLoggedPatient(request, response);
        if (patient == null) return;
        request.setAttribute("doctors", appointmentService.findActiveDoctors());
        request.setAttribute("doctorId", request.getParameter("doctorId"));

        try {
            long doctorId = RequestsValidator.positiveInt(request.getParameter("doctorId"), "Doctor Id");
            LocalDateTime startDateTime = RequestsValidator.parseDateTime(
                    request.getParameter("slotStart"), "Appointment slot");
            AppointmentType type = RequestsValidator.enumValuesValidate(
                    request.getParameter("type"), AppointmentType.class, "Appointment type");
            String reason = request.getParameter("reason");

            appointmentService.createAppointment(
                    doctorId,
                    patient,
                    startDateTime,
                    type,
                    reason == null || reason.isBlank() ? null : reason.trim()
            );

            response.sendRedirect(request.getContextPath() + "/appointments?success=true");
        } catch (IllegalArgumentException | DateTimeParseException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/appointments/create.jsp")
                    .forward(request, response);
        } catch (PersistenceException e) {
            request.setAttribute("error", "Appointment could not be saved. Please try again.");
            request.getRequestDispatcher("/views/appointments/create.jsp")
                    .forward(request, response);
        }
    }

    private Patient getLoggedPatient(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/views/auth/login.jsp");
            return null;
        }

        User user = (User) session.getAttribute("user");
        if (!(user instanceof Patient patient)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Only patients can create appointments");
            return null;
        }
        return patient;
    }
}
