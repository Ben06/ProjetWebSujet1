/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs.gestionnaires;

import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utilisateurs.modeles.Adresse;
import utilisateurs.modeles.Contact;
import utilisateurs.modeles.Telephone;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author Divz
 */
@Stateless
public class GestionnaireContacts
{

    @PersistenceContext
    private EntityManager em;

    public Collection<Contact> creerContactDeTest()
    {
	System.out.println("creation des contacts de test");
	Collection<Contact> contacts = new ArrayList<>();

	contacts.add(creerContact("John", "Lennon"));
	contacts.add(creerContact("John1", "Lennon"));
	contacts.add(creerContact("John2", "Lennon"));
	contacts.add(creerContact("John3", "Lennon"));
	contacts.add(creerContact("John4", "Lennon"));
	contacts.add(creerContact("John5", "Lennon"));
	contacts.add(creerContact("John6", "Lennon"));
	contacts.add(creerContact("John7", "Lennon"));
	contacts.add(creerContact("John8", "Lennon"));
	contacts.add(creerContact("John9", "Lennon"));
	contacts.add(creerContact("John10", "Lennon"));
	contacts.add(creerContact("John11", "Lennon"));
	contacts.add(creerContact("John112", "Lennon"));
	contacts.add(creerContact("John13", "Lennon"));
	contacts.add(creerContact("John15", "Lennon"));
	contacts.add(creerContact("John1516", "Lennon"));
	contacts.add(creerContact("John151", "Lennon"));
	return contacts;
    }

    public Contact creerContact(String nom, String prenom, String pictureName)
    {
	System.out.println("creation d'un contact");
	Contact c = new Contact(nom, prenom, pictureName);
	System.out.println("avan le em.persist(c)");
	em.persist(c);
	return c;
    }

    public Contact creerContact(String nom, String prenom)
    {
	System.out.println("creation d'un contact");
	Contact c = new Contact(nom, prenom);
	System.out.println("avan le em.persist(c)");
	em.persist(c);
	return c;
    }

    public Collection<Contact> getAllContact(Utilisateur u)
    {
	System.out.println("dans le get All contact");
	// Exécution d'une requête équivalente à un select *  
//	Query q = em.createQuery("select c from Contact c");
//	for (Object o : q.getResultList())
//	{
//	    Contact c = (Contact) o;
//	    System.out.println("contact : nom :" + c.getFirstname());
//	}
//	return q.getResultList();
	return u.getContacts();
    }

    public Collection<Contact> getTenContacts(int pageNumber, Utilisateur u)
    {
	// Exécution d'une requête équivalente à un select *  
	int id = u.getId();
	System.out.println("numero de page : " + pageNumber);
//	Query q = em.createQuery("SELECT c FROM Contact c, Utilisateur utilisateur WHERE utilisateur.id=:id");
//	q.setParameter("id", id);
//	int pageNumberInt = Integer.parseInt(pageNumber);
	Collection<Contact> liste = getAllContact(u);
	Collection<Contact> liste10 = new ArrayList<Contact>();
	int i = 0;
	for (Contact c : liste)
	{
	    System.out.println("i : " + i);
	    if (i >= pageNumber * 10 && i < (pageNumber + 1) * 10)
	    {
		System.out.println("c : " + c.getFirstname() + " photo du contact :" + c.getPictureName());
		liste10.add(c);
	    }
	    i++;
	}
	return liste10;
    }

