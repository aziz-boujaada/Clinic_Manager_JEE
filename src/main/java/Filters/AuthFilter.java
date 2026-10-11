package Filters;

import Enums.Role;
import Models.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String relativePath = path.substring(contextPath.length());

        if (isPublicRoute(relativePath) || isStaticResource(relativePath)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        User user = (session == null) ? null : (User) session.getAttribute("user");

        if (user == null) {
            httpResponse.sendRedirect(contextPath + "/login");
            return;
        }

        if (!user.isActive()) {
            session.invalidate();
            httpResponse.sendRedirect(contextPath + "/login");
            return;
        }

        if (!isAllowedForRole(relativePath, user.getRole())) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to access this page.");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicRoute(String path) {
        if (path == null || path.isEmpty() || "/".equals(path)) {
            return true;
        }

        return path.equals("/login")
                || path.startsWith("/login")
                || path.equals("/users/create")
                || path.startsWith("/views/auth/")
                || path.equals("/index.jsp")
                || path.equals("/register");
    }

    private boolean isStaticResource(String path) {
        return path.endsWith(".css")
                || path.endsWith(".js")
                || path.endsWith(".png")
                || path.endsWith(".jpg")
                || path.endsWith(".jpeg")
                || path.endsWith(".gif")
                || path.endsWith(".svg")
                || path.endsWith(".ico")
                || path.endsWith(".woff")
                || path.endsWith(".woff2")
                || path.endsWith(".ttf")
                || path.endsWith(".map");
    }

    private boolean isAllowedForRole(String path, Role role) {
        if (path == null || path.isBlank()) {
            return true;
        }

        if (path.equals("/dashboard.jsp") || path.equals("/dashboard") || path.equals("/")) {
            return true;
        }

        if (path.startsWith("/departments")) {
            return role == Role.ADMIN;
        }

        if (path.startsWith("/users") && !path.equals("/users/create")) {
            return role == Role.ADMIN;
        }

        if (path.startsWith("/absences") || path.startsWith("/availabilities")) {
            return role == Role.DOCTOR;
        }

        if (path.startsWith("/specialties")) {
            return role == Role.ADMIN || role == Role.DOCTOR || role == Role.PATIENT;
        }

        if (path.startsWith("/patients") || path.startsWith("/appointments")) {
            return role == Role.PATIENT || role == Role.DOCTOR || role == Role.ADMIN;
        }

        return true;
    }
}
