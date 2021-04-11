package repositories;

import entities.Imports;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.Arrays;
import java.util.List;

public class ImportsRepository {

    public static List<Imports> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Imports> query = session.createQuery("" +
                "SELECT i " +
                "FROM Imports i");
        List<Imports> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Imports getById(String id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Imports> query = session.createQuery("" +
                "SELECT i " +
                "FROM Imports i " +
                "WHERE i.id = :id");
        query.setParameter("id", id);
        Imports result = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<Imports> getByOrdersId(String ordersId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Imports> query = session.createQuery("" +
                "SELECT i " +
                "FROM Imports i " +
                "WHERE i.orders.id = :id");
        query.setParameter("id", ordersId);
        List<Imports> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<Imports> getLikeCustomerName(String customerName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Imports> query = session.createQuery("" +
                "SELECT c " +
                "FROM Imports c " +
                "WHERE c.orders.customer.fullName LIKE :customerName");
        query.setParameter("customerName", customerName);
        List<Imports> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

//    session.beginTransaction();
//    Query query = session.createQuery("" +
//            "SELECT i.id, i.imports.id, i.merchandise.id, sum(i.quantity) as quantity, sum(i.amount) as amount " +
//            "FROM ImportsDetail i " +
//            "WHERE i.imports.id IN (?1) " +
//            "GROUP BY i.merchandise.id");
//            query.setParameterList(1, importsId);
//    List<Object> queryResult = query.getResultList();
//    List<ImportsDetail> result = new ArrayList<>();
//            for (Object c : queryResult) {
//        result.add(new ImportsDetail(c));
//    }
//            session.getTransaction().commit();
//            return result;
}
