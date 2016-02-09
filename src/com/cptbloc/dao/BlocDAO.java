package com.cptbloc.dao;

import java.util.List;

import com.cptbloc.beans.Bloc;

public interface BlocDAO {
    void creer( Bloc bloc ) throws DAOException;

    Bloc trouver( Long idBloc ) throws DAOException;
    
    Bloc trouverId( Long idBloc ) throws DAOException;
    
    Bloc trouverNumBloc( int numBloc ) throws DAOException;

    List<Bloc> lister() throws DAOException;
    
    void MAJBloc(Bloc bloc, Long idBloc) throws DAOException;

    void supprimer( Bloc bloc ) throws DAOException;

}