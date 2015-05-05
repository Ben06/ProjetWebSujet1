/* 
 * To change this template, choose Tools | Templates 
 * and open the template in the editor. 
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
/*import java.net.URL;
 import java.nio.file.Path;
 import java.util.ArrayList;*/
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilisateurs.gestionnaires.GestionnaireAdresses;
import utilisateurs.gestionnaires.GestionnaireContacts;
import utilisateurs.gestionnaires.GestionnaireTelephones;
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;
import utilisateurs.modeles.Adresse;
import utilisateurs.modeles.ConnexionForm;
import utilisateurs.modeles.Contact;
import utilisateurs.modeles.Telephone;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author michel
 */
@WebServlet(name = "ServletUsers", urlPatterns =
{
    "/ServletUsers"
})
public class ServletUsers extends HttpServlet
{

    @EJB
    private GestionnaireAdresses gestionnaireAdresses;

    @EJB
    private GestionnaireTelephones gestionnaireTelephones;

    @EJB
    private GestionnaireContacts gestionnaireContacts;

    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    private Map<String, String> erreurs = new HashMap<String, String>();
    int page = 0;
    private ConnexionForm form = new ConnexionForm();

    // ici injection de code ! On n'initialise pas !  
    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
    {
	HttpSession session = request.getSession();
	Object current = session.getAttribute("sessionUtilisateur");

	Utilisateur currentUser = (Utilisateur) current;
	if (currentUser != null)
	{
	    System.out.println("session : " + currentUser.getId());
	}
	// Pratique pour décider de l'action à faire  
	String action = request.getParameter("action");
	String nom = request.getParameter("nom");
	String prenom = request.getParameter("prenom");
	String login = request.getParameter("login");
	String search = request.getParameter("search");
	String contact = request.getParameter("contact");
	String option = request.getParameter("option");
	String contactNom = request.getParameter("contactNom");
	String contactPrenom = request.getParameter("contactPrenom");
	String erreurPhoto = request.getParameter("erreurPhoto");

	String forwardTo = "";
	String message = "";

	System.out.println("get : action : " + action);

	// génération du nombre de page totale
	float nbMaxPageFloat = (float) gestionnaireUtilisateurs.getAllUsers().size() / 10;
	int nbMaxPageInt = gestionnaireUtilisateurs.getAllUsers().size() / 10;
	int nbMaxPage = 0;

	if (nbMaxPageFloat != nbMaxPageInt)
	{
	    if (nbMaxPageFloat > nbMaxPageInt)
	    {
		nbMaxPage = nbMaxPageInt + 1;
	    }
	}
	else
	{
	    nbMaxPage = nbMaxPageInt;
	}
	///////////////////////////////////////////////

	if (action != null)
	{
	    if (action.equals("Next"))
	    {
		page++;
		nbMaxPageFloat = (float) gestionnaireContacts.getAllContact(currentUser).size() / 10;
		nbMaxPageInt = gestionnaireContacts.getAllContact(currentUser).size() / 10;
		nbMaxPage = 0;

		if (nbMaxPageFloat != nbMaxPageInt)
		{
		    if (nbMaxPageFloat > nbMaxPageInt)
		    {
			nbMaxPage = nbMaxPageInt + 1;
		    }
		}
		else
		{
		    nbMaxPage = nbMaxPageInt;
		}
		Collection<Contact> liste = gestionnaireContacts.getTenContacts(page, currentUser);
		request.setAttribute("listeDesUsers", liste);
		forwardTo = "index.jsp?action=listerLesUtilisateurs";
//		message = String.valueOf(page);
	    }

	    if (action.equals("Previous"))
	    {
		page--;
		nbMaxPageFloat = (float) gestionnaireContacts.getAllContact(currentUser).size() / 10;
		nbMaxPageInt = gestionnaireContacts.getAllContact(currentUser).size() / 10;
		nbMaxPage = 0;

		if (nbMaxPageFloat != nbMaxPageInt)
		{
		    if (nbMaxPageFloat > nbMaxPageInt)
		    {
			nbMaxPage = nbMaxPageInt + 1;
		    }
		}
		else
		{
		    nbMaxPage = nbMaxPageInt;
		}
		Collection<Contact> liste = gestionnaireContacts.getTenContacts(page, currentUser);
		request.setAttribute("listeDesUsers", liste);
		forwardTo = "index.jsp?action=listerLesUtilisateurs";
//		message = message = String.valueOf(page);
	    }

	    /////////////////////////////////////////////////////////////////////////
	    //////////////////////// UTILISATEURS (versions précédentes) ///////////
	    ////////////////////////////////////////////////////////////////////////
	    if (action.equals("chercherParLogin"))
	    {
		System.out.println("option : " + option);
		erreurs.clear();
		Collection<Contact> liste = gestionnaireContacts.getContact(currentUser, search, option);

		if (liste.size() == 0)
		{
		    liste = null;
		}
		request.setAttribute("listeDesUsers", liste);
		forwardTo = "index.jsp?action=chercherParLogin";
	    }
//
	    if (action.equals("listerLesUtilisateurs"))
	    {
		nbMaxPageFloat = (float) gestionnaireContacts.getAllContact(currentUser).size() / 10;
		nbMaxPageInt = gestionnaireContacts.getAllContact(currentUser).size() / 10;
		nbMaxPage = 0;

		if (nbMaxPageFloat != nbMaxPageInt)
		{
		    if (nbMaxPageFloat > nbMaxPageInt)
		    {
			nbMaxPage = nbMaxPageInt + 1;
		    }
		}
		else
		{
		    nbMaxPage = nbMaxPageInt;
		}

//		System.out.println("dans le listerUtilisateur");
//		System.out.println("dans le listerlesUtilisateurs, page : " + page + "nbPage ? " + nbMaxPage);
//		Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
		Collection<Contact> liste = gestionnaireContacts.getTenContacts(page, currentUser);
		if (liste.size() == 0)
		{
		    liste = null;
		}
		else
		{
		    int i = 0;
		    for (Contact c : liste)
		    {
			Collection<Telephone> tel = c.getPhone();
			Collection<Adresse> adr = c.getAdresses();
//			Map<String, String> phones = new HashMap<>();

//			for (Telephone t : tel)
//			{
//			    phones.put(Integer.toString(c.getId()), t.getNumero());
//			} 
			if (adr.size() == 0)
			{
			    System.out.println("liste null pour le contact : " + c.getFirstname());
			    adr = null;
			}
			if (tel.size() == 0)
			{
			    System.out.println("liste null pour le contact : " + c.getFirstname());
			    tel = null;
			}

			request.setAttribute("phonesOf" + c.getId(), tel);
			request.setAttribute("adressesOf" + c.getId(), adr);

		    }
		}
		request.setAttribute("listeDesUsers", liste);
//		request.setAttribute("lis", current);
		forwardTo = "index.jsp?action=listerLesUtilisateurs";
		message = "Liste des utilisateurs";
	    }
	    else if (action.equals("creerUtilisateursDeTest"))
	    {
		Collection<Contact> contacts = gestionnaireContacts.creerContactDeTest();
		
//		Collection<Contact> contacts = gestionnaireContacts.getAllContact(currentUser);
		for (Contact c : contacts)
		{
		    Collection<Telephone> tels = gestionnaireTelephones.creerTelephonesTest();
		    for (Telephone t:tels)
		    {
			gestionnaireContacts.ajouterNumero(currentUser, c, t);
		    }
		    Collection<Adresse> adr = gestionnaireAdresses.creerAdresseTest();
		    for(Adresse a : adr)
		    {
			gestionnaireContacts.ajouterAdresse(currentUser, c, a);
		    }
		    gestionnaireUtilisateurs.addContact(currentUser, c);
		}
//		System.out.println("après le premier all contact");
		nbMaxPageFloat = (float) gestionnaireContacts.getAllContact(currentUser).size() / 10;
//		System.out.println("après le nbMaxPageFloat : " + nbMaxPageFloat);
		nbMaxPageInt = gestionnaireContacts.getAllContact(currentUser).size() / 10;
//		System.out.println("après le nbMaxPageInt : " + nbMaxPageInt);

//		System.out.println("liste des telephones ");
		if (nbMaxPageFloat != nbMaxPageInt)
		{
		    if (nbMaxPageFloat > nbMaxPageInt)
		    {
			nbMaxPage = nbMaxPageInt + 1;
		    }
		}
		else
		{
		    nbMaxPage = nbMaxPageInt;
		}

//		System.out.println("avant ten contacts");
		Collection<Contact> liste = gestionnaireContacts.getTenContacts(page, currentUser);
//		System.out.println("après ten contacts");
		request.setAttribute("listeDesUsers", liste);
//		System.out.println("après le set Attribute");
		forwardTo = "index.jsp?action=listerLesUtilisateurs";
//		System.out.println("après forwardTo");
		message = "Liste des utilisateurs";
	    }
//
//	    else if (action.equals("deleteAllUsers"))
//	    {
//		System.out.println("supprimons tous les users !!!!");
//		gestionnaireUtilisateurs.deleteAllUser();
//		Collection<Utilisateur> liste = gestionnaireUtilisateurs.getTenUsers(page);
//		request.setAttribute("listeDesUsers", liste);
//		forwardTo = "index.jsp?action=allUsersDeleted";
//	    }
	    else if (action.equals("deleteAllUsersConfirmation"))
	    {
		System.out.println("confirmation de suppression");
		forwardTo = "index.jsp?action=deleteAllUsersConfirmation";
	    }
	    else if (action.equals("allUsersDeleted"))
	    {
		System.out.println("tous les users sont suppr");
		forwardTo = "index.jsp?action=allUsersDeleted";
	    }
	    else if (action.equals("supprimerAll"))
	    {
		forwardTo = "index.jsp?action=deleteAllUsersConfirmation";
	    }

	    else if (action.equals("utilisateurMisAJour"))
	    {
		forwardTo = "index.jsp?action=utilisateurMisAJour";
	    }

	    /////////////////////////////////////////////////////////////////////////////
	    ///////////////////////GESTION DES ERREURS//////////////////////////////////
	    ////////////////////////////////////////////////////////////////////////////
	    else if (action.equals("erreurCreationUtilisateur"))
	    {
		request.setAttribute("erreurs", erreurs);
		forwardTo = "index.jsp?action=erreurCreationUtilisateur";
	    }

	    else if (action.equals("erreurMettreAJourUtilisateur"))
	    {
		request.setAttribute("erreurs", erreurs);
		forwardTo = "index.jsp?action=erreurMettreAJourUtilisateur";
	    }

	    else if (action.equals("erreurSupprimerUtilisateur"))
	    {
		request.setAttribute("erreurs", erreurs);
		forwardTo = "index.jsp?action=erreurSupprimerUtilisateur";
	    }

	    else if (action.equals("erreurInscription"))
	    {
		request.setAttribute("erreurs", erreurs);
		forwardTo = "index.jsp?action=erreurInscription";
	    }

	    else if (action.equals("erreurConnexion"))
	    {
		System.out.println("wutu");
		request.setAttribute("erreurs", erreurs);
		forwardTo = "index.jsp?action=erreurConnexion";
	    }
	    ///////////////////////////////////////////////////////////////////////////////
	    /////////////////// REDIRECTION VERS D'AUTRES ACTIONS /////////////////////////
	    //////////////////////////////////////////////////////////////////////////////

	    else if (action.equals("erreurMiseAJour"))
	    {
		forwardTo = "index.jsp?action=telephoneSupprime";
	    }

	    else if (action.equals("supprimerUneAdresse"))
	    {
		Collection<Contact> contacts = gestionnaireContacts.getContactByID(currentUser, contact);
		for (Contact c : contacts)
		{
		    request.setAttribute("adressesList", c.getAdresses());
		}
		forwardTo = "index.jsp?action=supprimerUneAdresse&contactID=" + contact + "&contactNom=" + contactNom + "&contactPrenom=" + contactPrenom;
	    }

	    else if (action.equals("telephoneSupprime"))
	    {
		forwardTo = "index.jsp?action=telephoneSupprime";
	    }

	    else if (action.equals("adresseSupprimee"))
	    {
		forwardTo = "index.jsp?action=telephoneSupprimee";
	    }

	    else if (action.equals("supprimerUnNumero"))
	    {
		Collection<Contact> contacts = gestionnaireContacts.getContactByID(currentUser, contact);
		for (Contact c : contacts)
		{
		    request.setAttribute("phonesList", c.getPhone());
		}
		forwardTo = "index.jsp?action=supprimerUnNumero&contactID=" + contact + "&contactNom=" + contactNom + "&contactPrenom=" + contactPrenom;
	    }

	    else if (action.equals("numeroAjoute"))
	    {
		forwardTo = "index.jsp?action=numeroAjoute";
	    }

	    else if (action.equals("ajouterUnNumero"))
	    {
		forwardTo = "index.jsp?action=ajouterUnNumero&contactID=" + contact + "&contactNom=" + contactNom + "&contactPrenom=" + contactPrenom;
	    }
	    else if (action.equals("photoAjoute"))
	    {
		forwardTo = "index.jsp?action=photoAjoute";
	    }

	    else if (action.equals("modification"))
	    {
//		Contact toDelete = gestionnaireContacts.getSingleContactByID(currentUser, contact);
		System.out.println("modification du contact d'id : " + contact);
		Collection<Contact> contacts = currentUser.getContacts();
		for (Contact c : contacts)
		{
		    Collection<Telephone> tels = c.getPhone();
		    Collection<Adresse> adr = c.getAdresses();

		    request.setAttribute("adressesList", adr);
		    request.setAttribute("phonesList", tels);
		}

		request.setAttribute("erreurs", erreurs);
		forwardTo = "index.jsp?action=modification&contactID=" + contact + "&contactNom=" + contactNom + "&contactPrenom=" + contactPrenom;
	    }

	    else if (action.equals("suppression"))
	    {
//		Contact toDelete = gestionnaireContacts.getSingleContactByID(currentUser, contact);
		System.out.println("suppression du contact d'id : " + contact);
		forwardTo = "index.jsp?action=suppression&contactID=" + contact + "&contactNom=" + contactNom + "&contactPrenom=" + contactPrenom;
	    }
	    else if (action.equals("erreurmodifierPhoto"))
	    {
		request.setAttribute("erreurs", erreurs);
		System.out.println("l'utilisateur va maintenant pouvoir modifier une photo au contact d'id : " + contact);
		forwardTo = "index.jsp?action=modifierPhoto&contactID=" + contact + "&contactNom=" + contactNom + "&contactPrenom=" + contactPrenom + "&erreurPhoto=" + erreurPhoto;
	    }

	    else if (action.equals("modifierPhoto"))
	    {
		request.setAttribute("erreurs", erreurs);
		System.out.println("l'utilisateur va maintenant pouvoir modifier une photo au contact d'id : " + contact);
		forwardTo = "index.jsp?action=modifierPhoto&contactID=" + contact + "&contactNom=" + contactNom + "&contactPrenom=" + contactPrenom;
	    }

	    else if (action.equals("erreurajouterUnePhoto"))
	    {
//		erreurs.clear();
//		erreurs.put("photo", erreurPhoto);
//		request.setAttribute("erreurs", erreurs);
//		System.out.println("l'utilisateur va maintenant pouvoir ajouter une photo au contact d'id : " + contact);
		forwardTo = "index.jsp?action=ajouterUnePhoto&contactID=" + contact + "&contactNom=" + contactNom + "&contactPrenom=" + contactPrenom + "&erreurPhoto=" + erreurPhoto;
	    }

	    else if (action.equals("ajouterUnePhoto"))
	    {
//		erreurs.clear();
//		erreurs.put("photo", erreurPhoto);
//		request.setAttribute("erreurs", erreurs);
		System.out.println("l'utilisateur va maintenant pouvoir ajouter une photo au contact d'id : " + contact);
		forwardTo = "index.jsp?action=ajouterUnePhoto&contactID=" + contact + "&erreurPhoto=" + erreurPhoto;
	    }

	    else if (action.equals("inscrireUtilisateur"))
	    {
		System.out.println("l'utilisateur va maintenant pouvoir s'inscrire...");
		forwardTo = "index.jsp?action=inscrireUtilisateur";
	    }

	    else if (action.equals("utilisateurInscrit"))
	    {
		forwardTo = "index.jsp?action=utilisateurInscrit";
	    }

	    else if (action.equals("utilisateurCree"))
	    {
		forwardTo = "index.jsp?action=utilisateurCree";
	    }
	    else if (action.equals("utilisateurSupprime"))
	    {
		forwardTo = "index.jsp?action=utilisateurSupprime";
	    }

	    else if (action.equals("creationUtilisateur"))
	    {
		forwardTo = "index.jsp?action=creationUtilisateur";
	    }
	    else if (action.equals("rechercheUtilisateur"))
	    {
		forwardTo = "index.jsp?action=rechercheUtilisateur";
	    }
	    else if (action.equals("mettreajourUtilisateur"))
	    {
		forwardTo = "index.jsp?action=mettreajourUtilisateur";
	    }
	    else if (action.equals("supprimerUtilisateur"))
	    {
		forwardTo = "index.jsp?action=supprimerUtilisateur";
	    }
	    else if (action.equals("userConnecte"))
	    {
		forwardTo = "index.jsp?action=userConnecte";
	    }
	    else if (action.equals("userDeconnecte"))
	    {
		forwardTo = "index.jsp?action=userDeconnecte";
	    }

	}

//	System.out.println("numero de la page au niveau du forwardTo : " + page + "nombre max de page : " + nbMaxPage + "action : " + action);
	RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message + "&page=" + page + "&lastPage=" + nbMaxPage);
	dp.forward(request, response);
	// Après un forward, plus rien ne peut être exécuté après ! 

