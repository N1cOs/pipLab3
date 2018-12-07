package ru.ifmo.se.lab3.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.ifmo.se.lab3.domain.Check;
import ru.ifmo.se.lab3.util.HibernateUtil;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "oracleRepository", eager = true)
@ApplicationScoped
public class OracleRepository implements CheckRepository {

    private final static SessionFactory sessionFactory;

    public List<Check> getAllResults() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Check");
        return (List<Check>) query.list();
    }

    static {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public boolean save(Check check) {
        check.setDate(new Date());

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(check);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            return false;
        }
        return true;
    }
}
