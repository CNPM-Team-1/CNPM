package repositories;

import entities.Customer;
import entities.Orders;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class OrderRepository {

    public static List<Orders> getAll(Session session) {
        try {
            session.beginTransaction();
            String sql = "Select o from " + Orders.class.getName() + " o";
            Query<Orders> query = session.createQuery(sql);
            List<Orders> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<Orders> getByCustomerId(Session session, String keySearch) {
        try {
            session.beginTransaction();
            String sql = "Select o from " + Orders.class.getName() + " o where o.customerId like '%" + keySearch + "%'" ;
            Query<Orders> query = session.createQuery(sql);
            List<Orders> result = query.list();
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
