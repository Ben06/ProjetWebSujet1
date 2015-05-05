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
public class Telephone implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String numero;

    
    public Telephone(){
	
    }
    
    public Telephone(String numero)
    {
	this.numero = numero;
    }
    /**
     * Get the value of numero
     *
     * @return the value of numero
     */
    public String getNumero()
    {
	return numero;
    }

    /**
     * Set the value of numero
     *
     * @param numero new value of numero
     */
    public void setNumero(String numero)
    {
	this.numero = numero;
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
	if (!(object instanceof Telephone))
	{
	    return false;
	}
	Telephone other = (Telephone) object;
	if (this.id != other.id)
	{
	    return false;
	}
	return true;
    }

    @Override
    public String toString()
    {
	return "utilisateurs.modeles.Telephone[ id=" + id + " ]";
    }
    
}
