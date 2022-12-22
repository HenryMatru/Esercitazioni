package it.course.servletstudente.controllers;

import it.course.servletstudente.models.Studente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "StudenteServlet", value = "/StudenteServlet")
public class ServletStudente extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nomeStudente = request.getParameter("nomeStudente");
        String cognomeStudente = request.getParameter("cognomeStudente");
        String scuolaStudente = request.getParameter("scuolaStudente");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("studente.jsp");

        Studente studente = new Studente(nomeStudente, cognomeStudente, scuolaStudente);
        request.setAttribute("studente", studente);

        requestDispatcher.forward(request, response);
    }

}
