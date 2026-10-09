package Controllers;

import Services.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setAttribute("users", authService.findAll());
        request.getRequestDispatcher("/views/users/users.jsp").forward(request, response);
    }
}