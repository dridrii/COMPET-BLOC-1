package com.cptbloc.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cptbloc.beans.Juge;

public final class ConnexionJugeForm {
    private static final String CHAMP_PSEUDO = "pseudo";
    private static final String CHAMP_MDP    = "mdp";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Juge connecterJuge( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String pseudo = getValeurChamp( request, CHAMP_PSEUDO );
        String mdp = getValeurChamp( request, CHAMP_MDP );

        Juge juge = new Juge();

        /* Validation du champ pseudo. */
        try {
            validationPseudo( pseudo );
        } catch ( Exception e ) {
            setErreur( CHAMP_PSEUDO, e.getMessage() );
        }
        juge.setPseudo( pseudo );

        /* Validation du champ mot de passe. */
        try {
            validationMdp( mdp );
        } catch ( Exception e ) {
            setErreur( CHAMP_MDP, e.getMessage() );
        }
        juge.setMdp( mdp );

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        return juge;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationPseudo( String pseudo ) throws Exception {
        if ( pseudo != null ) {
            if ( pseudo.length() < 6 ) {
                throw new Exception( "Votre pseudo doit contenir au moin 6 caractères" );
            }
        } else {
            throw new Exception( "Merci d'entrer un pseudo" );
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMdp( String mdp ) throws Exception {
        if ( mdp != null ) {
            if ( mdp.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}