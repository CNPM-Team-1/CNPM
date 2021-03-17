package repositories;

import entities.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class OrderRepository {

    public static List<Order> getAll(Session session) {
        try {
            session.beginTransaction();
            String sql = "Select o from " + Order.class.getName() + " o";
            Query<Order> query = session.createQuery(sql);
            List<Order> result = query.getResultList();
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
