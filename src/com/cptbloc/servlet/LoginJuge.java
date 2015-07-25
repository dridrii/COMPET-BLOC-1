package com.cptbloc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cptbloc.beans.Juge;
import com.cptbloc.forms.CreationJugeForm;

@SuppressWarnings( "serial" )
public class LoginJuge extends HttpServlet {
    public static final String ATT_JUGE    = "juge";
    public static final String ATT_FORM    = "form";

    public static final String VUE_SUCCESS = "/WEB-INF/Resume-Nv-Juge.jsp";
    public static final String VUE_FORM    = "/WEB-INF/Log-in.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        CreationJugeForm form = new CreationJugeForm();

        Juge juge = form.creerJuge( request );

        request.setAttribute( ATT_JUGE, juge );
        request.setAttribute( ATT_FORM, form );

        if ( form.getErreurs().isEmpty() ) {
            this.getServletContext().getRequestDispatcher( VUE_SUCCESS ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }

    }

}
