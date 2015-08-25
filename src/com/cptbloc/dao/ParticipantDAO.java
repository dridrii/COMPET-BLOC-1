package com.cptbloc.dao;

import java.util.List;

import com.cptbloc.beans.Participant;

public interface ParticipantDAO {
    void creer( Participant participant ) throws DAOException;

    Participant trouver( String dossard ) throws DAOException;

    List<Participant> lister() throws DAOException;

    void supprimer( Participant participant ) throws DAOException;

}