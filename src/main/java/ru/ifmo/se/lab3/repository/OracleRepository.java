package ru.ifmo.se.lab3.repository;

import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.ifmo.se.lab3.domain.Check;
import ru.ifmo.se.lab3.util.HibernateUtil;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

@ManagedBean(name = "oracleRepository", eager = true)
@ApplicationScoped
@Log
public class OracleRepository implements CheckRepository {

    private final static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Check> getAllResults() {
        List<Check> results = new LinkedList<>();
        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from Check");
            results = (List<Check>) query.list();
        }
        catch (Exception e){
            log.log(Level.SEVERE, "OracleRepository-getAll", e);
        }
        return results;
    }

    public boolean save(Check check) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(check);
            transaction.commit();
        }
        catch (Exception e) {
            if(transaction != null)
                transaction.rollback();

            log.log(Level.SEVERE, "OracleRepository-save", e);
            return false;
        }
        finally {
            session.close();
        }

        return true;
    }
}
