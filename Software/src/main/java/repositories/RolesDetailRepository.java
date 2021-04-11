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
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        String sql = "Select c from " + RolesDetail.class.getName() + " c where c.roles.id = '" + rolesId + "'";
        Query<RolesDetail> query = session.createQuery(sql);
        List<RolesDetail> result = query.getResultList();
        session.getTransaction().commit();
        return result;
    }

    public static void deleteByRoleId(String rolesId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("" +
                "DELETE " +
                "FROM RolesDetail r " +
                "WHERE r.roles.id = :rolesId");
        query.setParameter("rolesId", rolesId);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
