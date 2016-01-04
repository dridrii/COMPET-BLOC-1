package com.cptbloc.dao;

import java.util.List;

import com.cptbloc.beans.Participant;

public interface ParticipantDAO {
    void creer( Participant participant ) throws DAOException;

    Participant trouver( String idParticipant ) throws DAOException;

    Participant trouverDossard( String dossard ) throws DAOException;
    
    Participant trouverIdParticipant( Long idParticipant) throws DAOException;

    List<Participant> lister() throws DAOException;

    void MAJParticipant(Long idParticipant ) throws DAOException;

    void supprimer( Participant participant ) throws DAOException;

}