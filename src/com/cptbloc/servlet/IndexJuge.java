package com.cptbloc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings( "serial" )
@WebServlet( "/JUGE/Index-Juge" )
public class IndexJuge extends HttpServlet {
    public static final String VUE_INDEX_JUGE = "/JUGE/Index-Juge.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE_INDEX_JUGE ).forward( request, response );
    }
}
