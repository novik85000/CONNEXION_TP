package fr.eni.connexionTP.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.connexionTP.bo.Utilisateur;
import fr.eni.connexionTP.dal.DALException;
import fr.eni.connexionTP.dal.JdbcTools;
import fr.eni.connexionTP.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String SQL_INSERT = "insert into utilisateurs (nom, prenom, email, mot_de_passe) "
			+ " values(?,?,?,?)";
	private static final String SQL_SELECT_BY_ID = "select no_utilisateur, nom, prenom, email, mot_de_passe "
			+ " from utilisateurs where no_utilisateur = ?";
	private static final String SQL_SELECT_ALL = "select no_utilisateur, nom, prenom, email, mot_de_passe "
			+ " from utilisateurs";
	private static final String SQL_UPDATE = "update utilisateurs set nom=?,prenom=?,email=?,mot_de_passe=? where no_utilisateur = ?";
	private static final String SQL_DELETE = "delete from utilisateurs where no_utilisateur=? ";
	

	public void insert(Utilisateur user) throws DALException {

		//try with resources
		try(
				Connection uneConnection = JdbcTools.getConnection();					
				PreparedStatement pStmt = uneConnection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {
			// Valoriser les paramètres de la requete
			pStmt.setString(1, user.getNom());
			pStmt.setString(2, user.getPrenom());
			pStmt.setString(3, user.getEmail().toLowerCase().trim());
			pStmt.setString(4, user.getMot_de_passe().trim());

			pStmt.executeUpdate();

			//Récupérer la valeur de la clé primaire générée par la BD
			ResultSet rsId = pStmt.getGeneratedKeys();
			if (rsId.next()) {
				user.setNo_utilisateur(rsId.getInt(1));
			}

		} catch (SQLException e) {
			
			throw new DALException("Erreur � l'ajout d'un utilisateur : " + user, e);

		}

	}


	public Utilisateur selectById(int id) throws DALException {
		ResultSet rs = null;
		Utilisateur user = null;
		try (Connection cnx = JdbcTools.getConnection(); PreparedStatement rqt = cnx.prepareStatement(SQL_SELECT_BY_ID);) {

			rqt.setInt(1, id);

			rs = rqt.executeQuery();
			if (rs.next()) {

				user = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),
							rs.getString("mot_de_passe"));
			}

			
		} catch (SQLException e) {
			throw new DALException("selectById failed - no_utilisateur = " + id, e);
		}
		return user;
	}

	public List<Utilisateur> selectAll() throws DALException {

		Statement rqt = null;
		ResultSet rs = null;
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		try (Connection cnx = JdbcTools.getConnection();) {
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(SQL_SELECT_ALL);
			Utilisateur user = null;

			while (rs.next()) {
				
				user = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),
						rs.getString("mot_de_passe"));
				
				liste.add(user);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - ", e);
		}
		return liste;

	}

	public void update(Utilisateur user) throws DALException {
		try (Connection cnx = JdbcTools.getConnection(); PreparedStatement rqt = cnx.prepareStatement(SQL_UPDATE);) {
			
			rqt.setString(1, user.getNom());
			rqt.setString(2, user.getPrenom());
			rqt.setString(3, user.getEmail().toLowerCase().trim());
			rqt.setString(4, user.getMot_de_passe().trim());
			rqt.setInt(5, user.getNo_utilisateur());

			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update article failed - " + user, e);
		}

	}

	public void delete(int id) throws DALException {

		try (Connection cnx = JdbcTools.getConnection(); PreparedStatement rqt = cnx.prepareStatement(SQL_DELETE);) {

			// l'intégrité référentielle s'occupe d'invalider la suppression
			// si l'article est référencé dans une ligne de commande
			rqt.setInt(1, id);
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" + id, e);
		}
	}
}
