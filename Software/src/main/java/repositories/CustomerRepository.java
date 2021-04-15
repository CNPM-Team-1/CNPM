package repositories;

import entities.Customer;
import entities.Orders;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.Arrays;
import java.util.List;

public class CustomerRepository {

    public static List<Customer> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Query<Customer> query = session.createQuery("" +
                "SELECT c " +
                "FROM Customer c");
        List<Customer> result = query.getResultList();
        session.getTransaction().commit();

        session.close();
        return result;
    }

    public static List<Customer> getBuyingCustomer() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Query<Customer> query = session.createQuery("" +
                "SELECT c " +
                "FROM Customer c " +
                "WHERE c.type = 'Khách hàng'");
        List<Customer> result = query.getResultList();
        session.getTransaction().commit();

        session.close();
        return result;
    }

    public static List<Customer> getByNameOrPhone(String keySearch) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Query<Customer> query = session.createQuery("" +
                "SELECT c " +
                "FROM Customer c " +
                "WHERE c.fullName LIKE :keySearch OR c.phone LIKE :keySearch");
        query.setParameter("keySearch", keySearch);
        List<Customer> result = query.getResultList();
        session.getTransaction().commit();

        session.close();
        return result;
    }

    public static Customer getByName(String name) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Customer> query = session.createQuery("" +
                    "SELECT c " +
                    "FROM Customer c " +
                    "WHERE c.fullName = :name");
            query.setParameter("name", name);
            Customer result = query.uniqueResult();

            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Customer getByPhone(String phone) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Customer> query = session.createQuery("" +
                    "SELECT c " +
                    "FROM Customer c " +
                    "WHERE c.phone = :phone");
            query.setParameter("phone", phone);
            Customer result = query.uniqueResult();

            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static Customer getByEmail(String email) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Customer> query = session.createQuery("" +
                    "SELECT c " +
                    "FROM Customer c " +
                    "WHERE c.email = :email");
            query.setParameter("email", email);
            Customer result = query.uniqueResult();

            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static Customer getPhoneCustomer(Session session, String fullName) {
        try {
            session.beginTransaction();
            String sql = "Select c.phone from " + Customer.class.getName() + " c where c.fullName = '" + fullName + "'";
            Query<Customer> query = session.createQuery(sql);
            Customer result = query.getSingleResult();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<Customer> getLikeName(Session session, String name) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Customer.class.getName() + " c where c.fullName like '%" + name + "%'";
            Query<Customer> query = session.createQuery(sql);
            List<Customer> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<Customer> getAllCustomerActiveOrders() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Customer> query = session.createQuery("" +
                "SELECT c " +
                "FROM Customer c " +
                "WHERE c.type = 'Khách hàng' AND c.id in (" +
                "SELECT o.customer.id " +
                "FROM Orders o " +
                "WHERE o.status = 'Chưa hoàn tất')");
        List<Customer> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Customer getByCustomerName(String customerName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Customer> query = session.createQuery("" +
                "SELECT c " +
                "FROM Customer c " +
                "WHERE c.fullName = :name");
        query.setParameter("name", customerName);
        Customer result = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<Customer> getByCustomerType(Session session, String type) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Customer.class.getName() + " c where c.type = '" + type + "'";
            Query<Customer> query = session.createQuery(sql);
            List<Customer> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<Customer> getAllSupplierActiveOrders() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Customer> query = session.createQuery("" +
                "SELECT c " +
                "FROM Customer c " +
                "WHERE c.type = 'Nhà cung cấp' AND c.id in (" +
                "SELECT o.customer.id " +
                "FROM Orders o " +
                "WHERE o.status = 'Chưa hoàn tất')");
        List<Customer> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
