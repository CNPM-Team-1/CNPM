package repositories;

import entities.Customer;
import entities.OrdersDetail;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static List<OrdersDetail> getByOrdersId(String id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<OrdersDetail> query = session.createQuery("" +
                "SELECT a " +
                "FROM OrdersDetail a " +
                "WHERE a.orders.id = :id");
        query.setParameter("id", id);
        List<OrdersDetail> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static void deleteByOrdersId(String id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<OrdersDetail> query = session.createQuery("" +
                "DELETE " +
                "FROM OrdersDetail o " +
                "WHERE o.orders.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static Long getSumAmountById(String ordersId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Long> query = session.createQuery("" +
                "SELECT SUM(od.amount) " +
                "FROM OrdersDetail od " +
                "WHERE od.orders.id = :ordersId");
        query.setParameter("ordersId", ordersId);
        Long result = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Long getSumQuantityById(String ordersId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Long> query = session.createQuery("" +
                "SELECT SUM(od.quantity) " +
                "FROM OrdersDetail od " +
                "WHERE od.orders.id = :ordersId");
        query.setParameter("ordersId", ordersId);
        Long result = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static OrdersDetail getById(String id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<OrdersDetail> query = session.createQuery("" +
                "SELECT d " +
                "FROM OrdersDetail d " +
                "WHERE d.id = :id");
        query.setParameter("id", id);
        OrdersDetail result = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<Object> getAmountBuying(Date fromDate, Date toDate) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Object> query;
        if (fromDate == null && toDate == null) {
            query = session.createQuery("" +
                    "SELECT od.orders.customer.fullName, SUM(od.amount) AS sumAmount, od.orders.createdDate " +
                    "FROM OrdersDetail od " +
                    "WHERE od.orders.id IN (" +
                    "SELECT o.id " +
                    "FROM Orders o " +
                    "WHERE o.type = 'Bán hàng') " +
                    "GROUP BY od.orders.id " +
                    "ORDER BY sumAmount DESC ");
        } else if (fromDate != null && toDate == null) {
            query = session.createQuery("" +
                    "SELECT od.orders.customer.fullName, SUM(od.amount) AS sumAmount, od.orders.createdDate " +
                    "FROM OrdersDetail od " +
                    "WHERE od.orders.id IN (" +
                    "SELECT o.id " +
                    "FROM Orders o " +
                    "WHERE o.type = 'Bán hàng' AND o.createdDate >= :fromDate) " +
                    "GROUP BY od.orders.id " +
                    "ORDER BY sumAmount DESC ");
            query.setParameter("fromDate", fromDate);
        } else if (fromDate == null && toDate != null) {
            query = session.createQuery("" +
                    "SELECT od.orders.customer.fullName, SUM(od.amount) AS sumAmount, od.orders.createdDate " +
                    "FROM OrdersDetail od " +
                    "WHERE od.orders.id IN (" +
                    "SELECT o.id " +
                    "FROM Orders o " +
                    "WHERE o.type = 'Bán hàng' AND o.createdDate <= :toDate) " +
                    "GROUP BY od.orders.id " +
                    "ORDER BY sumAmount DESC ");
            query.setParameter("toDate", toDate);
        } else {
            query = session.createQuery("" +
                    "SELECT od.orders.customer.fullName, SUM(od.amount) AS sumAmount, od.orders.createdDate " +
                    "FROM OrdersDetail od " +
                    "WHERE od.orders.id IN (" +
                    "SELECT o.id " +
                    "FROM Orders o " +
                    "WHERE o.type = 'Bán hàng' AND o.createdDate >= :fromDate AND o.createdDate <= :toDate) " +
                    "GROUP BY od.orders.id " +
                    "ORDER BY sumAmount DESC ");
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
        }
        query.setMaxResults(8);
        List<Object> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
