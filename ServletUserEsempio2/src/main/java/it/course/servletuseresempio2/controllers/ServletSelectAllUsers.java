package it.course.servletuseresempio2.controllers;

import it.course.servletuseresempio2.dao.UserDAO;
import it.course.servletuseresempio2.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletSelectAllUsers", value = "/ServletSelectAllUsers")
public class ServletSelectAllUsers extends HttpServlet {

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("users.jsp");
        request.setAttribute("lista_user", userDAO.selectAllUsers());
        requestDispatcher.forward(request, response);
    }

}
