package com.cptbloc.forms;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cptbloc.beans.Bloc;
import com.cptbloc.dao.BlocDAO;
import com.cptbloc.dao.DAOException;

public final class CreationBlocForm {
    private static final String CHAMP_NUMBLOC     = "NumBlocTX";
    private static final String CHAMP_COULEURDIFF = "CouleurDiffTX";
    private static final String CHAMP_COULEURVOIE = "CouleurVoieTX";
    private static final String CHAMP_OUVREUR     = "OuvreurTX";
    private static final String CHAMP_VALEURINIT  = "ValeurInitTX";

    private String              resultat;
    private Map<String, String> erreurs           = new HashMap<String, String>();
    private BlocDAO             blocDAO;

    public CreationBlocForm( BlocDAO blocDAO ) {
        this.blocDAO = blocDAO;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Bloc creerBloc( HttpServletRequest request ) {
        String numBloc = getValeurChamp( request, CHAMP_NUMBLOC );
        String couleurDiff = getValeurChamp( request, CHAMP_COULEURDIFF );
        String couleurVoie = getValeurChamp( request, CHAMP_COULEURVOIE );
        String ouvreur = getValeurChamp( request, CHAMP_OUVREUR );
        String valeurInitTX = getValeurChamp( request, CHAMP_VALEURINIT );
        
        int NbReussi = 0;
        
        Bloc bloc = new Bloc();
        try {
            traiterNumBLoc( numBloc, bloc );
            bloc.setCouleurDiff( couleurDiff );
            bloc.setCouleurVoie( couleurVoie );
            bloc.setOuvreur( ouvreur );
            bloc.setNbReussi( NbReussi );
            traiterValeurInit( valeurInitTX, bloc );

            if ( erreurs.isEmpty() ) {
                blocDAO.creer( bloc );
                resultat = "Succès de l'ajout.";
            } else {
                resultat = "Echec de l'ajout.";
            }

        } catch ( DAOException e ) {
            setErreur( "imprévu", "Erreur imprévue lors de la création." );
            resultat = "Echec de l'insciption : une erreur imprévue et survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
        return bloc;
    }

    private void traiterNumBLoc( String numBloc, Bloc bloc ) {
        int valeurNumBloc = -1;

        try {
            valeurNumBloc = validationNumBloc( numBloc );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NUMBLOC, e.getMessage() );
        }
        if ( valeurNumBloc > 0 ) {
            bloc.setNumBloc( valeurNumBloc );
        }
    }

    private void traiterValeurInit( String valeurInitTX, Bloc bloc ) {
        int valeurInit = -1;

        try {
            valeurInit = validationValeurInit( valeurInitTX );
        } catch ( FormValidationException e ) {

        }
        bloc.setValeurInit( valeurInit );
    }

    private int validationNumBloc( String numBloc ) throws FormValidationException {
        int temp;

        if ( numBloc != null ) {
            try {
                temp = Integer.parseInt( numBloc );
                if ( temp < 0 ) {
                    throw new FormValidationException( "Le numéro de bloc doit être positif !" );
                }
                if ( blocDAO.trouverNumBloc( temp ) != null ) {
                    throw new FormValidationException( "Ce Numéro de bloc est déjà attribué." );
                }
            } catch ( NumberFormatException e ) {
                temp = -1;
                throw new FormValidationException( "L'age doit être un nombre." );

            } catch ( NullPointerException e ) {
                throw new FormValidationException( "Aucune valeur n'a été insérer" );
            }

        } else {
            temp = -1;
            throw new FormValidationException( "Merci de chercher le bloc le plus élevé." );
        }
        return temp;
    }

    private int validationValeurInit( String valeurInitTX ) throws FormValidationException {
        int temp;
        if ( valeurInitTX != null ) {
            try {
                temp = Integer.parseInt( valeurInitTX );

                if ( temp < 0 ) {
                    throw new FormValidationException( "La valeur du bloc doit être positive !" );

                }

            } catch ( NumberFormatException e ) {
                temp = -1;
                throw new FormValidationException( "La valeur initiale doit être un nombre. " );
            }
        }else {
            temp = -1 ;
            throw new FormValidationException( "Merci d'entrer une valeur." );
        }

        return temp;
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
