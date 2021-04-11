package repositories;

import entities.Merchandise;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.Arrays;
import java.util.List;

public class MerchandiseRepository {
    public static List<Merchandise> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Merchandise> query = session.createQuery("" +
                "SELECT m " +
                "FROM Merchandise m");
        List<Merchandise> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Merchandise getByName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Merchandise> query = session.createQuery("" +
                "SELECT c " +
                "FROM Merchandise c " +
                "WHERE c.name = :name");
        query.setParameter("name", name);
        Merchandise result = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<Merchandise> getLikeNameAndBranch(String keySearch) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Merchandise> query = session.createQuery("" +
                "SELECT m " +
                "FROM Merchandise m " +
                "WHERE m.name LIKE :keySearch OR m.branch LIKE :keySearch");
        query.setParameter("keySearch", keySearch);
        List<Merchandise> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<Merchandise> getHasQuantity() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Merchandise> query = session.createQuery("" +
                "SELECT m " +
                "FROM Merchandise m " +
                "WHERE m.quantity > 0");
        List<Merchandise> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Merchandise getById(String id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Merchandise> query = session.createQuery("" +
                "SELECT m " +
                "FROM Merchandise m " +
                "WHERE m.id = :id");
        query.setParameter("id", id);
        Merchandise result = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<String> getAllMerchandiseTypes() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<String> query = session.createQuery("" +
                "SELECT DISTINCT c.type " +
                "FROM Merchandise c");
        List<String> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