    public Collection<Contact> getContact(Utilisateur u, String toSearch, String option)
    {
//        System.out.println("login : "+login);
	Collection<Contact> contacts = getAllContact(u);
	Collection<Contact> result = new ArrayList<Contact>();
	for (Contact c : contacts)
	{
	    if (option.equals("nom"))
	    {
		if (c.getFirstname().equals(toSearch))
		{
		    System.out.println("contact trouvé ! ajout du contact au résultat");
		    result.add(c);
		}
	    }
	    else if (option.equals("prenom"))
	    {
		if (c.getLastname().equals(toSearch))
		{
		    System.out.println("contact trouvé ! ajout du contact au résultat");
		    result.add(c);
		}
	    }
	    else if (option.equals("nomRue"))
	    {
		Collection<Adresse> adresses = c.getAdresses();
		for (Adresse adr : adresses)
		{
		    if (adr.getNomRue().equals(toSearch))
		    {
			result.add(c);
		    }
		}
	    }
	    else if (option.equals("codePostal"))
	    {
		Collection<Adresse> adresses = c.getAdresses();
		for (Adresse adr : adresses)
		{
		    if (adr.getCodePostal() == (Integer.parseInt(toSearch)))
		    {
			result.add(c);
		    }
		}
	    }
	    else if (option.equals("nomVille"))
	    {
		Collection<Adresse> adresses = c.getAdresses();
		for (Adresse adr : adresses)
		{
		    if (adr.getNomVille().equals(toSearch))
		    {
			result.add(c);
		    }
		}
	    }
	    else if (option.equals("numero"))
	    {
		Collection<Telephone> tels = c.getPhone();
		for (Telephone tel : tels)
		{
		    if (tel.getNumero().equals(toSearch))
		    {
			result.add(c);
		    }
		}
	    }
	}

	return result;

//	Query q = em.createQuery("select c from Contact c where c.firstname=:firstname");
//	q.setParameter("firstname", firstname);
//	Collection<Contact> result = q.getResultList();
//        System.out.println("taille result : "+result.size());
//        for (Utilisateur u : result)
//        {
//            System.out.println("result : nom : "+u.getFirstname()+" prenom : "+u.getLastname()+" login : "+u.getLogin());
//        }
    }

    /**
     *
     * @param u
     * @param stringId
     * @return
     */
    public Collection<Contact> getContactByID(Utilisateur u, String stringId)
    {
	if (stringId != "")
	{
	    System.out.println("getContactById");
	    int id = Integer.parseInt(stringId);
	    Collection<Contact> contacts = getAllContact(u);
	    Collection<Contact> result = new ArrayList<Contact>();
	    for (Contact c : contacts)
	    {
		if (c.getId() == id)
		{
		    System.out.println("contact trouvé ! ajout du contact au résultat");
		    result.add(c);
		}
	    }

	    return result;
	}
	else
	{
	    return null;
	}
    }

    public Contact getSingleContactByID(Utilisateur u, String stringId)
    {
	System.out.println("getContactById id : " + stringId);
	int id = Integer.parseInt(stringId);
	Contact c = null;
	Collection<Contact> contacts = getAllContact(u);
	Collection<Contact> result = new ArrayList<Contact>();
	for (Contact contact : contacts)
	{
	    if (c.getId() == id)
	    {
		System.out.println("contact trouvé ! ajout du contact au résultat");
		c = contact;
	    }
	}
	return c;
    }

    public void ajouterPhoto(Utilisateur u, String id, String name)
    {
	System.out.println("ajout d'une photo pour le contact d'id : " + id);
	Contact c = null;

	if (id != "")
	{
	    Collection<Contact> contacts = getContactByID(u, id);
	    for (Contact contact : contacts)
	    {
		c = contact;
	    }
	}
	if (name != "" && c != null)
	{
	    System.out.println("contact trouvé : " + c.getFirstname());
	    c.setPictureName(name);
	    em.persist(u);
	}
    }

    public boolean deleteContact(Utilisateur u, String id)
    {
	System.out.println("dans le delete Contact");
	try
	{
	    Collection<Contact> contacts = getContactByID(u, id);
	    Collection<Contact> liste = getAllContact(u);
	    for (Contact c : contacts)
	    {
		System.out.println("Suppression du contact : " + c.getFirstname());

		for (Contact toDelete : liste)
		{
		    if (toDelete.getId() == c.getId())
		    {
			liste.remove(toDelete);
		    }
		}
		u.setContacts(liste);
		em.persist(u);
		System.out.println("done...");
	    }
	    return true;
	}
	catch (IllegalArgumentException e)
	{
	    return false;
	}
	catch (java.util.ConcurrentModificationException e1)
	{
	    return true;
	}
    }

