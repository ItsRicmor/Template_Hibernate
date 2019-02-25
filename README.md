# Platilla para Hibernate 5.4.1

>Con este repositorio se busca documentar la configuración básica de Hibernate

### Pasos para usar Hibernate

**SessionFactory**

SessionFactory es una interfaz. SessionFactory se puede crear proporcionando un objeto de configuración, que contendrá todos los detalles de propiedad relacionados con la base de datos extraídos del archivo hibernate.cfg.xml o del archivo hibernate.properties. SessionFactory es una fábrica de objetos _**Session**_.

```
SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Person.class)
            .buildSessionFactory();
```
**Session**

Session se utiliza para obtener una conexión física con una base de datos. 
Está diseñado para ser instanciado cada vez que se necesita una interacción con la base de datos. 
Los objetos persistentes se **guardan** y **recuperan** a través de un objeto Session.
> Siempre hay que cerrar la session

```
try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            //Hacer algun trabajo
            session.getTransaction().commit();
            session.close();
        }
```

### CREATE - READ - UPDATE - DELETE

1. **Create**

```
try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Person person = new Person("901110534", "Ricardo", "Morataya", 19);
            session.save(person);
            session.getTransaction().commit();
            session.close();
        }
```
2. **Read**

```
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
```

3. **Update**

```
try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Person person = session.byId(Person.class).load("901110534");
            person.setName("Ricardo Jafet");
            session.getTransaction().commit();
            session.close();
        }
```

4. **Delete**

```
try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Person person = session.byId(Person.class).load("901110534");
            session.delete(person);
            session.getTransaction().commit();
            session.close();
        }
```

##Configuración

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
  <session-factory>
    <!-- JDBC Database connection settings -->
    <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <property name="connection.url">jdbc:mysql://localhost:3306/hibernate_test?useTimezone=true&amp;serverTimezone=UTC</property>
    <property name="connection.username">root</property>
    <property name="connection.password">root</property>
    <!-- JDBC connection pool settings ... using built-in test pool -->
    <property name="connection.pool_size">1</property>
    <!-- Select our SQL dialect -->
    <property name="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</property>
    <!-- Echo the SQL to stdout -->

    <property name="show_sql">true</property>
    <property name="hbm2ddl.auto">update</property>
    <!-- Set the current session context -->
    <property name="current_session_context_class">thread</property>
    <mapping class="main.models.Person" />
  </session-factory>
</hibernate-configuration>
```


