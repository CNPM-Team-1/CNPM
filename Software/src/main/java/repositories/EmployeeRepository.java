package repositories;

import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeRepository {
    public static Employee getByEmail(String email) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Employee> query = session.createQuery("" +
                    "SELECT e " +
                    "FROM Employee e " +
                    "WHERE e.email = :email");
            query.setParameter("email", email);
            Employee result = query.uniqueResult();

            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static List<Employee> getAll() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Query<Employee> query = session.createQuery("" +
                    "SELECT e " +
                    "FROM Employee e");
            List<Employee> result = query.getResultList();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    public static Employee getByName(String name) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Employee> query = session.createQuery("" +
                    "SELECT e " +
                    "FROM Employee e " +
                    "WHERE e.fullName = :name");
            query.setParameter("name", name);
            Employee result = query.uniqueResult();
            session.getTransaction().commit();

            session.close();
            return query.getSingleResult();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static Employee getByEmployeeName(String employeeName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Employee> query = session.createQuery("" +
                "SELECT e " +
                "FROM Employee e " +
                "WHERE e.fullName = :employeeName");
        query.setParameter("employeeName", employeeName);
        Employee result = query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Employee getByPhone(String phone) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Query<Employee> query = session.createQuery("" +
                    "SELECT e " +
                    "FROM Employee e " +
                    "WHERE e.phone = :phone");
            query.setParameter("phone", phone);
            Employee result = query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<Employee> getByNamePhoneEmail(String keySearch) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Employee> query = session.createQuery("" +
                "SELECT e " +
                "FROM Employee e " +
                "WHERE e.fullName LIKE :keySearch OR e.phone LIKE :keySearch");
        query.setParameter("keySearch", keySearch);
        List<Employee> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Employee getById(Session session, String id) {
        try {
            session.beginTransaction();
            String sql = "Select e from " + Employee.class.getName() + " e where e.id = '" + id + "'";
            Query<Employee> query = session.createQuery(sql);
            Employee result = query.getSingleResult();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }
}
