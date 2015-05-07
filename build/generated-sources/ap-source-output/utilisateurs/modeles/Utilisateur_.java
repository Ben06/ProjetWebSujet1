package utilisateurs.modeles;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import utilisateurs.modeles.Contact;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-07T20:43:07")
@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ { 

    public static volatile SingularAttribute<Utilisateur, String> picturePath;
    public static volatile SingularAttribute<Utilisateur, String> firstname;
    public static volatile SingularAttribute<Utilisateur, String> password;
    public static volatile SingularAttribute<Utilisateur, Integer> id;
    public static volatile SingularAttribute<Utilisateur, String> login;
    public static volatile CollectionAttribute<Utilisateur, Contact> contacts;
    public static volatile SingularAttribute<Utilisateur, String> lastname;

}