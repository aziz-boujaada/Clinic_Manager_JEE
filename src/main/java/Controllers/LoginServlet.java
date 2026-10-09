package Controllers;

import Helpers.RequestsValidator;
import Models.User;
import Services.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.getRequestDispatcher("/views/auth/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        try {

            String email = RequestsValidator.required(request.getParameter("email"), "email");
            String password = RequestsValidator.required(request.getParameter("password"), "password");

            Optional<User> user = authService.login(email, password);

            if (user.isPresent()) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user.get());
                System.out.println(user.get().getFirstName());
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");

                return;
            }

            request.setAttribute("error", "Email or password incorrect");
            request.getRequestDispatcher("/views/auth/login.jsp")
                    .forward(request, response);

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "something went wrong Please try again");
            request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
        }
    }
}
