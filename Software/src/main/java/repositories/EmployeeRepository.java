package repositories;

import org.hibernate.Session;
import org.hibernate.query.Query;
import entities.Employee;

import java.util.Arrays;

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
}
