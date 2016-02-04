package com.cptbloc.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cptbloc.beans.ResultatBP;
import com.cptbloc.beans.Bloc;
import com.cptbloc.beans.Participant;
import com.cptbloc.dao.BlocDAO;
import com.cptbloc.dao.DAOException;
import com.cptbloc.dao.ParticipantDAO;
import com.cptbloc.dao.ResultatBPDAO;

public final class NouveauResultatForm {
    private static final String CHAMP_DOSSARD = "dossardParticipant";
    private static final String CHAMP_NUMBLOC = "NumBlocTX";
    private static final String CHAMP_ERREUR_GLOBAL ="ErreurGlobal";

    private String              resultat;
    private Map<String, String> erreurs       = new HashMap<String, String>();
    private ParticipantDAO      participantDAO;
    private BlocDAO             blocDAO;
    private ResultatBPDAO       resultatBPDAO;

    public NouveauResultatForm( ParticipantDAO participantDAO, BlocDAO blocDAO, ResultatBPDAO resultatBPDAO ) {
        this.participantDAO = participantDAO;
        this.blocDAO = blocDAO;
        this.resultatBPDAO = resultatBPDAO;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public ResultatBP NouveauResultat( HttpServletRequest request ) {
        String dossard = getValeurChamp( request, CHAMP_DOSSARD );
        String numBloc = getValeurChamp( request, CHAMP_NUMBLOC );
        Long Participant_idParticipant = null;
        Long Bloc_idBloc = null;

        Participant participant = new Participant();
        Bloc bloc = new Bloc();
        ResultatBP resultatBP = new ResultatBP();
        try {

            
            traiterDossard( dossard, participant, resultatBP );
            traiterNumBloc( numBloc, bloc, resultatBP );
            traiterDoublon( dossard, numBloc, Participant_idParticipant, Bloc_idBloc, participant, bloc, resultatBP );

            if ( erreurs.isEmpty() ) {
                resultatBPDAO.creer( resultatBP );
                resultat = "Succès de l'ajout.";
            } else {
                resultat = "Echec de l'ajout.";
            }

        } catch ( DAOException e ) {
            setErreur( "imprévu", "Erreur imprévue lors de la création." );
            resultat = "Echec de l'insciption : une erreur imprévue et survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
        return resultatBP;
    }

    private void traiterDoublon( String dossard, String numBloc, Long Participant_idParticipant, Long Bloc_idBloc,
            Participant participant, Bloc bloc,
            ResultatBP resultatBP ) {
        
        try {
            validationDoublon( dossard, numBloc, Participant_idParticipant, Bloc_idBloc, participant, bloc,
                    resultatBP );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_ERREUR_GLOBAL, e.getMessage() );
        }

    }

    private void traiterDossard( String dossard, Participant participant, ResultatBP resultatBP ) {
        try {
            validationDossard( dossard, participant );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_DOSSARD, e.getMessage() );
        }
        participant = participantDAO.trouverDossard( dossard );
        resultatBP.setParticipant_IdParticipant( participant.getidParticipant() );
    }

    private void traiterNumBloc( String numBloc, Bloc bloc, ResultatBP resultatBP ) {
        try {
            validationNumBloc( numBloc, bloc );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NUMBLOC, e.getMessage() );
        }

        bloc = blocDAO.trouverNumBloc( numBloc );
        resultatBP.setBloc_IdBloc( bloc.getIdBloc() );
    }

    private void validationDoublon( String dossard, String numBloc, Long Participant_idParticipant, Long Bloc_idBloc,
            Participant participant, Bloc bloc, ResultatBP resultatBP ) throws FormValidationException {

        participant = participantDAO.trouverDossard( dossard );
        resultatBP.setParticipant_IdParticipant( participant.getidParticipant() );

        bloc = blocDAO.trouverNumBloc( numBloc );
        resultatBP.setBloc_IdBloc( bloc.getIdBloc() );
        
        Participant_idParticipant = resultatBP.getParticipant_IdParticipant();
        Bloc_idBloc= resultatBP.getBloc_IdBloc();

        if ( resultatBPDAO.trouverDoublon( Participant_idParticipant, Bloc_idBloc ) != null ){
            throw new FormValidationException( "Ce résultat existe déjà ! " );
        }
    }

    private void validationDossard( String dossard, Participant participant ) throws FormValidationException {
        if ( dossard != null ) {
            if ( participantDAO.trouverDossard( dossard ).equals( null ) ) {
                throw new FormValidationException( "Ce dossard n'est pas attribué" );

            }
        } else {
            throw new FormValidationException( "Merci d'entrer un numéro de dossard" );
        }

    }

    private void validationNumBloc( String numBloc, Bloc bloc ) throws FormValidationException {
        if ( numBloc != null ) {
            if ( blocDAO.trouverNumBloc( numBloc ).equals( null ) ) {
                throw new FormValidationException( "Ce numéro de bloc n'est pas attribuer" );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un numro de bloc." );
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
