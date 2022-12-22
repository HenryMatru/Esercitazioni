package it.course.servletexample;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String newPage = "login.jsp";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);

        requestDispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMessage = "Non esiste nessun utente con queste credenziali!";
        RequestDispatcher requestDispatcher = null;

        if (username.equals("user") && password.equals("user")) {
            requestDispatcher = request.getRequestDispatcher("user.jsp");
            request.setAttribute("username", username);
        } else {
            requestDispatcher = request.getRequestDispatcher("login.jsp");
            request.setAttribute("error", errorMessage);
        }

        requestDispatcher.forward(request, response);
    }

}
