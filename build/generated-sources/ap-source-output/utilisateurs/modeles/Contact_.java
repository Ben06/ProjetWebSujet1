package utilisateurs.modeles;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import utilisateurs.modeles.Telephone;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-05T14:54:50")
@StaticMetamodel(Contact.class)
public class Contact_ { 

    public static volatile SingularAttribute<Contact, String> firstname;
    public static volatile CollectionAttribute<Contact, Telephone> numeros;
    public static volatile SingularAttribute<Contact, Integer> id;
    public static volatile SingularAttribute<Contact, String> pictureName;
    public static volatile SingularAttribute<Contact, String> lastname;

}