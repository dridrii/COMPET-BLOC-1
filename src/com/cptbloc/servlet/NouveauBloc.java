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
import com.cptbloc.dao.DAOFactory;
import com.cptbloc.dao.ParticipantDAO;
import com.cptbloc.dao.CategorieDAO;
import com.cptbloc.forms.CreationParticipantForm;

@SuppressWarnings("serial")
@WebServlet("/JUGE/NouveauBloc")
public class NouveauBloc extends HttpServlet {
	public static final String CONF_DAO_FACTORY = "daofactory";

	public static final String ATT_PARTICIPANT = "participant";
	public static final String ATT_FORM = "form";
	public static final String SESSION_PARTICIPANTS = "participants";

	public static final String VUE_SUCCESS = "/JUGE/Resume-Nv-Participant.jsp";
	public static final String VUE_FORM = "/JUGE/Nv-Participant.jsp";

	private ParticipantDAO participantDAO;
	private CategorieDAO categorieDAO;

	public void init() throws ServletException {
		/* Récupérations d'une instance de notre dao participant */
		this.participantDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getParticipantDAO();
		this.categorieDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCategorieDAO();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CreationParticipantForm form = new CreationParticipantForm(participantDAO, categorieDAO);

		Participant participant = form.creerParticipant(request);

		request.setAttribute(ATT_PARTICIPANT, participant);
		request.setAttribute(ATT_FORM, form);

		if (form.getErreurs().isEmpty()) {
		    
	          /* Récupération de la map client dans la session */
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            Map<Long, Participant> participants = (HashMap<Long, Participant>) session.getAttribute(SESSION_PARTICIPANTS);

            /* Si aucune map n'existe, alors initialisation d'une nouvele map */
            if (participants == null) {
                participants = new HashMap<Long, Participant>();
            }

            /* Ajout de Juge courant dans la map */

            participants.put(participant.getidParticipant(), participant);
            /* Enregistrement de la map en session */

            session.setAttribute(SESSION_PARTICIPANTS, participants);
		    
			this.getServletContext().getRequestDispatcher(VUE_SUCCESS).forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}

	}
}
