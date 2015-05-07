/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs.gestionnaires;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utilisateurs.modeles.Adresse;
import utilisateurs.modeles.Contact;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author Divz
 */
@Stateless
public class GestionnaireAdresses
{

    @PersistenceContext
    private EntityManager em;

    private String generateRandomNumber()
    {
	Random r = new Random();
	String randomNumber = "";

	String alphabet = "0123456789";
	for (int i = 0; i < 5; i++)
	{
	    randomNumber += alphabet.charAt(r.nextInt(alphabet.length()));
	}

	return randomNumber;
    }

    private String generateRandomString()
    {
	Random r = new Random();
	String randomString = "";

	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	for (int i = 0; i < 8; i++)
	{
	    randomString += alphabet.charAt(r.nextInt(alphabet.length()));
	}

	return randomString;
    }

    public Collection<Adresse> creerAdresseTest()
    {
	Collection<Adresse> adr = new ArrayList<>();
	adr.add(new Adresse(generateRandomString(), Integer.parseInt(generateRandomNumber()), generateRandomString()));
	adr.add(new Adresse(generateRandomString(), Integer.parseInt(generateRandomNumber()), generateRandomString()));
	return adr;
    }

    public Adresse creerAdresse(String nomRue, int cp, String nomVille)
    {
	System.out.println("creation d'un telephone");
	Adresse adr = new Adresse(nomRue, cp, nomVille);
	System.out.println("avan le em.persist(tel)");
	em.persist(adr);
	return adr;
    }

    public boolean deleteAdr(String id, Utilisateur u, Contact c)
    {
	System.out.println("dans le delete Adresse");
	try
	{
	    Collection<Adresse> adresses = c.getAdresses();
	    for (Adresse toDelete : adresses)
	    {
		if (toDelete.getId() == Integer.parseInt(id))
		{
		    adresses.remove(toDelete);
		}
		c.setAdresses(adresses);
		em.persist(c);
		System.out.println("done");
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
}
