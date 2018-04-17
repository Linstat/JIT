package ru.sivak.addressbookWebTests.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author p.sivak.
 * @since 13.04.2018.
 */
public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<NewGroupParameters> result = session.createQuery("from NewGroupParameters").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<NewContactParameters> result = session.createQuery("from NewContactParameters where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public Groups contactInGroups(NewContactParameters contact){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List <NewContactParameters> result = session.createQuery("from NewContactParameters where deprecated = '0000-00-00' and id = '"+contact.getId()+"'").list();
        session.getTransaction().commit();
        session.close();
        NewContactParameters selectedContact = result.get(0);
        return selectedContact.getGroups();
    }

    public NewContactParameters getContactById (NewContactParameters contact){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List <NewContactParameters> result = session.createQuery("from NewContactParameters where deprecated = '0000-00-00' and id = '"+contact.getId()+"'").list();
        session.getTransaction().commit();
        session.close();
        NewContactParameters selectedContact = result.get(0);
        return selectedContact;
    }
}
