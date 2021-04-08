package repositories;

import entities.ImportsDetail;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ImportsDetailRepository {

    public static List<ImportsDetail> getAll(Session session) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + ImportsDetail.class.getName() + " c";
            Query<ImportsDetail> query = session.createQuery(sql);
            List<ImportsDetail> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Long getSumAmountByImportsId(Session session, String importsId) {
        try {
            session.beginTransaction();
            String sql = "Select sum(od.amount) from " + ImportsDetail.class.getName() + " od where od.imports.id = '" + importsId + "'";
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

    public static Long getSumQuantityByImportsId(Session session, String importsId) {
        try {
            session.beginTransaction();
            String sql = "Select sum(od.quantity) from " + ImportsDetail.class.getName() + " od where od.imports.id = '" + importsId + "'";
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

    public static List<ImportsDetail> getByImportsId(Session session, String importsId) {
        try {
            session.beginTransaction();
            String sql = "Select c from " + ImportsDetail.class.getName() + " c where c.imports.id = '" + importsId + "'";
            Query<ImportsDetail> query = session.createQuery(sql);
            List<ImportsDetail> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<ImportsDetail> getByImportsIdIn(Session session, List<String> importsId) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM ImportsDetail i WHERE i.imports.id IN (?1)");
            query.setParameterList(1, importsId);
            List<ImportsDetail> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static List<ImportsDetail> getDistinctByImportsIdIn(Session session, List<String> importsId) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("" +
                    "SELECT i.id, i.imports.id, i.merchandise.id, sum(i.quantity) as quantity, sum(i.amount) as amount " +
                    "FROM ImportsDetail i " +
                    "WHERE i.imports.id IN (?1) " +
                    "GROUP BY i.merchandise.id");
            query.setParameterList(1, importsId);
            List<Object> queryResult = query.getResultList();
            List<ImportsDetail> result = new ArrayList<>();
            for (Object c : queryResult) {
                result.add(new ImportsDetail(c));
            }
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Long getBoughtQuantityOfMerchandise(Session session, String ordersId, String merchandiseId, String importsId) {
        try {
            session.beginTransaction();
            Query<Long> query = session.createQuery("" +
                    "SELECT sum(t.quantity) " +
                    "FROM ImportsDetail t " +
                    "WHERE t.imports.id IN (SELECT u.id FROM Imports u WHERE u.orders.id = :ordersId) " +
                    "AND t.merchandise.id = :merchandiseId " +
                    "AND t.imports.id <> :importsId");
            query.setParameter("ordersId", ordersId);
            query.setParameter("merchandiseId", merchandiseId);
            query.setParameter("importsId", importsId);

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
}
