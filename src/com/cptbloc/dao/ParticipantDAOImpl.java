package com.cptbloc.dao;

import static com.cptbloc.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.cptbloc.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cptbloc.beans.Participant;

public class ParticipantDAOImpl implements ParticipantDAO {

    private static final String SQL_SELECT             = "SELECT idParticipant, dossard, nom, prenom, age, sex, categorieParti, resultat FROM Participant ORDER BY idParticipant";
    private static final String SQL_SELECT_PAR_DOSSARD = "SELECT idParticipant, dossard, nom, prenom, age, sex, categorieParti, resultat FROM Participant WHERE dossard = ?";
    private static final String SQL_SELECT_PAR_ID      = "SELECT idParticipant, dossard, nom, prenom, age, sex, categorieParti, resultat FROM Participant WHERE idParticipant = ?";
    private static final String SQL_INSERT             = "INSERT INTO Participant (dossard, nom, prenom, age, sex, categorieParti) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE             = "UPDATE Participant SET dossard = ?, nom = ?, prenom = ?, age = ?, sex = ?, categorieParti = ? WHERE idParticipant = ?";
    private static final String SQL_DELETE_PAR_ID      = "DELETE FROM Participant WHERE idParticipant =?";

    private DAOFactory          daoFactory;

    ParticipantDAOImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Participant trouver( String idParticipant ) throws DAOException {
        return trouver( SQL_SELECT, idParticipant );
    }
    
    @Override
    public Participant trouverIdParticipant( Long idParticipant ) throws DAOException {
        return trouver( SQL_SELECT_PAR_ID, idParticipant );
    }

    @Override
    public Participant trouverDossard( String dossard ) throws DAOException {
        return trouver( SQL_SELECT_PAR_DOSSARD, dossard );
    }

    /* Implémentation de la méthode définie dans l'interface participantDAO */
    @Override
    public void creer( Participant participant ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
                    participant.getDossard(),
                    participant.getNom(),
                    participant.getPrenom(),
                    participant.getAge(),
                    participant.getSex(),
                    participant.getCategorieparti() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                participant.setidParticipant( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException(
                        "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    @Override
    public List<Participant> lister() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Participant> participant = new ArrayList<Participant>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                participant.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );

        }
        return participant;
    }

    @Override
    public void supprimer( Participant participant ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true,
                    participant.getidParticipant() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression du client, aucune ligne supprimée de la table." );
            } else {
                participant.setidParticipant( null );
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
    private Participant trouver( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Participant participant = null;

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
                participant = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return participant;
    }



    /* Implémentation de la méthode définie dans l'interface participantDAO */
   
    @Override
    public void MAJParticipant(Participant participant, Long idParticipant ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true,
                    participant.getDossard(),
                    participant.getNom(),
                    participant.getPrenom(),
                    participant.getAge(),
                    participant.getSex(),
                    participant.getCategorieparti(),
                    participant.getidParticipant());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la modification du participant, aucune ligne modifié dans la table." );
            }
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }

    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */

    private static Participant map( ResultSet resultSet ) throws SQLException {
        Participant participant = new Participant();

        participant.setidParticipant( resultSet.getLong( "idParticipant" ) );
        participant.setDossard( resultSet.getString( "dossard" ) );
        participant.setNom( resultSet.getString( "nom" ) );
        participant.setPrenom( resultSet.getString( "prenom" ) );
        participant.setSex( resultSet.getString( "sex" ) );
        participant.setAge( resultSet.getInt( "age" ) );
        participant.setCategorieParti( resultSet.getString( "categorieparti" ) );
        return participant;
    }

}