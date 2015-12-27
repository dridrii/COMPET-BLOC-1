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


import com.cptbloc.beans.Bloc;
import com.cptbloc.dao.DAOFactory;
import com.cptbloc.dao.BlocDAO;
import com.cptbloc.forms.CreationBlocForm;

@SuppressWarnings( "serial" )
@WebServlet("/JUGE/NouveauBloc")
public class NouveauBloc extends HttpServlet {
	public static final String CONF_DAO_FACTORY = "daofactory";

	public static final String ATT_BLOC = "bloc";
	public static final String ATT_FORM = "form";
	public static final String SESSION_BLOCS = "blocs";

	public static final String VUE_SUCCESS = "/JUGE/Resume-Nv-Bloc.jsp";
	public static final String VUE_FORM = "/JUGE/NV-Bloc.jsp";

	private BlocDAO blocDAO;

	public void init() throws ServletException {
		/* Récupérations d'une instance de notre dao bloc */
		this.blocDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getBlocDAO();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CreationBlocForm form = new CreationBlocForm(blocDAO);

		Bloc bloc = form.creerBloc(request);

		request.setAttribute(ATT_BLOC, bloc);
		request.setAttribute(ATT_FORM, form);

		if (form.getErreurs().isEmpty()) {
		    
	          /* Récupération de la map bloc dans la session */
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            Map<Long, Bloc> blocs= (HashMap<Long, Bloc>) session.getAttribute(SESSION_BLOCS);

            /* Si aucune map n'existe, alors initialisation d'une nouvele map */
            if (blocs == null) {
                blocs= new HashMap<Long, Bloc>();
            }

            /* Ajout de Bloc courant dans la map */

            blocs.put(bloc.getIdBloc(), bloc);
            /* Enregistrement de la map en session */

            session.setAttribute(SESSION_BLOCS, blocs);
		    
			this.getServletContext().getRequestDispatcher(VUE_SUCCESS).forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}

	}
}
