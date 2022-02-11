package fr.eni.connexionTP.bll;

import java.util.List;

public class CatalogueManager {

	private static ArticleDAO daoArticles;
	
	private static CatalogueManager instance;
	
	public CatalogueManager() {
		//Instancier le Data Access Object
		//daoArticles = new ArticleDAOJdbcImpl();
	 	daoArticles =DAOFactory.getArticleDAO();

	}
	
	public static CatalogueManager getInstance() {
		if(instance == null) {
			instance = new CatalogueManager();
		}
		return instance;
	}
	
	//Ajouter un article au catalogue (liste d'articles)
	public void addArticle(Article newArticle)  throws BLLException{
		if(newArticle.getIdArticle()!=null){
			throw new BLLException("Article deja existant.");
		}
		try {
			validerArticle(newArticle);
			daoArticles.insert(newArticle);
		} catch (Exception e) {
			throw new BLLException("Echec addArticle", e);
		}		
	}

	
	//Liste les articles connus
	public static List<Article> getCatalogue() throws BLLException{
		List<Article> articles=null;
		try {
			articles = daoArticles.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération catalogue", e);
		}
		
		return articles;

	}

	
	
	//Modifier les informations d'un article (Ramette ou Stylo)
	public void updateArticle(Article article)  throws BLLException{
		try {
			validerArticle(article);
			daoArticles.update(article);
			
		} catch (Exception e) {
			throw new BLLException("Echec updateArticle-article:"+article, e);
		}

		
	}

	
	//Supprimer un article 
	public void removeArticle(Integer idArticle)  throws BLLException{
		try {
			daoArticles.delete(idArticle);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression de l'article - ", e);
		}
		
	}
	
	/**
	 * Recuperer un article connaissant son id
	 * @param index
	 * @throws BLLException
	 */
	public Article getArticleById(int idArticle) throws BLLException{
		Article article = null;
		try {
			article = daoArticles.selectById(idArticle);
		} catch (DALException e) {
			throw new BLLException("Echec getArticleById - ", e);
		}
		return article;
		
	}
	
	/**
	 * Valider les données d'un article
	 * @param a
	 * @throws BLLException
	 */
	public void validerArticle(Article a) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(a==null){
			throw new BLLException("Article null");
		}
		//Les attributs des articles sont obligatoires
		if(a.getReference()==null || a.getReference().trim().length()==0){
			sb.append("La reference article est obligatoire.\n");
			valide = false;
		}
		if(a.getMarque()==null || a.getMarque().trim().length()==0){
			sb.append("La marque  est obligatoire.\n");
			valide = false;
		}
		if(a.getDesignation()==null || a.getDesignation().trim().length()==0){
			sb.append("La designation  est obligatoire.\n");
			valide = false;
		}
		if(a instanceof Ramette && ((Ramette)a).getGrammage()<=0){
			sb.append("Le grammage doit avoir une valeur positive.\n");
			valide = false;
		}
		if(a instanceof Stylo ){
			Stylo s = (Stylo) a;
			if(s.getCouleur()==null || s.getCouleur().trim().length()==0){
				sb.append("La couleur pour un stylo  est obligatoire.\n");
				valide = false;
			}
		}
		
		if(!valide){
			throw new BLLException(sb.toString());
		}

	}


}
