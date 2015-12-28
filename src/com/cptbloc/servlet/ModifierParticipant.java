package com.cptbloc.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cptbloc.beans.Participant;
import com.cptbloc.dao.DAOException;
import com.cptbloc.dao.DAOFactory;
import com.cptbloc.dao.ParticipantDAO;
import com.cptbloc.forms.ModificationParticipantForm;

@SuppressWarnings( "serial" )
@WebServlet( "/JUGE/ModifierParticipant" )
public class ModifierParticipant extends HttpServlet {
    public static final String CONF_DAO_FACTORY     = "daofactory";

    public static final String ATT_BLOC             = "participant";
    public static final String ATT_FORM             = "form";

    public static final String VUE_SUCCESS          = "/JUGE/Resume-Nv-Bloc.jsp";
    public static final String VUE_FORM             = "/JUGE/MDF-participant.jsp";

    public static final String PARAM_ID_PARTICIPANT = "idParticipant";

    private ParticipantDAO     participantDAO;

    public void init() throws ServletException {
        /* Récupérations d'une instance de notre dao bloc */
        this.participantDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getParticipantDAO();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        /* Récupération du paramètre */

        String idParticipant = getValeurParametre( request, PARAM_ID_PARTICIPANT );

        Long id = Long.parseLong( idParticipant );

        if ( id != null ) {
            try {
                participantDAO.trouverDossard( idParticipant );

            } catch ( DAOException e ) {
                e.printStackTrace();
            }

        }

        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        CreationBlocForm form = new CreationBlocForm( participantDAO );

        Participant participant = form.creerBloc( request );

        request.setAttribute( ATT_BLOC, participant );
        request.setAttribute( ATT_FORM, form );

        if ( form.getErreurs().isEmpty() ) {
            this.getServletContext().getRequestDispatcher( VUE_SUCCESS ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }

    }

    /*
     * 
     * Méthode utilitaire qui retourne null si un paramètre est vide, et son
     * 
     * contenu sinon.
     * 
     */

    private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {

        String valeur = request.getParameter( nomChamp );

        if ( valeur == null || valeur.trim().length() == 0 ) {

            return null;

        } else {

            return valeur;

        }

    }
}
