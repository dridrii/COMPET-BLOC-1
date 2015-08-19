package com.cptbloc.dao;

import java.util.List;

import com.cptbloc.beans.Juge;
import com.cptbloc.beans.Jugec;

public interface JugeDAO {
    void creer( Juge juge ) throws DAOException;

    Juge trouver( String pseudo ) throws DAOException;

    Jugec trouverconnection( String pseudo ) throws DAOException;

    List<Juge> lister() throws DAOException;

    void supprimer( Juge juge ) throws DAOException;

}