package com.cptbloc.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cptbloc.beans.Juge;
import com.cptbloc.dao.DAOException;
import com.cptbloc.dao.JugeDAO;

public final class ConnexionJugeForm {
    private static final String CHAMP_PSEUDO = "pseudo";
    private static final String CHAMP_MDP    = "mdp";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();
    private JugeDAO             jugeDAO;

    public ConnexionJugeForm( JugeDAO jugeDAO ) {
        this.jugeDAO = jugeDAO;
    }

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
        String pwd;
        String pseudobdd;

        Juge juge = new Juge();

        try {
            traiterPseudo( pseudo, juge );
            traiterMdp( mdp, juge );

            if ( erreurs.isEmpty() ) {

                jugeDAO.trouver( pseudo );
                pwd = juge.getMdp();
                pseudobdd = juge.getPseudo();

                traiterConnection( pwd, pseudobdd, mdp, pseudo );

                if ( erreurs.isEmpty() ) {
                    resultat = "Succès de la connextion.";
                } else {
                    resultat = "Echec de la connexion.";
                }

            } else {
                resultat = "Échec de la connexion.";
            }

        }

        catch ( DAOException e ) {
            setErreur( "imprévu", "Erreur imprévue lors de la validation." );
            resultat = "Echec de la connextion : une erreur imprévue et survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
        return juge;
    }

    private void traiterPseudo( String pseudo, Juge juge ) {
        try {
            validationPseudo( pseudo );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PSEUDO, e.getMessage() );
        }
        juge.setPseudo( pseudo );
    }

    private void traiterMdp( String mdp, Juge juge ) {
        try {
            validationMdp( mdp );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_MDP, e.getMessage() );
        }
        juge.setMdp( mdp );
    }

    private void traiterConnection( String pwd, String pseudobdd, String mdp, String pseudo ) {
        try {
            validationConnection( pwd, pseudobdd, mdp, pseudo );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_MDP, e.getMessage() );
        }
    }

    private void validationConnection( String pwd, String pseudobdd, String mdp, String pseudo )
            throws FormValidationException {
        if ( pwd == mdp && pseudobdd == pseudo ) {

        } else {
            throw new FormValidationException( "Connextion échouer !" );
        }
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationPseudo( String pseudo ) throws FormValidationException {
        if ( pseudo != null ) {
            if ( pseudo.length() < 6 ) {
                throw new FormValidationException( "Votre pseudo doit contenir au moin 6 caractères" );
            } else if ( jugeDAO.trouver( pseudo ) == null ) {
                throw new FormValidationException( "Ce pseudo n'existe pas, inscrivez-vous!" );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un pseudo" );
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMdp( String mdp ) throws FormValidationException {
        if ( mdp != null ) {
            if ( mdp.length() < 3 ) {
                throw new FormValidationException( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir votre mot de passe." );
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