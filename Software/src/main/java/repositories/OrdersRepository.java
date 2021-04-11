package repositories;

import entities.Customer;
import entities.Orders;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersRepository {

    public static List<Orders> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Orders> query = session.createQuery("" +
                "SELECT o " +
                "FROM Orders o");
        List<Orders> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<Orders> getByCustomerId(Session session, String keySearch) {
        try {
            session.beginTransaction();
            String sql = "Select o from " + Orders.class.getName() + " o where o.customerId like '%" + keySearch + "%'";
            Query<Orders> query = session.createQuery(sql);
            List<Orders> result = query.list();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<Orders> getLikeCustomerName(String customerName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Orders> query = session.createQuery("" +
                "SELECT o " +
                "FROM Orders o " +
                "WHERE o.customer.fullName LIKE :customerName");
        query.setParameter("customerName", customerName);
        List<Orders> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<Orders> getByCustomerName(String customerName) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Orders> query = session.createQuery("" +
                    "SELECT o " +
                    "FROM Orders o " +
                    "WHERE o.customer.fullName = :customerName");
            query.setParameter("customerName", customerName);
            List<Orders> result = query.getResultList();
            session.getTransaction().commit();

            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static List<Orders> getActiveByCustomerName(String customerName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Orders> query = session.createQuery("" +
                "SELECT o " +
                "FROM Orders o " +
                "WHERE o.customer.fullName = :customerName AND o.status = 'Chưa hoàn tất'");
        query.setParameter("customerName", customerName);
        List<Orders> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Orders getById(String id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Orders> query = session.createQuery("" +
                "SELECT o " +
                "FROM Orders o " +
                "WHERE o.id = :id");
        query.setParameter("id", id);
        Orders result = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
