package com.cptbloc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cptbloc.beans.Participant;
import com.cptbloc.dao.DAOFactory;
import com.cptbloc.dao.ParticipantDAO;
import com.cptbloc.forms.CreationParticipantForm;

@SuppressWarnings( "serial" )
@WebServlet( "/JUGE/NouveauParticipant" )
public class NouveauParticipant extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";

    public static final String ATT_PARTICIPANT  = "participant";
    public static final String ATT_FORM         = "form";

    public static final String VUE_SUCCESS      = "/JUGE/Resume-Nv-Participant.jsp";
    public static final String VUE_FORM         = "/JUGE/Nv-Participant.jsp";

    private ParticipantDAO     participantDAO;

    public void init() throws ServletException {
        /* Récupérations d'une instance de notre dao participant */
        this.participantDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getParticipantDAO();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        CreationParticipantForm form = new CreationParticipantForm( participantDAO );

        Participant participant = creerParticipant( request );

        request.setAttribute( ATT_PARTICIPANT, participant );
        request.setAttribute( ATT_FORM, form );

        if ( form.getErreurs().isEmpty() ) {
            this.getServletContext().getRequestDispatcher( VUE_SUCCESS ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }

    }
}
