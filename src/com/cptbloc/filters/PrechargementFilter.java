package com.cptbloc.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cptbloc.beans.Juge;
import com.cptbloc.dao.DAOFactory;
import com.cptbloc.dao.JugeDAO;

public class PrechargementFilter implements Filter {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_SESSION_JUGE = "juge";

    private JugeDAO            jugeDAO;

    public void init( FilterConfig config ) throws ServletException {
        /* Récupération d'une instance de nos DAO Client et Commande */
        this.jugeDAO = ( (DAOFactory) config.getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getJugeDAO();

    }

    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
        /* Cast de l'objet request */
        HttpServletRequest request = (HttpServletRequest) req;

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /*
         * Si la map des juges n'existe pas en session, alors l'utilisateur se
         * connecte pour la première fois et nous devons précharger en session
         * les infos contenues dans la BDD.
         */
        if ( session.getAttribute( ATT_SESSION_JUGE ) == null ) {
            /*
             * Récupération de la liste des clients existants, et enregistrement
             * en session
             */
            List<Juge> listeJuge = jugeDAO.lister();
            Map<Long, Juge> mapJuge = new HashMap<Long, Juge>();
            for ( Juge juge : listeJuge ) {
                mapJuge.put( juge.getId(), juge );
            }
            session.setAttribute( ATT_SESSION_JUGE, mapJuge );
        }
        chain.doFilter( request, res );
    }

    public void destroy() {
    }
}