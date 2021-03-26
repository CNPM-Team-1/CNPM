package repositories;

import entities.Receipt;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class ReceiptRepository {
    public static List<Receipt> getAll(Session session) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Receipt.class.getName() + " c";
            Query<Receipt> query = session.createQuery(sql);
            List<Receipt> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<Receipt> getLikeCustomerName(Session session, String customerName) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Receipt.class.getName() + " c where c.orders.customer.fullName like '%" + customerName + "%'";
            Query<Receipt> query = session.createQuery(sql);
            List<Receipt> result = query.getResultList();
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
