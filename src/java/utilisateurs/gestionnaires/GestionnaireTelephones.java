/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs.gestionnaires;

import java.util.Collection;
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
	    for(Telephone toDelete : tels)
	    {
		if(toDelete.getId() == Integer.parseInt(id))
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
