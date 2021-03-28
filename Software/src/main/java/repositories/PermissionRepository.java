package repositories;

import entities.Permissions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.Arrays;
import java.util.List;

public class PermissionRepository {

    public static List<Permissions> getAll(Session session) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Permissions.class.getName() + " c";
            Query<Permissions> query = session.createQuery(sql);
            List<Permissions> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Permissions getByName(Session session, String name) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Permissions.class.getName() + " c where c.name = '" + name + "'";
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
}
