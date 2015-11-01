package com.cptbloc.dao;

import static com.cptbloc.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.cptbloc.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cptbloc.beans.Juge;

public class JugeDAOImpl implements JugeDAO {

    private static final String SQL_SELECT            = "SELECT idJuge, pseudo, nom, prenom, mdp FROM Juge ORDER BY idJuge";
    private static final String SQL_SELECT_PAR_PSEUDO = "SELECT idJuge, pseudo, nom, prenom, mdp FROM Juge WHERE pseudo = ?";
    private static final String SQL_SELECT_CONNECTION = "SELECT idJuge, pseudo, nom, prenom, mdp FROM Juge WHERE pseudo = ? and mdp = ?";
    private static final String SQL_INSERT            = "INSERT INTO Juge (pseudo, nom, prenom, mdp) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE_PAR_ID     = "DELETE FROM Juge WHERE idJuge =?";

    private DAOFactory          daoFactory;

    JugeDAOImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Juge trouver( String pseudo ) throws DAOException {
        return trouver( SQL_SELECT_PAR_PSEUDO, pseudo );
    }

    @Override
    public Juge trouverconnection( String pseudo, String mdp ) throws DAOException {
        return trouverconnection( SQL_SELECT_CONNECTION, pseudo, mdp );
    }

    /* Implémentation de la méthode définie dans l'interface JugeDAO */
    @Override
    public void creer( Juge juge ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
                    juge.getPseudo(),
                    juge.getNom(),
                    juge.getPrenom(),
                    juge.getMdp() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                juge.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    @Override
    public List<Juge> lister() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Juge> juge = new ArrayList<Juge>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                juge.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );

        }
        return juge;
    }

    @Override
    public void supprimer( Juge client ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, client.getId() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression du client, aucune ligne supprimée de la table." );
            } else {
                client.setId( null );
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
    private Juge trouver( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Juge juge = null;

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
                juge = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return juge;
    }

    private Juge trouverconnection( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Juge juge = null;

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
                juge = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return juge;
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */

    private static Juge map( ResultSet resultSet ) throws SQLException {
        Juge juge = new Juge();

        juge.setId( resultSet.getLong( "idJuge" ) );
        juge.setPseudo( resultSet.getString( "pseudo" ) );
        juge.setNom( resultSet.getString( "nom" ) );
        juge.setPrenom( resultSet.getString( "prenom" ) );
        juge.setMdp( resultSet.getString( "mdp" ) );
        return juge;
    }

}