    public boolean updateContact(Utilisateur u, String id, String newNom, String newPrenom, String adresseId, String phoneId, String nomRue, String nomVille, String codePostal, String numero)
    {

	Contact c;

	if (id != "" || id != null)
	{
	    System.out.println("recherche du contact à update");

	    Collection<Contact> contacts = getContactByID(u, id);
	    for (Contact contact : contacts)
	    {
		System.out.println("trouvé!");
		c = contact;
		if (newNom.length()!=0)
		{
		    c.setFirstname(newNom);
		}
		if(newPrenom.length()!=0)
		{
		    c.setLastname(newPrenom);
		}
		if (adresseId != "" || adresseId != null)
		{
//		    System.out.println("modification de l'adresse nouvelle adresse : "+);
		    Collection<Adresse> adresses = contact.getAdresses();
		    for (Adresse adr : adresses)
		    {
			if (adr.getId() == Integer.parseInt(adresseId))
			{
			    if (nomRue != "" || nomRue.length()!=0)
			    {
				System.out.println("nouveau nom de rue : "+nomRue);
				adr.setNomRue(nomRue);
			    }

			    if (codePostal != "" || codePostal.length()!=0)
			    {
				System.out.println("nouveau cp : "+codePostal);
				adr.setCodePostal(Integer.parseInt(codePostal));
			    }

			    if (nomVille != "" || nomVille.length()!=0)
			    {
				System.out.println("nouveau nom de ville : "+nomVille);
				adr.setNomVille(nomVille);
			    }
			}
			c.setAdresses(adresses);
		    }
		    System.out.println("done");
		}
		if (phoneId != "" || phoneId != null)
		{
		    System.out.println("modification du telephone");
		    Collection<Telephone> tels = contact.getPhone();
		    for (Telephone tel : tels)
		    {
			if (tel.getId() == Integer.parseInt(phoneId))
			{
			    if (numero != "" || numero.length()!=0)
			    {
				tel.setNumero(numero);
			    }
			}
			c.setPhone(tels);
		    }
		    System.out.println("done");
		}
		em.persist(u);
		System.out.println("modification terminée");
		return true;

	    }

	}
	return false;
    }

//    public boolean updateContact(Utilisateur u, String id, Telephone t)
//    {
//
//	Contact c;
//
//	if (id != "")
//	{
//	    System.out.println("recherche du contact à update");
//
//	    Collection<Contact> contacts = getContactByID(u, id);
//	    for (Contact contact : contacts)
//	    {
//		System.out.println("trouvé!");
//		c = contact;
//		if (t.getNumero() != "")
//		{
//		    c.modifierNumero(t);
//		    System.out.println("done!");
//		    em.persist(u);
//		    return true;
//		}
//	    }
//	}
//	else
//	{
//	    return false;
//	}
//	return false;
//    }
//    public void modifierNumero(Utilisateur u, Contact c, Telephone t)
//    {
//	Collection<Telephone> tel
//    }
    public void ajouterNumero(Utilisateur u, Contact c, Telephone numero)
    {
	System.out.println("ajout du numéro au contact");
	c.getPhone().add(numero);
	em.persist(c);
	System.out.println("numéro ajouté! ");
//	for (Telephone tel : c.getPhone())
//	{
//	    
//	    System.out.println("tel : "+tel.getNumero());
//	    
//	}

    }

    public void ajouterAdresse(Utilisateur u, Contact c, Adresse adr)
    {
	System.out.println("ajout d'une adresse au contact");
	c.getAdresses().add(adr);
	em.persist(c);
	System.out.println("adresse ajoutée! ");
//	for (Telephone tel : c.getPhone())
//	{
//	    
//	    System.out.println("tel : "+tel.getNumero());
//	    
//	}

    }

}
