/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs.modeles;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Divz
 */
@Entity
public class Adresse implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nomRue;

    private int codePostal;

    private String nomVille;

    public Adresse()
    {

    }

    public Adresse(String nomRue, int codePostal, String nomVille)
    {
	this.nomRue = nomRue;
	this.codePostal = codePostal;
	this.nomVille = nomVille;
    }

    /**
     * Get the value of nomVille
     *
     * @return the value of nomVille
     */
    public String getNomVille()
    {
	return nomVille;
    }

    /**
     * Set the value of nomVille
     *
     * @param nomVille new value of nomVille
     */
    public void setNomVille(String nomVille)
    {
	this.nomVille = nomVille;
    }

    /**
     * Get the value of codePostal
     *
     * @return the value of codePostal
     */
    public int getCodePostal()
    {
	return codePostal;
    }

    /**
     * Set the value of codePostal
     *
     * @param codePostal new value of codePostal
     */
    public void setCodePostal(int codePostal)
    {
	this.codePostal = codePostal;
    }

    /**
     * Get the value of nomRue
     *
     * @return the value of nomRue
     */
    public String getNomRue()
    {
	return nomRue;
    }

    /**
     * Set the value of nomRue
     *
     * @param nomRue new value of nomRue
     */
    public void setNomRue(String nomRue)
    {
	this.nomRue = nomRue;
    }


    public int getId()
    {
	return id;
    }

    public void setId(int id)
    {
	this.id = id;
    }

    @Override
    public int hashCode()
    {
	int hash = 0;
	hash += (int) id;
	return hash;
    }

    @Override
    public boolean equals(Object object)
    {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Adresse))
	{
	    return false;
	}
	Adresse other = (Adresse) object;
	if (this.id != other.id)
	{
	    return false;
	}
	return true;
    }

    @Override
    public String toString()
    {
	return "utilisateurs.modeles.Adresse[ id=" + id + " ]";
    }

}
