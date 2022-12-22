package it.course.servletexample;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "NameServlet", value = "/NameServlet")
public class NameServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = "Enrico";
        String newPage = "name.jsp";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
        request.setAttribute("name", name);

        requestDispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String newPage = "name.jsp";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
        request.setAttribute("name", name);

        requestDispatcher.forward(request, response);
    }

}
