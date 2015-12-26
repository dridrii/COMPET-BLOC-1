package com.cptbloc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings( "serial" )
@WebServlet( "/JUGE/NouveauBloc1" )
public class NouveauBloc1 extends HttpServlet {
    public static final String VUE_NV_JUGE = "/JUGE/NV-Bloc.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE_NV_JUGE ).forward( request, response );
    }
}
