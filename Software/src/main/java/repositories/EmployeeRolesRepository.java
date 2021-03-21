package repositories;

import entities.EmployeeRoles;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;

public class EmployeeRolesRepository {

    public static EmployeeRoles getByEmployeeId(Session session, String employeeId) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + EmployeeRoles.class.getName() + " c where c.employeeId = '" + employeeId + "'";
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
}
