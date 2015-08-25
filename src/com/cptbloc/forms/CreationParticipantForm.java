package com.cptbloc.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cptbloc.beans.Juge;
import com.cptbloc.beans.Participant;
import com.cptbloc.dao.DAOException;
import com.cptbloc.dao.ParticipantDAO;

public final class CreationParticipantForm {
    private static final String CHAMP_DOSSARD   = "dossardParticipant";
    private static final String CHAMP_NOM       = "nomParticipant";
    private static final String CHAMP_PRENOM    = "prenomParticipant";
    private static final String CHAMP_AGE       = "ageParticipant";
    private static final String CHAMP_SEX       = "secParticipant";
    private static final String CHAMP_CATEGORIE = "categorieParticipant";

    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();
    private ParticipantDAO      participantDAO;

    public CreationParticipantForm( ParticipantDAO participantDAO ) {
        this.participantDAO = participantDAO;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Participant creerParticipant( HttpServletRequest request ) {
        String pseudo = getValeurChamp( request, CHAMP_PSEUDO );
        String nom = getValeurChamp( request, CHAMP_NOM );
        String prenom = getValeurChamp( request, CHAMP_PRENOM );
        String mdp = getValeurChamp( request, CHAMP_MDP );
        String validmdp = getValeurChamp( request, CHAMP_VALIDMDP );

        Participant participant = new Participant();
        try {
            traiterPseudo( pseudo, juge );
            traiterNom( nom, juge );
            traiterPrenom( prenom, juge );
            traiterMdp( mdp, validmdp, juge );

            if ( erreurs.isEmpty() ) {
                participantDAO.creer( participant );
                resultat = "Succès de l'inscription.";
            } else {
                resultat = "Echec de l'inscription.";
            }

        } catch ( DAOException e ) {
            setErreur( "imprévu", "Erreur imprévue lors de la création." );
            resultat = "Echec de l'insciption : une erreur imprévue et survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
        return participant;
    }

    private void traiterPseudo( String pseudo, Juge juge ) {
        try {
            validationPseudo( pseudo );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PSEUDO, e.getMessage() );
        }
        juge.setPseudo( pseudo );
    }

    private void traiterNom( String nom, Juge juge ) {
        try {
            validationNom( nom );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        juge.setNom( nom );
    }

    private void traiterPrenom( String prenom, Juge juge ) {
        try {
            validationPrenom( prenom );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        juge.setPrenom( prenom );
    }

    private void traiterMdp( String mdp, String validmdp, Juge juge ) {
        try {
            validationMdp( mdp, validmdp );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_MDP, e.getMessage() );
            setErreur( CHAMP_VALIDMDP, null );
        }
        juge.setMdp( mdp );
    }

    private void validationPseudo( String pseudo ) throws FormValidationException {
        if ( pseudo != null ) {
            if ( pseudo.length() < 6 ) {
                throw new FormValidationException( "Votre pseudo doit contenir au moin 6 caractères" );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un pseudo" );
        }
    }

    private void validationNom( String nom ) throws FormValidationException {
        if ( nom != null ) {
            if ( nom.length() < 3 ) {
                throw new FormValidationException( "Le nom d'un Juge doit contenir au moins 3 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un nom." );
        }
    }

    private void validationPrenom( String prenom ) throws FormValidationException {
        if ( prenom != null && prenom.length() < 3 ) {
            throw new FormValidationException( "Le prénom d'un Juge doit contenir au moins 3 caractères." );
        }
    }

    private void validationMdp( String mdp, String validmdp ) throws FormValidationException {
        if ( mdp != null && mdp.trim().length() != 0 && validmdp != null && validmdp.trim().length() != 0 ) {
            if ( !mdp.equals( validmdp ) ) {
                throw new FormValidationException(
                        "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( mdp.trim().length() < 6 ) {
                throw new FormValidationException( "Les mots de passe doivent contenir au moins 6 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
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
