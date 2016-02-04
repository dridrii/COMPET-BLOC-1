package com.cptbloc.dao;

import static com.cptbloc.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.cptbloc.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cptbloc.beans.ResultatBP;

public class ResultatBPDAOImpl implements ResultatBPDAO {

    private static final String SQL_INSERT                           = "INSERT INTO Participant_has_Bloc (Participant_idParticipant,Bloc_idBloc) VALUES (?, ?)";
    private static final String SQL_SELECT_PARTICIPANT_IDPARTICIPANT = "SELECT Participant_idParticipant, Bloc_idBloc FROM Participant_has_Bloc WHERE Participant_idParticipant = ?";
    private static final String SQL_SELECT_Bloc_IDBLOC               = "SELECT Participant_idParticipant, Bloc_idBloc FROM Participant_has_Bloc WHERE Bloc_idBloc = ?";
    private static final String SQL_SELECT_DOUBLON                   = "SELECT Participant_idParticipant, Bloc_idBloc FROM Participant_has_Bloc WHERE Participant_idParticipant = ? AND Bloc_idBloc = ?";
    private static final String SQL_UPDATE                           = "UPDATE Participant_has_Bloc SET Bloc_idBloc = ? WHERE Participant_idParticipant = ?";
    private static final String SQL_DELETE_PAR_ID                    = "DELETE FROM Participant_has_Bloc WHERE Participant_idParticipant = ?";

    private DAOFactory          daoFactory;

    ResultatBPDAOImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ResultatBP trouverResultatB( Long Bloc_idBloc ) throws DAOException {
        return trouver( SQL_SELECT_Bloc_IDBLOC, Bloc_idBloc );
    }

    @Override
    public ResultatBP trouverResultatP( Long Participant_idParticipant ) throws DAOException {
        return trouver( SQL_SELECT_PARTICIPANT_IDPARTICIPANT, Participant_idParticipant );
    }

    @Override
    public ResultatBP trouverDoublon( long Participant_idParticipant, Long Bloc_idBloc ) throws DAOException {
        return trouver( SQL_SELECT_DOUBLON, Participant_idParticipant, Bloc_idBloc );
    }

    /* Implémentation de la méthode définie dans l'interface resultatBPDAO */
    @Override
    public void creer( ResultatBP resultatBP ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
                    resultatBP.getParticipant_IdParticipant(),
                    resultatBP.getBloc_IdBloc() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du résultat, aucune ligne ajoutée dans la table." );
            }

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    /*
     * Méthode générique utilisée pour retourner un utilisateur depuis la base
     * de données, correspondant à n; la requête SQL donnée prenant en
     * paramètres les objets passés en argument.
     */
    private ResultatBP trouver( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResultatBP resultatBP = null;

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
                resultatBP = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return resultatBP;
    }

    @Override
    public void MAJResultatBP( ResultatBP resultatBP, Long Participant_idParticipant ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true,
                    resultatBP.getBloc_IdBloc(),
                    resultatBP.getParticipant_IdParticipant() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException(
                        "Échec de la modification du participant, aucune ligne modifié dans la table." );
            }

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }

    }

    @Override
    public void supprimer( ResultatBP resultatBP ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true,
                    resultatBP.getParticipant_IdParticipant() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression du client, aucune ligne supprimée de la table." );
            } else {
                resultatBP.setParticipant_IdParticipant( null );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */

    private static ResultatBP map( ResultSet resultSet ) throws SQLException {
        ResultatBP resultatBP = new ResultatBP();

        resultatBP.setBloc_IdBloc( resultSet.getLong( "Bloc_idBloc" ) );
        resultatBP.setParticipant_IdParticipant( resultSet.getLong( "Participant_idParticipant" ) );
        return resultatBP;
    }

}