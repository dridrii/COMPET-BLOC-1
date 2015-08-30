package com.cptbloc.dao;

import static com.cptbloc.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.cptbloc.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cptbloc.beans.Categorie;

public class CategorieDAOImpl implements CategorieDAO {

    private static final String SQL_SELECT_AGE_HOMME = "SELECT idDefCategorie, ageConfigHomme, ageConfigFemme FROM DefCategorie WHERE ageConfigHomme = ?";
    private static final String SQL_SELECT_AGE_FEMME = "SELECT idDefCategorie, ageConfigHomme, ageConfigFemme FROM DefCategorie WHERE ageConfigFemme = ? ";

    private DAOFactory          daoFactory;

    CategorieDAOImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Categorie trouverAgeHomme( int age ) throws DAOException {
        return trouverAgeHomme( SQL_SELECT_AGE_HOMME, age );
    }

    @Override
    public Categorie trouverAgeFemme( int age ) throws DAOException {
        return trouverAgeFemme( SQL_SELECT_AGE_FEMME, age );
    }

    /*
     * Méthode générique utilisée pour retourner un utilisateur depuis la base
     * de données, correspondant à n; la requête SQL donnée prenant en
     * paramètres les objets passés en argument.
     */
    private Categorie trouverAgeHomme( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Categorie categorie = null;

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
                categorie = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return categorie;
    }

    private Categorie trouverAgeFemme( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Categorie categorie = null;

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
                categorie = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return categorie;
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */

    private static Categorie map( ResultSet resultSet ) throws SQLException {
        Categorie categorie = new Categorie();

        categorie.setId( resultSet.getLong( "idDefCategorie" ) );
        categorie.setId( resultSet.getLong( "ageConfigHomme" ) );
        categorie.setId( resultSet.getLong( "ageConfigFemme" ) );
        return categorie;
    }

}