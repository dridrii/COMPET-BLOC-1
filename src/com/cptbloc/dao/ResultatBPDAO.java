package com.cptbloc.dao;

import com.cptbloc.beans.ResultatBP;

public interface ResultatBPDAO {
    
    void creer( ResultatBP resultatBP) throws DAOException;
    
    void MAJResultatBP(ResultatBP resultatBP, Long Partidcipant_idParticipant) throws DAOException;

    ResultatBP trouverResultatB( Long Bloc_idBloc ) throws DAOException;
    
    ResultatBP trouverResultatP( Long Partidcipant_idParticipant ) throws DAOException;
    
    ResultatBP trouverDoublon  ( long Participant_idParticipant, Long Bloc_idBloc) throws DAOException;
    
    void supprimer( ResultatBP resultatBP) throws DAOException;
}
