package repositories;

import entities.Permissions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.Arrays;
import java.util.List;

public class PermissionRepository {

    public static List<Permissions> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Permissions> query = session.createQuery("" +
                "SELECT p " +
                "FROM Permissions p");
        List<Permissions> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
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
}
