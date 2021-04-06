package repositories;

import entities.Customer;
import entities.Imports;
import entities.ImportsDetail;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImportsRepository {

    public static List<Imports> getAll(Session session) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Imports.class.getName() + " c";
            Query<Imports> query = session.createQuery(sql);
            List<Imports> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Imports getById(Session session, String id) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Imports.class.getName() + " c where c.id = '" + id + "'";
            Query<Imports> query = session.createQuery(sql);
            Imports result = query.getSingleResult();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<Imports> getByOrdersId(Session session, String ordersId) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + Imports.class.getName() + " c where c.orders.id = '" + ordersId + "'";
            Query<Imports> query = session.createQuery(sql);
            List<Imports> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<Imports> getLikeCustomerName(Session session, String customerName) {
        try {
            session.beginTransaction();
            List<Imports> result = session.createQuery("" +
                    "SELECT c " +
                    "FROM Imports c " +
                    "WHERE c.orders.customer.fullName like '%" + customerName + "%'").getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

//    session.beginTransaction();
//    Query query = session.createQuery("" +
//            "SELECT i.id, i.imports.id, i.merchandise.id, sum(i.quantity) as quantity, sum(i.amount) as amount " +
//            "FROM ImportsDetail i " +
//            "WHERE i.imports.id IN (?1) " +
//            "GROUP BY i.merchandise.id");
//            query.setParameterList(1, importsId);
//    List<Object> queryResult = query.getResultList();
//    List<ImportsDetail> result = new ArrayList<>();
//            for (Object c : queryResult) {
//        result.add(new ImportsDetail(c));
//    }
//            session.getTransaction().commit();
//            return result;
}
