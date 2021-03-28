package repositories;

import entities.RolesDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.Arrays;
import java.util.List;

public class RolesDetailRepository {

    public static List<RolesDetail> getByRolesId(String rolesId) {
        try {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            String sql = "Select c from " + RolesDetail.class.getName() + " c where c.roles.id = '" + rolesId + "'";
            Query<RolesDetail> query = session.createQuery(sql);
            List<RolesDetail> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static void deleteByRoleId(Session session, String rolesId) {
        try {
            session.beginTransaction();
            String sql = "Delete from " + RolesDetail.class.getName() + " r where r.roles.id = '" + rolesId + "'";
            Query query = session.createQuery(sql);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }
}
