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
import java.sql.SQLException;

@WebServlet(name = "ServletUpdateUser", value = "/ServletUpdateUser")
public class ServletUpdateUser extends HttpServlet {

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        User user = userDAO.selectUser(id);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("update_user.jsp");
        request.setAttribute("user", user);

        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        int age = Integer.parseInt(request.getParameter("age"));
        User newUser = new User(name, email, country, age);
        try {
            userDAO.updateUser(newUser);
        } catch (SQLException e) {
            // throw new RuntimeException(e);
            request.setAttribute("errore", e.getMessage());
        }
        requestDispatcher.forward(request, response);
    }

}
