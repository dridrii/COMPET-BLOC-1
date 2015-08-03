package com.cptbloc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings( "serial" )
@WebServlet( "/LogoutJuge" )
public class LogoutJuge extends HttpServlet {
    public static final String URL_REDIRECTION = "localhost:8080/COMPET-BLOC-1/Index-Public";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        session.invalidate();

        /* Redirection vers la page d'acceuil ! */
        response.sendRedirect( URL_REDIRECTION );

    }
}