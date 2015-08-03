package com.cptbloc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JugeFilter implements Filter {

    public static final String ACCES_PUBLIC     = "/Index";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    @Override
    public void init( FilterConfig arg0 ) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
        // TODO Auto-generated method stub
        /* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /* Non-filtrage des ressources statiques */
        String chemin = request.getRequestURI().substring( request.getContextPath().length() );
        if ( chemin.startsWith( "/inc" ) ) {
            chain.doFilter( request, response );
            return;
        }

        /* Non-filtrage des ressources statiques */
        String chemin1 = request.getRequestURI().substring( request.getContextPath().length() );
        if ( chemin1.startsWith( "/bootstrap" ) ) {
            chain.doFilter( request, response );
            return;
        }

        /* Non-filtrage des ressources statiques */
        String chemin2 = request.getRequestURI().substring( request.getContextPath().length() );
        if ( chemin2.startsWith( "/src" ) ) {
            chain.doFilter( request, response );
            return;
        }

        /* Non-filtrage des ressources statiques */
        String chemin3 = request.getRequestURI().substring( request.getContextPath().length() );
        if ( chemin3.startsWith( "/WEB-INF/lib" ) ) {
            chain.doFilter( request, response );
            return;
        }
        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            /* Redirection vers la page publique */
            response.sendRedirect( request.getContextPath() + ACCES_PUBLIC );
        } else {
            /* Affichage de la page restreinte */
            chain.doFilter( request, response );
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
