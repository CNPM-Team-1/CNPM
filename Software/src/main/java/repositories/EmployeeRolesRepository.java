package repositories;

import entities.EmployeeRoles;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class EmployeeRolesRepository {

    public static EmployeeRoles getByEmployeeId(Session session, String employeeId) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + EmployeeRoles.class.getName() + " c where c.employee.id = '" + employeeId + "'";
            Query<EmployeeRoles> query = session.createQuery(sql);
            EmployeeRoles result = query.getSingleResult();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<EmployeeRoles> getByRolesId(Session session, String rolesId) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + EmployeeRoles.class.getName() + " c where c.roles.id = '" + rolesId + "'";
            Query<EmployeeRoles> query = session.createQuery(sql);
            List<EmployeeRoles> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static void deleteByEmployeeId(Session session, String employeeId) {
        try {
            session.beginTransaction();
            String sql = "Delete from " + EmployeeRoles.class.getName() + " c where c.employee.id = '" + employeeId + "'";
            Query<EmployeeRoles> query = session.createQuery(sql);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }
}
