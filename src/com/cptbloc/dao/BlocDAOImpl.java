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

    private static final String SQL_SELECT         = "SELECT idBloc, numBloc, couleurDiff, couleurVoie, ouvreur, nbReussi, valeurInit, valeurFinal FROM Bloc ORDER BY idBloc";
    private static final String SQL_SELECT_PAR_ID  = "SELECT idBloc, numBloc, couleurDiff, couleurVoie, ouvreur, nbReussi, valeurInit, valeurFinal FROM Bloc WHERE idBloc = ?";
    private static final String SQL_SELECT_NUMBLOC = "SELECT idBloc, numBloc, couleurDiff, couleurVoie, ouvreur, nbReussi, valeurInit, valeurFinal FROM Bloc WHERE numBloc = ? ";
    private static final String SQL_INSERT         = "INSERT INTO Bloc (numBloc, couleurDiff, couleurVoie, ouvreur, valeurInit) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE         = "UPDATE Bloc SET numBloc = ?, couleurDiff = ?, couleurVoie = ?, ouvreur = ?, valeurInit = ? WHERE idBloc = ?";
    private static final String SQL_DELETE_PAR_ID  = "DELETE FROM Bloc WHERE idBloc =?";

    private DAOFactory          daoFactory;

    BlocDAOImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Bloc trouver( Long idBloc ) throws DAOException {
        return trouver( SQL_SELECT, idBloc );
    }
    
    @Override
    public Bloc trouverId( Long idBloc ) throws DAOException {
        return trouver( SQL_SELECT_PAR_ID, idBloc );
    }

    @Override
    public Bloc trouverNumBloc( String numBloc ) throws DAOException {
        return trouverNumBloc( SQL_SELECT_NUMBLOC, numBloc );
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
    public void MAJBloc( Bloc bloc, Long idBloc ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true,
                    bloc.getNumBloc(),
                    bloc.getCouleurDiff(),
                    bloc.getCouleurVoie(),
                    bloc.getOuvreur(),
                    bloc.getValeurInit(),
                    bloc.getIdBloc() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la modification du bloc, aucune ligne modifié dans la table." );
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

        bloc.setIdBloc( resultSet.getLong( "idBloc" ) );
        bloc.setNumBloc( resultSet.getString( "numBloc" ) );
        bloc.setCouleurDiff( resultSet.getString( "couleurDiff" ) );
        bloc.setCouleurVoie( resultSet.getString( "couleurVoie" ) );
        bloc.setOuvreur( resultSet.getString( "ouvreur" ) );
        bloc.setNbReussi( resultSet.getInt( "nbReussi" ) );
        bloc.setValeurInit( resultSet.getInt( "valeurInit" ) );
        bloc.setValeurFinal( resultSet.getInt( "valeurFinal" ) );

        return bloc;
    }

}