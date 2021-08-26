package entity;

import entity.Artikal;
import entity.Narudzbenica;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-17T23:41:57")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> ime;
    public static volatile SingularAttribute<Users, String> prezime;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile CollectionAttribute<Users, Artikal> artikalCollection;
    public static volatile SingularAttribute<Users, String> role;
    public static volatile SingularAttribute<Users, String> phone;
    public static volatile SingularAttribute<Users, String> adresa;
    public static volatile CollectionAttribute<Users, Narudzbenica> narudzbenicaCollection;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, String> username;

}