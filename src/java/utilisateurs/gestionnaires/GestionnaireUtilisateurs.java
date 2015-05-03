package utilisateurs.gestionnaires;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utilisateurs.modeles.Contact;
import utilisateurs.modeles.Utilisateur;

@Stateless
public class GestionnaireUtilisateurs
{

    // Ici injection de code : on n'initialise pas. L'entity manager sera créé  
    // à partir du contenu de persistence.xml  
    @PersistenceContext
    private EntityManager em;

    public void creerUtilisateursDeTest()
    {
	creeUtilisateur("John", "Lennon", "jlennon");
	creeUtilisateur("Paul", "Mac Cartney", "pmc");
	creeUtilisateur("Ringo", "Starr", "rstarr");
	creeUtilisateur("Georges", "Harisson", "georgesH");
	creeUtilisateur("Michael", "Jackson", "mjackson");
	creeUtilisateur("Elvis", "Presley", "epresley");
	creeUtilisateur("Joe", "Satriani", "jsatriani");
	creeUtilisateur("Eric", "Clapton", "eclapton");
	creeUtilisateur("Jimi", "Hendrix", "jhendrix");
	creeUtilisateur("Steve", "Vai", "svai");
	creeUtilisateur("George", "Washington", "gwashington");
	creeUtilisateur("King", "Kong", "kkong");
	creeUtilisateur("John", "Petrucci", "jpetrucci");
	creeUtilisateur("Marilyn", "Monroe", "mmonroe");
	creeUtilisateur("Pac", "Man", "pman");
	creeUtilisateur("Mario", "Bros", "mbros");
	creeUtilisateur("Luigi", "Bros", "lbros");
	creeUtilisateur("Donkey", "Kong", "dkong");
	creeUtilisateur("Oui", "Non", "onon");
	creeUtilisateur("Bob", "Marley", "bmarley");
	creeUtilisateur("Angus", "Young", "ayoung");
	creeUtilisateur("Tupac", "Shakur", "tshakur");
	creeUtilisateur("Snoop", "Dog", "sdog");
	creeUtilisateur("Biggy", "Small", "bsmall");
	creeUtilisateur("Ousama", "Benladen", "obenladen");
	creeUtilisateur("Rania", "Azizi", "razizi");
	creeUtilisateur("Steve", "Morrison", "smorrison");
	creeUtilisateur("John", "Connor", "jconnor");
	creeUtilisateur("Sarah", "Connor", "sconnor");
	creeUtilisateur("Bruce", "Willis", "bwillis");
	creeUtilisateur("Caron", "Anthony", "acaron");
	creeUtilisateur("Reale", "Benjamin", "breale");
    }

    public Utilisateur creeUtilisateur(String prenom, String nom, String login)
    {
	Utilisateur u = new Utilisateur(login, nom, prenom);
//	u.setPassword(login);
	em.persist(u);
	return u;
    }

    public Utilisateur creeUtilisateur(String prenom, String nom, String login, String password)
    {
	System.out.println("Création de l'utilisateur");
	System.out.println("prenom : " + prenom + " nom :" + nom + " login : " + login + " password : " + password);
	Utilisateur u = new Utilisateur(login, nom, prenom, password);
	System.out.println("utilisateur crée u : " + u.getLogin());
	System.out.println("user null ? : " + u.getPassword());
	em.persist(u);
	return u;
    }

    public Utilisateur creeUtilisateur(String prenom, String nom, String login, String password, String path)
    {
	System.out.println("Création de l'utilisateur");
	System.out.println("prenom : " + prenom + " nom :" + nom + " login : " + login + " password : " + password);
	Utilisateur u = new Utilisateur(login, nom, prenom, password, path);
	System.out.println("utilisateur crée u : " + u.getLogin());
	System.out.println("user null ? : " + u.getPassword());
	em.persist(u);
	return u;
    }

    public Utilisateur creerUtilisateur(String prenom, String nom, String login, String password, String path, Collection<Contact> contacts)
    {
	System.out.println("Création de l'utilisateur");
	System.out.println("prenom : " + prenom + " nom :" + nom + " login : " + login + " password : " + password);
	Utilisateur u = new Utilisateur(login, nom, prenom, password, path);
	u.setContacts(contacts);
	System.out.println("utilisateur crée u : " + u.getLogin());
	System.out.println("user null ? : " + u.getPassword());
	em.persist(u);
	return u;
    }

//    public boolean isUser(String login, String password)
//    {
//	Utilisateur u = getSingleUserByLogin(login);
//	
//    }
    public Collection<Utilisateur> getAllUsers()
    {
	System.out.println("dans le get All Users");
	// Exécution d'une requête équivalente à un select *  
	Query q = em.createQuery("select u from Utilisateur u");
	return q.getResultList();
    }

