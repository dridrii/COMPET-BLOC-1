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
        String NumBloc = getValeurChamp( request, CHAMP_NUMBLOC );
        String CouleurDiff = getValeurChamp( request, CHAMP_COULEURDIFF );
        String CouleurVoie = getValeurChamp( request, CHAMP_COULEURVOIE );
        String Ouvreur = getValeurChamp( request, CHAMP_OUVREUR );
        String ValeurInitTX = getValeurChamp( request, CHAMP_VALEURINIT );

        String str = ValeurInitTX;
        int ValeurInit = Integer.parseInt( str );

        Bloc bloc = new Bloc();
        try {
            traiterNumBLoc( NumBloc, bloc );
            bloc.setCouleurDiff( CouleurDiff );
            bloc.setCouleurVoie( CouleurVoie );
            bloc.setOuvreur( Ouvreur );
            bloc.setValeurInit( ValeurInit );

            if ( erreurs.isEmpty() ) {
                blocDAO.creer( bloc );
                resultat = "Succès de l'inscription.";
            } else {
                resultat = "Echec de l'inscription.";
            }

        } catch ( DAOException e ) {
            setErreur( "imprévu", "Erreur imprévue lors de la création." );
            resultat = "Echec de l'insciption : une erreur imprévue et survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
        return bloc;
    }

    private void traiterNumBLoc( String NumBloc, Bloc bloc ) {
        try {
            validationNumBloc( NumBloc );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NUMBLOC, e.getMessage() );
        }
        bloc.setNumBloc( NumBloc );
    }

    private void validationNumBloc( String NumBloc ) throws FormValidationException {
        if ( NumBloc != null ) {
            if ( blocDAO.trouverNumBloc( NumBloc ) != null ) {
                throw new FormValidationException( "Ce Numéro de bloc est déjà attribué." );
            }
        } else {
            throw new FormValidationException( "Merci de chercher le bloc le plus élevé." );
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
