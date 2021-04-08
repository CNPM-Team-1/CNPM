package repositories;

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

    public static List<OrdersDetail> getByOrdersId(Session session, String id) {
        try {
            session.beginTransaction();
            String sql = "Select a from " + OrdersDetail.class.getName() + " a where a.orders.id = '" + id + "'";
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

    public static void deleteByOrdersId(Session session, String id) {
        try {
            session.beginTransaction();
            String sql = "delete from " + OrdersDetail.class.getName() + " o where o.orders.id = '" + id + "'";
            Query<OrdersDetail> query = session.createQuery(sql);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    public static Long getSumAmountById(Session session, String ordersId) {
        try {
            session.beginTransaction();
            String sql = "Select sum(od.amount) from " + OrdersDetail.class.getName() + " od where od.orders.id = '" + ordersId + "'";
            Query<Long> query = session.createQuery(sql);
            Long result = query.getSingleResult();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Long getSumQuantityById(Session session, String ordersId) {
        try {
            session.beginTransaction();
            String sql = "Select sum(od.quantity) from " + OrdersDetail.class.getName() + " od where od.orders.id = '" + ordersId + "'";
            Query<Long> query = session.createQuery(sql);
            Long result = query.getSingleResult();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static OrdersDetail getById(Session session, String id) {
        try {
            session.beginTransaction();
            String sql = "Select d from " + OrdersDetail.class.getName() + " d where d.id = '" + id + "'";
            Query<OrdersDetail> query = session.createQuery(sql);
            OrdersDetail result = query.getSingleResult();
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
