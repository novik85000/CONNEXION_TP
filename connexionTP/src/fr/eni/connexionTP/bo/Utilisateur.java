package fr.eni.connexionTP.bo;

public class Utilisateur {

	private int no_utilisateur;
	private String nom;
	private String prenom;
	private String email;
	private String mot_de_passe;
	
	public Utilisateur(int no_utilisateur, String nom, String prenom, String email, String mot_de_passe) {
		super();
		this.no_utilisateur = no_utilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mot_de_passe = mot_de_passe;
	}
	
	public Utilisateur(String nom, String prenom, String email, String mot_de_passe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mot_de_passe = mot_de_passe;
	}
	
	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getEmail() {
		return email;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}

	@Override
	public String toString() {
		return "Utilisateur [no_utilisateur=" + no_utilisateur + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", mot_de_passe=" + mot_de_passe + "]";
	}
	
	
	
	
}
