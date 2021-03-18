package repositories;

import entities.Customer;
import javafx.scene.Parent;
import org.hibernate.Session;
import org.hibernate.query.Query;
import entities.Employee;

import java.util.Arrays;
import java.util.List;

public class EmployeeRepository {
    public static Employee getByEmail(String email, Session session) {
        try {
            String sql = "Select e from " + Employee.class.getName() + " e where e.email = '" + email + "'";
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

    public static List<Employee> getAll(Session session) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Employee.class.getName() + " c";
            Query<Employee> query = session.createQuery(sql);
            List<Employee> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Employee getByName(Session session, String name) {
        try {
            String sql = "Select c from " + Employee.class.getName() + " c where c.fullName = '" + name + "'";
            Query<Employee> query = session.createQuery(sql);
            return query.getSingleResult();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Employee getByPhone(Session session, String phone) {
        try {
            String sql = "Select c from " + Employee.class.getName() + " c where c.phone = '" + phone + "'";
            Query<Employee> query = session.createQuery(sql);
            return query.getSingleResult();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<Employee> getByNamePhoneEmail(Session session, String keySearch) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Employee.class.getName() + " c where c.fullName like '%" + keySearch + "%' or c.phone like '%" + keySearch + "%' or c.email like '%" + keySearch + "%'";
            Query<Employee> query = session.createQuery(sql);
            List<Employee> result = query.getResultList();
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
