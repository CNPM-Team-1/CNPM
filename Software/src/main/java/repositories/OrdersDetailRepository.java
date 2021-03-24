package repositories;

import entities.Orders;
import entities.OrdersDetail;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class OrdersDetailRepository {

    public static List<OrdersDetail> getAll(Session session) {
        try {
            session.beginTransaction();
            String sql = "Select o from " + OrdersDetail.class.getName() + " o";
            Query<OrdersDetail> query = session.createQuery(sql);
            List<OrdersDetail> result = query.getResultList();
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
