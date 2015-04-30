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
public class Contact implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String firstname;

    private String lastname;

    private String pictureName;

    
    public Contact(){
	
    }
    
    public Contact(String firstname, String lastname, String pictureName)
    {
	this.firstname = firstname;
	this.lastname = lastname;
	this.pictureName = pictureName;
    }
    /**
     * Get the value of pictureName
     *
     * @return the value of pictureName
     */
    public String getPictureName()
    {
	return pictureName;
    }

    /**
     * Set the value of pictureName
     *
     * @param pictureName new value of pictureName
     */
    public void setPictureName(String pictureName)
    {
	this.pictureName = pictureName;
    }

    
    /**
     * Get the value of lastname
     *
     * @return the value of lastname
     */
    public String getLastname()
    {
	return lastname;
    }

    /**
     * Set the value of lastname
     *
     * @param lastname new value of lastname
     */
    public void setLastname(String lastname)
    {
	this.lastname = lastname;
    }

    /**
     * Get the value of firstname
     *
     * @return the value of firstname
     */
    public String getFirstname()
    {
	return firstname;
    }

    /**
     * Set the value of firstname
     *
     * @param firstname new value of firstname
     */
    public void setFirstname(String firstname)
    {
	this.firstname = firstname;
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
	if (!(object instanceof Contact))
	{
	    return false;
	}
	Contact other = (Contact) object;
	if (this.id != other.id)
	{
	    return false;
	}
	return true;
    }

    @Override
    public String toString()
    {
	return "utilisateurs.modeles.Contacts[ id=" + id + " ]";
    }
    
}
