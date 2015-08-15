package com.cptbloc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cptbloc.beans.Juge;
import com.cptbloc.forms.ConnexionJugeForm;

@SuppressWarnings( "serial" )
@WebServlet( name = "ConnexionJuge", urlPatterns = "/Sign-in" )
public class ConnexionJuge extends HttpServlet {
    public static final String ATT_JUGE         = "juge";
    public static final String ATT_FORM         = "form";

    public static final String ATT_SESSION_JUGE = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/Sign-in.jsp";
    public static final String VUE_SUCCESS      = "/JUGE/Index-Juge.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnexionJugeForm form = new ConnexionJugeForm();

        /* Traitement de la requête et récupération du bean en résultant */
        Juge juge = form.connecterJuge( request );

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_SESSION_JUGE, juge );
        } else {
            session.setAttribute( ATT_SESSION_JUGE, null );
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_JUGE, juge );

        if ( form.getErreurs().isEmpty() ) {
            this.getServletContext().getRequestDispatcher( VUE_SUCCESS ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }

    }
}