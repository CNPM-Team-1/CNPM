package repositories;

import entities.Roles;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RolesRepository {

    public static List<Roles> getAll() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Roles> query = session.createQuery("" +
                    "SELECT r " +
                    "FROM Roles r");
            List<Roles> result = query.getResultList();
            session.getTransaction().commit();

            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    public static Roles getByName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Roles> query = session.createQuery("" +
                "SELECT r " +
                "FROM Roles r " +
                "WHERE r.name = :name");
        query.setParameter("name", name);
        Roles result = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<Roles> getLikeName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Roles> query = session.createQuery("" +
                "SELECT r " +
                "FROM Roles r " +
                "WHERE r.name LIKE :name");
        query.setParameter("name", name);
        List<Roles> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
