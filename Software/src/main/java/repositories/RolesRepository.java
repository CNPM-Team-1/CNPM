package repositories;

import entities.Roles;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class RolesRepository {

    public static List<Roles> getAll(Session session) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Roles.class.getName() + " c";
            Query<Roles> query = session.createQuery(sql);
            List<Roles> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Roles getByName(Session session, String name) {
        try {
            String sql = "Select c from " + Roles.class.getName() + " c where c.name = '" + name + "'";
            Query<Roles> query = session.createQuery(sql);
            return query.getSingleResult();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<Roles> getLikeName(Session session, String name) {
        try {
            String sql = "Select c from " + Roles.class.getName() + " c where c.name like '%" + name + "%'";
            Query<Roles> query = session.createQuery(sql);
            return query.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }
}
