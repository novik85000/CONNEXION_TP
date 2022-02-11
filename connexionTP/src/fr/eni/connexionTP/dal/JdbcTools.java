package fr.eni.connexionTP.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class JdbcTools {

	//bloc d'execution statique
	//Executé une seule fois au premier chargment de la classe !!!
	static {
			//DriverManager.registerDriver(new SQLServerDriver());
			try {
				String driverJdbc = Settings.getProperty("driverJdbc");
				Class.forName(driverJdbc).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Chargement du pilote JDBC réussi !!");
		
	}
	
	public static Connection getConnection() throws SQLException {
		//String url = "jdbc:sqlserver://localhost:1433;databaseName=BDD_DEMO";
		String url = Settings.getProperty("urldb");
		Connection uneConnection = DriverManager.getConnection(url, 
				Settings.getProperty("userdb"), Settings.getProperty("pwddb"));

		return uneConnection;
	}
	
	
}
