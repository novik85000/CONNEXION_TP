package fr.eni.connexionTP.dal;

import fr.eni.connexionTP.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {


	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

}
