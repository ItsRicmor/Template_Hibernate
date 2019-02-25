package main.tests;

import java.util.List;
import main.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test_Read {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Person.class)
            .buildSessionFactory();
        
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Person> persons = session.createQuery("from Person").list();
            System.out.println("Person Information");
            persons.stream().forEach((person) -> {
                System.out.println("Nombre: " + person.getName());
                System.out.println("Apellido: " + person.getLastName());
                System.out.println("Edad: " + person.getAge());
            });
            session.getTransaction().commit();
            session.close();
        }

    }
}
