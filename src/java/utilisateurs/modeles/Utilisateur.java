/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs.modeles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Divz
 */
@Entity
public class Utilisateur implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    private String login;
    private String password = "toto";
    private String picturePath;

    @OneToMany(cascade =
    {
	CascadeType.ALL
    }, fetch = FetchType.EAGER)
    private Collection<Contact> contacts;

//    @OneToMany(cascade =
//    {
//	CascadeType.PERSIST
//    }, fetch = FetchType.EAGER)
    public Collection<Contact> getContacts()
    {
	return contacts;
    }

    public void setContacts(Collection<Contact> contacts)
    {
	this.contacts = contacts;
    }

    public String getPicturePath()
    {
	return picturePath;
    }

    public void setPicturePath(String picturePath)
    {
	this.picturePath = picturePath;
    }

    public Utilisateur()
    {
    }

    public Utilisateur(final String login, final String lastname, final String firstname)
    {
	this.login = login;
	this.lastname = lastname;
	this.firstname = firstname;
	this.contacts = new ArrayList<>();
    }

    public Utilisateur(final String login, final String lastname, final String firstname, final String pass)
    {
	this.login = login;
	this.lastname = lastname;
	this.firstname = firstname;
	this.password = pass;
	this.contacts = new ArrayList<>();
    }

    public Utilisateur(final String login, final String lastname, final String firstname, final String pass, final String path)
    {
	this.login = login;
	this.lastname = lastname;
	this.firstname = firstname;
	this.password = pass;
	this.picturePath = path;
	this.contacts = new ArrayList<>();
    }

    public Utilisateur(final String login, final String lastname, final String firstname, final String pass, final String path, ArrayList<Contact> contact)
    {
	this.login = login;
	this.lastname = lastname;
	this.firstname = firstname;
	this.password = pass;
	this.picturePath = path;
	this.contacts = contact;

    }

    public String getPassword()
    {
	return password;
    }

    public void setPassword(String password)
    {
	this.password = password;
    }

    public String getFirstname()
    {
	return firstname;
    }

    public void setFirstname(String firstname)
    {
	this.firstname = firstname;
    }

    public String getLastname()
    {
	return lastname;
    }

    public String getLogin()
    {
	return login;
    }

    public void setLastname(String lastname)
    {
	this.lastname = lastname;
    }

    public void setLogin(String login)
    {
	this.login = login;
    }

    @Id
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
	if (!(object instanceof Utilisateur))
	{
	    return false;
	}
	Utilisateur other = (Utilisateur) object;
	if (this.id != other.id)
	{
	    return false;
	}
	return true;
    }

    @Override
    public String toString()
    {
	return "utilisateurs.modeles.Utilisateur[ id=" + id + " ]";
    }

}
