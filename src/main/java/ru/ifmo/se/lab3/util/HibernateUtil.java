package ru.ifmo.se.lab3.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ifmo.se.lab3.domain.Check;

import java.util.Properties;

public class HibernateUtil {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orbis";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String USER = "s243856";
    private static final String PASSWORD = "";
    private static final SessionFactory sessionFactory;

    static {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", URL);
        properties.setProperty("hibernate.connection.driver_class", DRIVER);
        properties.setProperty("hibernate.connection.username", USER);
        properties.setProperty("hibernate.connection.password", PASSWORD);

        sessionFactory = new Configuration().addProperties(properties).
                addAnnotatedClass(Check.class).buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
