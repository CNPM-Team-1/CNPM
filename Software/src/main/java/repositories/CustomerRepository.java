package repositories;

import entities.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class CustomerRepository{

    public static List<Customer> getAll(Session session) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Customer.class.getName() + " c";
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

    public static List<Customer> getByNameOrPhone(Session session, String keySearch) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Customer.class.getName() + " c where c.fullName like '%" + keySearch + "%' or c.phone like '%" + keySearch + "%'" ;
            Query<Customer> query = session.createQuery(sql);
            List<Customer> result = query.list();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Customer getByName(Session session, String name) {
        try {
            String sql = "Select c from " + Customer.class.getName() + " c where c.fullName = '" + name + "'";
            Query<Customer> query = session.createQuery(sql);
            Customer result = query.getSingleResult();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Customer getByPhone(Session session, String phone) {
        try {
            String sql = "Select c from " + Customer.class.getName() + " c where c.phone = '" + phone + "'";
            Query<Customer> query = session.createQuery(sql);
            Customer result = query.getSingleResult();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Customer getByEmail(Session session, String email) {
        try {
            String sql = "Select c from " + Customer.class.getName() + " c where c.email = '" + email + "'";
            Query<Customer> query = session.createQuery(sql);
            Customer result = query.getSingleResult();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
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

    public static Customer getByCustomerName(Session session, String name) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Customer.class.getName() + " c where c.fullName = '" + name + "'";
            Query<Customer> query = session.createQuery(sql);
            Customer result = query.getSingleResult();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }
}
