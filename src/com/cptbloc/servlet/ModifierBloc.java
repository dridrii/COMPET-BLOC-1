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
import com.cptbloc.dao.DAOException;
import com.cptbloc.dao.DAOFactory;
import com.cptbloc.dao.BlocDAO;
import com.cptbloc.forms.ModificationBlocForm;


@SuppressWarnings( { "serial", "unused" } )
@WebServlet( "/JUGE/ModifierBloc" )
public class ModifierBloc extends HttpServlet {
    public static final String CONF_DAO_FACTORY     = "daofactory";

    public static final String ATT_BLOC             = "bloc";
    public static final String ATT_FORM             = "form";

    public static final String VUE_SUCCESS          = "/JUGE/ListeBloc.jsp";
    public static final String VUE_FORM             = "/JUGE/MDF-Bloc.jsp";

    public static final String PARAM_ID_BLOC = "IdBloc";
    public static final String SESSION_BLOCS = "blocs";

    private BlocDAO     blocDAO;
  

    public void init() throws ServletException {
        /* Récupérations d'une instance de notre dao bloc */
        this.blocDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getBlocDAO();
  
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        /* Récupération du paramètre */

        String idBlocTX = getValeurParametre( request, PARAM_ID_BLOC );

        String str1 = idBlocTX;
        Long idBloc = Long.parseLong(str1);

        Bloc bloc= new Bloc();
        bloc = blocDAO.trouverId( idBloc );

        request.setAttribute( "bloc", bloc );
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        ModificationBlocForm form = new ModificationBlocForm( blocDAO);

        Bloc bloc = form.ModifierBloc( request );

        request.setAttribute( ATT_BLOC, bloc );
        request.setAttribute( ATT_FORM, form );
        
        if ( form.getErreurs().isEmpty() ) {
        
        /* Récupération de la map bloc dans la session */
      HttpSession session = request.getSession();
      @SuppressWarnings("unchecked")
      Map<Long, Bloc> blocs = (HashMap<Long, Bloc>) session.getAttribute(SESSION_BLOCS);


      /* Modification du bloc modifié dans la map */

      blocs.replace( bloc.getIdBloc(), bloc );
      
      /* Enregistrement de la map en session */

      session.setAttribute(SESSION_BLOCS, blocs);
        

        
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
