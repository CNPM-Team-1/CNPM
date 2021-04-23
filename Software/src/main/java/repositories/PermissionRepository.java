package repositories;

import entities.Permissions;
import entities.RolesDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.Arrays;
import java.util.List;

public class PermissionRepository {

    private static Session session;

    public static List<Permissions> getAll() {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Query<Permissions> query = session.createQuery("" +
                    "SELECT p " +
                    "FROM Permissions p");
            List<Permissions> result = query.getResultList();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception ex) {
            session.close();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Permissions getByName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Permissions> query = session.createQuery("" +
                "SELECT p " +
                "FROM Permissions p " +
                "WHERE p.name = :name");
        query.setParameter("name", name);
        Permissions result = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Permissions getByCode(String code) {
        try {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            String sql = "Select c from " + Permissions.class.getName() + " c where c.code = '" + code + "'";
            Query<Permissions> query = session.createQuery(sql);
            Permissions result = query.getSingleResult();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<String> getEmployeePermissions(String employeeId) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Query<String> query = session.createQuery("" +
                    "SELECT p.code " +
                    "FROM Permissions p " +
                    "WHERE p.code IN (SELECT rd.permissions.code " +
                    "FROM RolesDetail rd " +
                    "WHERE rd.roles.id = (SELECT er.roles.id " +
                    "FROM EmployeeRoles er " +
                    "WHERE er.employee.id = :employeeId))");
            query.setParameter("employeeId", employeeId);
            List<String> result = query.getResultList();
            session.close();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            session.close();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }
}
