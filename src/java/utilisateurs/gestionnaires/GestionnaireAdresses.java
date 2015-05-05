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
import utilisateurs.modeles.Adresse;
import utilisateurs.modeles.Contact;
import utilisateurs.modeles.Telephone;
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

    public Adresse creerTelephone(String nomRue, int cp, String nomVille)
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
	    for(Adresse toDelete : adresses)
	    {
		if(toDelete.getId() == Integer.parseInt(id))
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
