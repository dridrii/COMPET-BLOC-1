package com.cptbloc.dao;

import static com.cptbloc.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.cptbloc.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cptbloc.beans.Bloc;

public class BlocDAOImpl implements BlocDAO {

    private static final String SQL_SELECT         = "SELECT IdBloc, NumBloc, CouleurDiff, CouleurVoie, Ouveur, NbReussi, ValeurInit, ValeurFinal FROM bloc ORDER BY IdBloc";
    private static final String SQL_SELECT_NUMBLOC = "SELECT IdBloc, NumBloc, CouleurDiff, CouleurVoie, Ouveur, NbReussi, ValeurInit, ValeurFinal FROM bloc ORDER BY NumBloc";

    private static final String SQL_INSERT         = "INSERT INTO bloc (NumBloc, CouleurDiff, CouleurVoie, Ouvreur, ValeurInit) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_PAR_ID  = "DELETE FROM bloc WHERE id =?";

    private DAOFactory          daoFactory;

    BlocDAOImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Bloc trouver( String idBloc ) throws DAOException {
        return trouver( SQL_SELECT, idBloc );
    }
    
    @Override
    public Bloc trouverNumBloc( String NumBloc ) throws DAOException {
        return trouverNumBloc( SQL_SELECT_NUMBLOC, NumBloc );
    }

    /* Implémentation de la méthode définie dans l'interface participantDAO */
    @Override
    public void creer( Bloc bloc ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
                    bloc.getNumBloc(),
                    bloc.getCouleurDiff(),
                    bloc.getCouleurVoie(),
                    bloc.getOuvreur(),
                    bloc.getValeurInit() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du bloc, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                bloc.setIdBloc( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création du bloc en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    @Override
    public List<Bloc> lister() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Bloc> bloc = new ArrayList<Bloc>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                bloc.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );

        }
        return bloc;
    }

    @Override
    public void supprimer( Bloc bloc ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, bloc.getIdBloc() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression du bloc, aucune ligne supprimée de la table." );
            } else {
                bloc.setIdBloc( null );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

    /*
     * Méthode générique utilisée pour retourner un utilisateur depuis la base
     * de données, correspondant à n; la requête SQL donnée prenant en
     * paramètres les objets passés en argument.
     */
    private Bloc trouver( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Bloc bloc = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement une adresse email) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            if ( resultSet.next() ) {
                bloc = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return bloc;
    }
    
    private Bloc trouverNumBloc( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Bloc bloc = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement une adresse email) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            if ( resultSet.next() ) {
                bloc = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return bloc;
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */

    private static Bloc map( ResultSet resultSet ) throws SQLException {
        Bloc bloc = new Bloc();

        bloc.setIdBloc( resultSet.getLong( "IdBloc" ) );
        bloc.setNumBloc( resultSet.getInt( "NumBloc" ) );
        bloc.setCouleurDiff( resultSet.getString( "CouleurDiff" ) );
        bloc.setCouleurVoie( resultSet.getString( "CouleurVoie" ) );
        bloc.setOuvreur( resultSet.getString( "Ouvreur" ) );
        bloc.setNbReussi( resultSet.getInt( "NbReussi" ) );
        bloc.setValeurInit( resultSet.getInt( "ValeurInit" ) );
        bloc.setValeurFinal( resultSet.getInt( "ValeurFinal" ) );

        return bloc;
    }

}