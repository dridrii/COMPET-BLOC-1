package com.cptbloc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cptbloc.beans.ResultatBP;
import com.cptbloc.dao.DAOFactory;
import com.cptbloc.dao.ParticipantDAO;
import com.cptbloc.dao.BlocDAO;
import com.cptbloc.forms.NouveauResultatForm;
import com.cptbloc.dao.ResultatBPDAO;

@SuppressWarnings( "serial" )
@WebServlet( "/JUGE/NouveauResultat" )
public class NouveauResultat extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";

    public static final String ATT_PARTICIPANT  = "resultatBP";
    public static final String ATT_FORM         = "form";

    public static final String VUE_SUCCESS      = "/JUGE/Resume-Nv-Participant.jsp";
    public static final String VUE_FORM         = "/JUGE/Nv-Resultat.jsp";

    private ParticipantDAO     participantDAO;
    private BlocDAO            blocDAO;
    private ResultatBPDAO      resultatBPDAO;

    public void init() throws ServletException {
        /* Récupérations d'une instance de notre dao participant */
        this.participantDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getParticipantDAO();
        this.blocDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getBlocDAO();
        this.resultatBPDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getResultatBPDAO();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        NouveauResultatForm form = new NouveauResultatForm( participantDAO, blocDAO, resultatBPDAO );

        ResultatBP resultatBP = form.NouveauResultat( request );

        request.setAttribute( ATT_PARTICIPANT, resultatBP );
        request.setAttribute( ATT_FORM, form );

        if ( form.getErreurs().isEmpty() ) {

            this.getServletContext().getRequestDispatcher( VUE_SUCCESS ).forward( request, response );
        } else {
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }

    }
}