    public void deleteAllUser()
    {
	Collection<Utilisateur> liste = getAllUsers();
	for (Utilisateur u : liste)
	{
	    deleteUser(u.getLogin());
	}
	System.out.println("tous les utilisateurs sont supprimés!");
    }

    public Collection<Utilisateur> getTenUsers(int pageNumber)
    {
	// Exécution d'une requête équivalente à un select *  
	System.out.println("numero de page : " + pageNumber);
	Query q = em.createQuery("select u from Utilisateur u");
//	int pageNumberInt = Integer.parseInt(pageNumber);
	Collection<Utilisateur> liste = q.getResultList();
	Collection<Utilisateur> liste10 = new ArrayList<Utilisateur>();
	int i = 0;
	for (Utilisateur u : liste)
	{
	    System.out.println("i : " + i);
	    if (i >= pageNumber * 10 && i < (pageNumber + 1) * 10)
	    {
		System.out.println("u : " + u.getLogin() + " photo :" + u.getPicturePath());
		liste10.add(u);
	    }
	    i++;
	}
	return liste10;
    }

    public Collection<Utilisateur> getUser(String login)
    {
//        System.out.println("login : "+login);
	Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
	q.setParameter("login", login);
	Collection<Utilisateur> result = q.getResultList();
//        System.out.println("taille result : "+result.size());
//        for (Utilisateur u : result)
//        {
//            System.out.println("result : nom : "+u.getFirstname()+" prenom : "+u.getLastname()+" login : "+u.getLogin());
//        }
	return q.getResultList();
    }

//    public void updateUser(String login, String nom, String prenom){
//        String hqlUpdate ="update Utilisateur u set u.lastname = :prenom where u.login = :login";
//        Query q = em.createQuery(hqlUpdate).setString("prenom", prenom).setString("login", login ).executeUpdate();
//    }
    public boolean updateUser(String login, String newNom, String newPrenom)
    {

	Utilisateur u;

	if (login != "")
	{
	    u = getSingleUserByLogin(login);
	}
	else
	{
	    return false;
	}
	// Ici on est après une requête, em existe, l'objet est donc connecté
	// on peut modifier ses attributs, ça changera en base
	if (newNom != "" && newPrenom != "")
	{
	    u.setLastname(newNom);
	    u.setFirstname(newPrenom);
	    return true;
	}
	else
	{
	    return false;
	}
    }
    /*
     }
     Query q = em.createQuery("update from Utilisateur u set u.firstname =:newNom where u.login=:login");
     q.setParameter("newNom", newNom);
     q.setParameter("login", login);
     int result = q.executeUpdate();
     return result;
     */

    public Utilisateur getSingleUserByLogin(String login)
    {
	System.out.println("dans le getsingleuser");
	// Sur un objet on ne fait pas de requête pour ça !!!
	Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
	q.setParameter("login", login);
	Utilisateur u = (Utilisateur) q.getSingleResult();
	if (u != null)
	{
	    return u;
	}
	return null;
    }

    public boolean deleteUser(String login)
    {
	// Pour fair un remove l'objet doit être connecté
	try
	{
	    em.remove(getSingleUserByLogin(login));
	    return true;
	}
	catch (IllegalArgumentException e)
	{
	    return false;
	}
    }
    
    public void addContact(Utilisateur u, Contact contact)
    {
	System.out.println("ajout de contacts");
//	Contact c = new Contact("jean", "merde");
//	contact.setUtilisateur(u);
	u.getContacts().add(contact);
//	contacts.add(c);
//	u.setContacts(contacts);
//	deleteUser(u.getLogin());
//	em.persist(contact);
	
	em.persist(u);
	System.out.println("contact ajouté!");

    }

//    public void addContactest()
//    {
////	Utilisateur u = creeUtilisateur("Reale", "Benjamin", "breale");
//	
//	System.out.println("ajout de contacts");
////	Contact c = new Contact("jean", "merde");
//	Collection<Contact> contacts = new ArrayList<Contact>();
//
//	if (u.getContacts() != null)
//	{
//	    System.out.println("contacts null");
//	    contacts = u.getContacts();
//	}
//	
//	contacts.add(c);
//	u.setContacts(contacts);
//
//	System.out.println("contact ajouté!");
//
//	em.persist(u);
//    }
}
