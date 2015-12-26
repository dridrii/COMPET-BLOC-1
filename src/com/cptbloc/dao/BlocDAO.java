package com.cptbloc.dao;

import java.util.List;

import com.cptbloc.beans.Bloc;

public interface BlocDAO {
    void creer( Bloc bloc ) throws DAOException;

    Bloc trouver( String idBloc ) throws DAOException;
    
    Bloc trouverNumBloc( String NumBloc ) throws DAOException;

    List<Bloc> lister() throws DAOException;

    void supprimer( Bloc bloc ) throws DAOException;

}