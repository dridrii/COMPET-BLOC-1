package com.cptbloc.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cptbloc.beans.Bloc;
import com.cptbloc.dao.BlocDAO;
import com.cptbloc.dao.DAOException;

public final class ModificationBlocForm {
    private static final String CHAMP_IDBLOC      = "idBlocTX";
    private static final String CHAMP_NUMBLOC     = "NumBlocTX";
    private static final String CHAMP_COULEURDIFF = "CouleurDiffTX";
    private static final String CHAMP_COULEURVOIE = "CouleurVoieTX";
    private static final String CHAMP_OUVREUR     = "OuvreurTX";
    private static final String CHAMP_VALEURINIT  = "ValeurInitTX";

    private String              resultat;
    private Map<String, String> erreurs           = new HashMap<String, String>();
    private BlocDAO             blocDAO;

    public ModificationBlocForm( BlocDAO blocDAO ) {
        this.blocDAO = blocDAO;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Bloc ModifierBloc( HttpServletRequest request ) {
        String idBlocTX = getValeurChamp( request, CHAMP_IDBLOC );
        String numBlocTX = getValeurChamp( request, CHAMP_NUMBLOC );
        String couleurDiff = getValeurChamp( request, CHAMP_COULEURDIFF );
        String couleurVoie = getValeurChamp( request, CHAMP_COULEURVOIE );
        String ouvreur = getValeurChamp( request, CHAMP_OUVREUR );
        String valeurInitTX = getValeurChamp( request, CHAMP_VALEURINIT );

        String str1 = idBlocTX;
        Long idBloc = Long.parseLong( str1 );

        String str = valeurInitTX;
        int valeurInit = Integer.parseInt( str );

        Bloc bloc = new Bloc();
        try {
            bloc.setIdBloc( idBloc );
            traiterNumBLoc( numBlocTX, bloc, idBloc );
            bloc.setCouleurDiff( couleurDiff );
            bloc.setCouleurVoie( couleurVoie );
            bloc.setOuvreur( ouvreur );
            bloc.setValeurInit( valeurInit );

            if ( erreurs.isEmpty() ) {
                blocDAO.MAJBloc( bloc, idBloc );
                resultat = "Succès de la modification.";
            } else {
                resultat = "Echec de la modification";
            }

        } catch ( DAOException e ) {
            setErreur( "imprévu", "Erreur imprévue lors de la modification." );
            resultat = "Echec de l'insciption : une erreur imprévue et survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
        return bloc;
    }

    private void traiterNumBLoc( String numBlocTX, Bloc bloc, Long idBloc ) {
        int valeurNumBloc = -1;
        try {
            valeurNumBloc = validationNumBloc( numBlocTX, bloc, idBloc );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NUMBLOC, e.getMessage() );
        }
        bloc.setNumBloc( valeurNumBloc );
    }

    private int validationNumBloc( String numBlocTX, Bloc bloc, Long idBLoc ) throws FormValidationException {
        int temp;

        Bloc bloc2 = new Bloc();
        bloc2 = blocDAO.trouverId( idBLoc );

        temp = Integer.parseInt( numBlocTX );

        if ( numBlocTX != null ) {

            if ( blocDAO.trouverNumBloc( temp ) != null ) {
                if ( bloc2.getNumBloc() != temp ) {
                    throw new FormValidationException( "Ce Numéro de bloc est déjà attribué." );
                }
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un numéro de bloc" );
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
