package repositories;

import entities.Customer;
import entities.Merchandise;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class MerchandiseRepository {
    public static List<Merchandise> getAll(Session session) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Merchandise.class.getName() + " c";
            Query<Merchandise> query = session.createQuery(sql);
            List<Merchandise> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Merchandise getByName(Session session, String name) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Merchandise.class.getName() + " c where c.name = '" + name + "'";
            Query<Merchandise> query = session.createQuery(sql);
            Merchandise result = query.getSingleResult();
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
