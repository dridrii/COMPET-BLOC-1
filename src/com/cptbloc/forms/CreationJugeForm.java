package com.cptbloc.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cptbloc.beans.Juge;

public final class CreationJugeForm {
    private static final String CHAMP_PSEUDO   = "pseudoJuge";
    private static final String CHAMP_NOM      = "nomJuge";
    private static final String CHAMP_PRENOM   = "prenomJuge";
    private static final String CHAMP_MDP      = "mdpJuge";
    private static final String CHAMP_VALIDMDP = "validmdpJuge";

    private String              resultat;
    private Map<String, String> erreurs        = new HashMap<String, String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Juge creerJuge( HttpServletRequest request ) {
        String pseudo = getValeurChamp( request, CHAMP_PSEUDO );
        String nom = getValeurChamp( request, CHAMP_NOM );
        String prenom = getValeurChamp( request, CHAMP_PRENOM );
        String mdp = getValeurChamp( request, CHAMP_MDP );
        String validmdp = getValeurChamp( request, CHAMP_VALIDMDP );

        Juge juge = new Juge();

        try {
            validationPseudo( pseudo );
        } catch ( Exception e ) {
            setErreur( CHAMP_PSEUDO, e.getMessage() );
        }
        juge.setPseudo( pseudo );

        try {
            validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        juge.setNom( nom );

        try {
            validationPrenom( prenom );
        } catch ( Exception e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        juge.setPrenom( prenom );

        try {
            validationMdp( mdp, validmdp );
        } catch ( Exception e ) {
            setErreur( CHAMP_MDP, e.getMessage() );
            setErreur( CHAMP_VALIDMDP, null );
        }
        juge.setMdp( mdp );

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la création d'un nouveau Juge";
        } else {
            resultat = "Echec de la création d'un nouveau Juge";
        }
        return juge;
    }

    private void validationPseudo( String pseudo ) throws Exception {
        if ( pseudo != null ) {
            if ( pseudo.length() < 6 ) {
                throw new Exception( "Votre pseudo doit contenir au moin 6 caractères" );
            }
        } else {
            throw new Exception( "Merci d'entrer un pseudo" );
        }
    }

    private void validationNom( String nom ) throws Exception {
        if ( nom != null ) {
            if ( nom.length() < 3 ) {
                throw new Exception( "Le nom d'un Juge doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci d'entrer un nom." );
        }
    }

    private void validationPrenom( String prenom ) throws Exception {
        if ( prenom != null && prenom.length() < 3 ) {
            throw new Exception( "Le prénom d'un Juge doit contenir au moins 3 caractères." );
        }
    }

    private void validationMdp( String mdp, String validmdp ) throws Exception {
        if ( mdp != null && mdp.trim().length() != 0 && validmdp != null && validmdp.trim().length() != 0 ) {
            if ( !mdp.equals( validmdp ) ) {
                throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( mdp.trim().length() < 6 ) {
                throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
