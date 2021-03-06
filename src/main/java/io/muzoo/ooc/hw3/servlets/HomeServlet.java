package io.muzoo.ooc.hw3.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class HomeServlet extends AbstractRoutableHttpServlet{


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if (securityService.isAuthorized(request)) {
                String username = securityService.getCurrentUsername(request);
                request.setAttribute("username", username);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
                requestDispatcher.include(request, response);
            } else {
                response.sendRedirect("/login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getPattern() {
        return "/index.jsp";
    }
}
