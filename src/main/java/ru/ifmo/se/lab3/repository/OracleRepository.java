package ru.ifmo.se.lab3.repository;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ifmo.se.lab3.domain.Check;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.Properties;

@ManagedBean(name = "oracleRepository")
@ApplicationScoped
@Getter
@Setter
public class OracleRepository implements CheckRepository {

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
                addPackage("ru.ifmo.se.lab3.domain").buildSessionFactory();
    }

    public List<Check> getHistory() {
        return null;
    }
}
