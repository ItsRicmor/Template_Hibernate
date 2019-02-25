package main.tests;

import java.util.List;
import main.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test_Update {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Person person = session.byId(Person.class).load("901110534");
            person.setName("Ricardo Jafet");
            session.getTransaction().commit();
            session.close();
        }
    }
}