	response.setContentType("text/html;charset=UTF-8");
	try (PrintWriter out = response.getWriter())
	{
	    /* TODO output your page here. You may use following sample code. */
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Servlet ServletUsers</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>Servlet ServletUsers at " + request.getContextPath() + "</h1>");
	    out.println("</body>");
	    out.println("</html>");
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

	// On récupère le contenu du formulaire  
	String action = request.getParameter("action");
	String nom = request.getParameter("nom");
	String prenom = request.getParameter("prenom");
	String login = request.getParameter("login");
	String password = request.getParameter("motdepasse");
	String contact = request.getParameter("contact");
	String numero = request.getParameter("numero");
	String nomRue = request.getParameter("nomRue");
	String codePostal = request.getParameter("codePostal");
	String nomVille = request.getParameter("nomVille");
	String adresse = request.getParameter("adresse");
	String telephone = request.getParameter("telephone");
//	String actionMultipart = getParamFromMultipartRequest(request, "action");
	String redirectTo = "";
//	System.out.println("post : action : " + action);
//	request.get
	HttpSession session = request.getSession();
	Object current = session.getAttribute("sessionUtilisateur");
	Utilisateur currentUser = (Utilisateur) current;
	System.out.println("action post : " + action);
	System.out.println("contact dans le post : " + contact);
	if (currentUser != null)
	{
	    System.out.println("session : " + currentUser.getId());
	}

	if (action.equals("deleteUtilisateur"))
	{
	    erreurs.clear();
	    if (contact.length() != 0)
	    {
		System.out.println("début de la suppresion (avant la méthode) contact : " + contact);
		boolean result = gestionnaireContacts.deleteContact(currentUser, contact);
		System.out.println("result : " + result);
		redirectTo = "ServletUsers?action=utilisateurSupprime";
	    }
	    else
	    {
		erreurs.put("login", "Veuillez entrer un login.");
		redirectTo = "ServletUsers?action=erreurSupprimerUtilisateur";
	    }
	}
	if (action.equals("updateUtilisateur"))
	{
	    System.out.println("numero de tel dans le update : " + numero + ", length : " + numero.length());
	    erreurs.clear();
	    if (numero.length() != 10 && numero.length() != 0)
	    {
		erreurs.put("numero", "Veuillez entrer un numéro de telephone correct");
		request.setAttribute("erreurs", erreurs);
		redirectTo = "ServletUsers?action=modification&contact=" + contact;
	    }
	    else if (codePostal.length() != 5 && codePostal.length() != 0)
	    {
		erreurs.put("codePostal", "Veuillez entrer un code postal correct (6 chiffres)");
		request.setAttribute("erreurs", erreurs);
		redirectTo = "ServletUsers?action=modification&contact=" + contact;
	    }
	    else
	    {
		boolean res = gestionnaireContacts.updateContact(currentUser, contact, nom, prenom, adresse, telephone, nomRue, nomVille, codePostal, numero);
		if (res)
		{
		    redirectTo = "ServletUsers?action=utilisateurMisAJour";
		}
		else
		{
		    redirectTo = "ServletUsers?action=erreurMiseAJour";
		}
	    }
	}

	if (action.equals("ajoutNumero"))
	{

	    erreurs.clear();
	    if (numero.length() != 10)
	    {
		erreurs.put("numero", "Veuillez entrer un numéro de téléphone correct");
		redirectTo = "ServletUsers?action=ajouterUnNumero";
	    }
	    else
	    {
		System.out.println("ajouterUnNumero : " + contact);
		Contact c = null;
		Collection<Contact> contacts = gestionnaireContacts.getContactByID(currentUser, contact);
		for (Contact getContact : contacts)
		{
		    c = getContact;
		}
		Telephone tel = new Telephone(numero);
		gestionnaireContacts.ajouterNumero(currentUser, c, tel);
		redirectTo = "ServletUsers?action=numeroAjoute";
	    }
//	    System.out.println("on est dans le if du post");
	}

	if (action.equals("supprimerNumero"))
	{

	    System.out.println("supprimerNumero : " + contact);
	    Contact c = null;
	    for (Contact getContact : gestionnaireContacts.getContactByID(currentUser, contact))
	    {
		c = getContact;
	    }
	    boolean result = gestionnaireTelephones.deletePhone(numero, currentUser, c);
	    System.out.println("result : " + result);
	    redirectTo = "ServletUsers?action=telephoneSupprime";

//	    System.out.println("on est dans le if du post");
	}

	if (action.equals("supprimerAdresse"))
	{

	    System.out.println("supprimerAdresse : " + contact);
	    Contact c = null;
	    for (Contact getContact : gestionnaireContacts.getContactByID(currentUser, contact))
	    {
		c = getContact;
	    }
	    boolean result = gestionnaireAdresses.deleteAdr(adresse, currentUser, c);
	    System.out.println("result : " + result);
	    redirectTo = "ServletUsers?action=adresseSupprimee";

//	    System.out.println("on est dans le if du post");
	}

	// création d'un contact
	else if (action.equals("creerUnUtilisateur"))
	{

	    System.out.println("dans le créerUnContact");

	    erreurs.clear();
	    if (numero.length() != 10 && numero.length() != 0)
	    {
		erreurs.put("numero", "Veuillez entrer un numéro de telephone correct");
		request.setAttribute("erreurs", erreurs);
		redirectTo = "ServletUsers?action=erreurCreationUtilisateur";
	    }
	    else if (codePostal.length() != 5 && codePostal.length() != 0)
	    {
		erreurs.put("codePostal", "Veuillez entrer un code postal correct (6 chiffres)");
		request.setAttribute("erreurs", erreurs);
		redirectTo = "ServletUsers?action=erreurCreationUtilisateur";
	    }
	    else
	    {
		Contact c = new Contact(nom, prenom);

		Adresse adr = new Adresse(nomRue, Integer.parseInt(codePostal), nomVille);
		gestionnaireContacts.ajouterAdresse(currentUser, c, adr);
		Telephone tel = new Telephone(numero);
		gestionnaireContacts.ajouterNumero(currentUser, c, tel);
//	    gestionnaireTelephones.creerTelephone(numero);
		gestionnaireUtilisateurs.addContact(currentUser, c);
		System.out.println("done");
		Collection<Contact> contacts = gestionnaireContacts.getAllContact(currentUser);
		for (Contact c1 : contacts)
		{
		    System.out.println("c : " + c1.getFirstname() + " pour l'user: " + currentUser.getLogin());
		}
		redirectTo = "ServletUsers?action=utilisateurCree";
	    }
	}

	////////////////////////////////////////////////////////////////////////
	////////////////////// CONNEXION ET INSCRIPTION ////////////////////////
	////////////////////////////////////////////////////////////////////////
	else if (action.equals("connexion"))
	{
	    erreurs.clear();
	    if (login.length() == 0)
	    {
		erreurs.put("login", "Veuillez entrer un login.");
//		System.out.println("erreurs 1 : "+erreurs.get("login"));
		if (password.length() == 0)
		{
		    erreurs.put("password", "Veuillez entrer un mot de passe.");
//		    System.out.println("erreurs 2 : "+erreurs.get("password"));
		}
		redirectTo = "ServletUsers?action=erreurConnexion";
	    }
	    else
	    {
		Collection<Utilisateur> liste = gestionnaireUtilisateurs.getUser(login);

		if (liste.size() == 1)
		{
		    for (Utilisateur u : liste)
		    {
			if (password.length() == 0)
			{
			    erreurs.put("password", "Veuillez rentrer un mot de passe.");
			    redirectTo = "ServletUsers?action=erreurConnexion";
			}
			else if (password.equals(u.getPassword()))
			{
			    HttpSession session1 = request.getSession();
			    session1.setAttribute(ATT_SESSION_USER, u);
			    redirectTo = "ServletUsers?action=userConnecte";
			}
			else
			{
			    erreurs.put("wrongpass", "Mauvais mot de passe");
//			    System.out.println("erreurs 3 : "+erreurs.get("wrongpass"));
			    redirectTo = "ServletUsers?action=erreurConnexion";
			}
		    }
		}
		else
		{
		    if (liste.size() > 1)
		    {
			for (Utilisateur u : liste)
			{
			    if (password.length() == 0)
			    {
				erreurs.put("password", "Veuillez rentrer un mot de passe.");
				redirectTo = "ServletUsers?action=erreurConnexion";
			    }
			    else if (password.equals(u.getPassword()))
			    {
				HttpSession session1 = request.getSession();
				session1.setAttribute(ATT_SESSION_USER, u);
				redirectTo = "ServletUsers?action=userConnecte";
			    }
			    else
			    {
				erreurs.put("wrongpass", "Mauvais mot de passe");
//			    System.out.println("erreurs 3 : "+erreurs.get("wrongpass"));
				redirectTo = "ServletUsers?action=erreurConnexion";
			    }
			}
		    }
		    else
		    {
			erreurs.put("wronglogin", "Veuillez rentrer un login existant.");
			if (password.length() == 0)
			{
			    erreurs.put("password", "Veuillez rentrer un mot de passe.");
			}
			System.out.println("erreurs 2 : " + erreurs.get("wronglogin"));
			redirectTo = "ServletUsers?action=erreurConnexion";
		    }
		}
	    }
//	    gestionnaireUtilisateurs.addContactest();
	}
	else if (action.equals("Deconnexion"))
	{
	    System.out.println("déconnexion en cours...");
	    HttpSession session1 = request.getSession();
	    session1.invalidate();
	    redirectTo = "ServletUsers?action=userDeconnecte";

	}

	else if (action.equals("inscription"))
	{
	    erreurs.clear();
	    if (nom.length() != 0)
	    {
		if (prenom.length() != 0)
		{
		    if (login.length() != 0)
		    {
			if (password.length() != 0)
			{
			    if (gestionnaireUtilisateurs.getUser(login).size() != 0)
			    {
				erreurs.put("login", "Veuillez choisir un login non existant");
				redirectTo = "ServletUsers?action=erreurInscription";
			    }
			    else
			    {
				Utilisateur u = gestionnaireUtilisateurs.creeUtilisateur(nom, prenom, login, password);
				redirectTo = "ServletUsers?action=utilisateurInscrit";
			    }
			}
			else
			{
			    erreurs.put("password", "Veuillez entrer un mot de passe.");
			    redirectTo = "ServletUsers?action=erreurInscription";
			}
		    }
		    else
		    {
//			System.out.println("pas de prenom");
			erreurs.put("login", "Veuillez entrer un login.");
			if (password.length() == 0)
			{
			    erreurs.put("password", "Veuillez entrer un mot de passe.");
			}
			redirectTo = "ServletUsers?action=erreurInscription";
		    }
		}
		else
		{
//		    System.out.println("pas de login");
		    erreurs.put("prenom", "Veuillez entrer un prenom.");

		    if (login.length() == 0)
		    {
			erreurs.put("login", "Veuillez entrer un login.");
		    }
		    if (password.length() == 0)
		    {
			erreurs.put("password", "Veuillez entrer un mot de passe.");
		    }

		    redirectTo = "ServletUsers?action=erreurInscription";
		}
	    }
	    else
	    {
		erreurs.put("nom", "Veuillez entrer un nom.");

		if (prenom.length() == 0)
		{
		    erreurs.put("prenom", "Veuillez entrer un prenom.");
		}
		if (login.length() == 0)
		{
		    erreurs.put("login", "Veuillez entrer un login.");
		}
		if (password.length() == 0)
		{
		    erreurs.put("password", "Veuillez entrer un mot de passe.");
		}

		redirectTo = "ServletUsers?action=erreurInscription";

	    }

	}

// le sendRedirect génère une autre requete
	response.sendRedirect(redirectTo);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
    {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
	return "Short description";
    }// </editor-fold>
}
