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
import utilisateurs.modeles.Contact;
import utilisateurs.modeles.Telephone;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author Divz
 */
@Stateless
public class GestionnaireTelephones
{

    @PersistenceContext
    private EntityManager em;

    private String generateRandomNumber()
    {
	Random r = new Random();
	String randomNumber = "";

	String alphabet = "0123456789";
	for (int i = 0; i < 10; i++)
	{
	    randomNumber += alphabet.charAt(r.nextInt(alphabet.length()));
	}

	return randomNumber;
    }

    public Collection<Telephone> creerTelephonesTest()
    {
	Collection<Telephone> tels = new ArrayList<>();
	tels.add(new Telephone(generateRandomNumber()));
	tels.add(new Telephone(generateRandomNumber()));
	return tels;
    }

    public Telephone creerTelephone(String numero)
    {
	System.out.println("creation d'un telephone");
	Telephone tel = new Telephone(numero);
	System.out.println("avan le em.persist(tel)");
	em.persist(tel);
	return tel;
    }

    public boolean deletePhone(String id, Utilisateur u, Contact c)
    {
	System.out.println(
		"dans le delete Phone");
	try
	{
	    Collection<Telephone> tels = c.getPhone();
	    for (Telephone toDelete : tels)
	    {
		if (toDelete.getId() == Integer.parseInt(id))
		{
		    tels.remove(toDelete);
		}
		c.setPhone(tels);
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
