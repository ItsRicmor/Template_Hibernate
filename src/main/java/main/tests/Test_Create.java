package main.tests;

import main.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test_Create {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Person.class)
            .buildSessionFactory();
        
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Person person = new Person("901110534", "Ricardo", "Morataya", 19);
            session.save(person);
            session.getTransaction().commit();
        }
    }
}
