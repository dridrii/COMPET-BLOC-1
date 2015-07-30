package com.cptbloc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cptbloc.beans.Juge;
import com.cptbloc.dao.DAOFactory;
import com.cptbloc.dao.JugeDAO;
import com.cptbloc.forms.CreationJugeForm;

@SuppressWarnings( "serial" )
@WebServlet( "/Log-in" )
public class LoginJuge extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";

    public static final String ATT_JUGE         = "juge";
    public static final String ATT_FORM         = "form";

    public static final String VUE_SUCCESS      = "/WEB-INF/Resume-Nv-Juge.jsp";
    public static final String VUE_FORM         = "/WEB-INF/Log-in.jsp";

    private JugeDAO            jugeDAO;

    public void init() throws ServletException {
        /* Récupérations d'une instance de notre dao juge */
        this.jugeDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getJugeDAO();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        CreationJugeForm form = new CreationJugeForm( jugeDAO );

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
