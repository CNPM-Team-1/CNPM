package repositories;

import entities.Receipt;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.Arrays;
import java.util.List;

public class ReceiptRepository {
    public static List<Receipt> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Receipt> query = session.createQuery("" +
                "SELECT r " +
                "FROM Receipt r");
        List<Receipt> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<Receipt> getLikeCustomerName(String customerName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Receipt> query = session.createQuery("" +
                "SELECT r " +
                "FROM Receipt r " +
                "WHERE r.orders.customer.fullName LIKE :customerName");
        query.setParameter("customerName", "%" + customerName + "%");
        List<Receipt> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
