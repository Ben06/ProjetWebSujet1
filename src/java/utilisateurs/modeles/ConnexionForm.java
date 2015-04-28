package utilisateurs.modeles;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;

import utilisateurs.modeles.Utilisateur;
// génère des erreurs d'injection EJB que je n'ai pas reussi à fixer
// cette classe avait pour but de gérer la connexion et l'inscription d'un utilisateur

public final class ConnexionForm
{
    

//    private static final String CHAMP_LOGIN = "login";
//    private static final String CHAMP_PASS = "motdepasse";
//    private static final String CHAMP_NOM = "nom";
//    private static final String CHAMP_PRENOM = "prenom";
//    GestionnaireUtilisateurs gestionnaireUtilisateur = new GestionnaireUtilisateurs();
//    
//    private String resultat;
//    private Map<String, String> erreurs = new HashMap<String, String>();
//
//    public String getResultat()
//    {
//	return resultat;
//    }
//
//    public Map<String, String> getErreurs()
//    {
//	return erreurs;
//    }
//
//    public boolean inscrireUtilisateur(HttpServletRequest request)
//    {
//	String login = getValeurChamp(request, CHAMP_LOGIN);
//	String motDePasse = getValeurChamp(request, CHAMP_PASS);
//	String nom = getValeurChamp(request, CHAMP_NOM);
//	String prenom = getValeurChamp(request, CHAMP_PRENOM);
//	
//	System.out.println("dans le inscrire utilisateur");
//	System.out.println("login : "+login+ " nom : "+nom+" prenom : "+prenom+" pass : "+motDePasse);
//	
//	gestionnaireUtilisateur.creeUtilisateur(prenom, nom, login);
//	
//	return true;
//    }
//
//    public Utilisateur connecterUtilisateur(HttpServletRequest request)
//    {
//	/* Récupération des champs du formulaire */
//	String login = getValeurChamp(request, CHAMP_LOGIN);
//	String motDePasse = getValeurChamp(request, CHAMP_PASS);
//
//	System.out.println("récupération de l'utilisateur dans la bd");
//	Utilisateur u = gestionnaireUtilisateur.getSingleUserByLogin(login);
//	
//	if(u!=null)
//	{
//	    if(u.getPassword().equals(motDePasse))
//	    {
//		System.out.println("Utilisateur correct, accès autorisé");
//		return u;
//	    }
//	    else
//	    {
//		System.out.println("Mot de passe incorrect");
//		setErreur(motDePasse, "Mot de passe incorrect");
//		return null;
//	    }
//	}
//	else
//	{
//	    System.out.println("Login incorrect");
//	    setErreur(login, "Login incorrect");
//	    return null;
//	}
//	
//    }
//
//    /**
//     * Valide l'adresse email saisie.
//     */
//    private void validationLogin(String login) throws Exception
//    {
//	if (login == null)
//	{
//	    throw new Exception("Merci de saisir un login valide.");
//	}
//    }
//
//    /**
//     * Valide le mot de passe saisi.
//     */
//    private void validationMotDePasse(String motDePasse) throws Exception
//    {
//	if (motDePasse != null)
//	{
//	    if (motDePasse.length() < 3)
//	    {
//		throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
//	    }
//	}
//	else
//	{
//	    throw new Exception("Merci de saisir votre mot de passe.");
//	}
//    }
//
//    /*
//     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
//     */
//    private void setErreur(String champ, String message)
//    {
//	erreurs.put(champ, message);
//    }
//
//    /*
//     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
//     * sinon.
//     */
//    private static String getValeurChamp(HttpServletRequest request, String nomChamp)
//    {
//	String valeur = request.getParameter(nomChamp);
//	if (valeur == null || valeur.trim().length() == 0)
//	{
//	    return null;
//	}
//	else
//	{
//	    return valeur;
//	}
//    }
}
