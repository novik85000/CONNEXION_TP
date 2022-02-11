package fr.eni.connexionTP.dal;

import java.util.ArrayList;
import java.util.List;

import fr.eni.connexionTP.bo.Utilisateur;

public class AppliTestDAL {
	
	public static void main(String[] args) {

		//verification de methodes suivantes :
		//public void insert(T art) throws DALException;
		//public T selectById(int id) throws DALException ;
		//public List<T> selectAll() throws DALException ;
		//public void update(T data) throws DALException ;
		//public void delete(int id) throws DALException ;
		
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		
		UtilisateurDAO userDAO = DAOFactory.getUtilisateurDAO();
		
		Utilisateur konstantin = new Utilisateur("Konstantin", "NOVIK", "konst123@gmail.com", "QWERTY");
		Utilisateur konstantin1 = new Utilisateur("Konstantka", "NOVIK", "konst123@gmail.com", "QWERTY");
		Utilisateur konstantin2 = new Utilisateur("Konstantinchik", "NOVIK", "konst123@gmail.com", "QWERTY");
		
		System.out.println("Utilisateurs a bien ajoute");
		
		try {
			userDAO.insert(konstantin);
			System.out.println("Article ajouté  : " + konstantin.toString() );
			userDAO.insert(konstantin1);
			System.out.println("Article ajouté  : " + konstantin1.toString() );
			userDAO.insert(konstantin2);
			System.out.println("Article ajouté  : " + konstantin2.toString() );


			Utilisateur u = userDAO.selectById(konstantin1.getNo_utilisateur());
			System.out.println("\nSélection de l'article par id  : " + u.toString() );

			//Sélection de tous les articles
			utilisateurs = userDAO.selectAll();
			System.out.println("\nSélection de tous les articles  : "  );

			//Modification d'un article
			System.out.println("\nModification d'un article  : " );
			System.out.println("Article avant modification : "  + konstantin1.toString());
			
			konstantin1.setNom("Kostik");
			
			userDAO.update(konstantin1);
			
			konstantin1 = userDAO.selectById(konstantin1.getNo_utilisateur());
			System.out.println("Article apres modification  : " + konstantin1.toString() );
			
			
			//Suppression d'un article
			System.out.println("\nSuppression de l'article  : " + konstantin2.toString());
			userDAO.delete(konstantin2.getNo_utilisateur());
			utilisateurs = userDAO.selectAll();
			System.out.println("Liste des articles apres suppression : "  );
			afficherUtilisateurs(utilisateurs);
			System.out.println("---------------------------------------------------------------");

			
		} catch (DALException e) {
			e.printStackTrace();
		}

	}
	

	private static void afficherUtilisateurs(List<Utilisateur> utilisateurs){
		StringBuffer sb = new StringBuffer();
		for(Utilisateur user: utilisateurs){
			sb.append(user.toString());
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
		
}
