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
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String NumeroNomDeRue;

    private String CodePostal;

    private String Ville;

    private String Pays;

    
    public Adresse()
    {
	
    }
    
    public Adresse(String numero, String cp, String ville, String pays)
    {
	this.NumeroNomDeRue = numero;
	this.CodePostal = cp;
	this.Ville = ville;
	this.Pays = pays;
    }
    /**
     * Get the value of Pays
     *
     * @return the value of Pays
     */
    public String getPays()
    {
	return Pays;
    }

    /**
     * Set the value of Pays
     *
     * @param Pays new value of Pays
     */
    public void setPays(String Pays)
    {
	this.Pays = Pays;
    }

    /**
     * Get the value of Ville
     *
     * @return the value of Ville
     */
    public String getVille()
    {
	return Ville;
    }

    /**
     * Set the value of Ville
     *
     * @param Ville new value of Ville
     */
    public void setVille(String Ville)
    {
	this.Ville = Ville;
    }

    /**
     * Get the value of CodePostal
     *
     * @return the value of CodePostal
     */
    public String getCodePostal()
    {
	return CodePostal;
    }

    /**
     * Set the value of CodePostal
     *
     * @param CodePostal new value of CodePostal
     */
    public void setCodePostal(String CodePostal)
    {
	this.CodePostal = CodePostal;
    }

    /**
     * Get the value of NumeroNomDeRue
     *
     * @return the value of NumeroNomDeRue
     */
    public String getNumeroNomDeRue()
    {
	return NumeroNomDeRue;
    }

    /**
     * Set the value of NumeroNomDeRue
     *
     * @param NumeroNomDeRue new value of NumeroNomDeRue
     */
    public void setNumeroNomDeRue(String NumeroNomDeRue)
    {
	this.NumeroNomDeRue = NumeroNomDeRue;
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
