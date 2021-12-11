package repositories;

import dataModel.search.OrderSearchModel;
import entities.Orders;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrdersRepository {
    private static Session session;

    public static List<Orders> getAll() {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Query<Orders> query = session.createQuery("" +
                    "SELECT o " +
                    "FROM Orders o");
            List<Orders> result = query.getResultList();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            session.close();
            return null;
        }
    }

    public static List<Orders> getLikeCustomerName(String customerName) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Query<Orders> query = session.createQuery("" +
                    "SELECT o " +
                    "FROM Orders o " +
                    "WHERE o.customer.fullName LIKE :customerName");
            query.setParameter("customerName", "%" + customerName + "%");
            List<Orders> result = query.getResultList();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            session.close();
            return null;
        }
    }

    public static List<Orders> getByCustomerName(String customerName) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Query<Orders> query = session.createQuery("" +
                    "SELECT o " +
                    "FROM Orders o " +
                    "WHERE o.customer.fullName = :customerName");
            query.setParameter("customerName", customerName);
            List<Orders> result = query.getResultList();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            session.close();
            return null;
        }
    }

    public static List<Orders> getActiveByCustomerName(String customerName) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Query<Orders> query = session.createQuery("" +
                    "SELECT o " +
                    "FROM Orders o " +
                    "WHERE o.customer.fullName = :customerName AND o.status = 'Chưa hoàn tất'");
            query.setParameter("customerName", customerName);
            List<Orders> result = query.getResultList();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            session.close();
            return null;
        }
    }

    public static Orders getById(String id) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Query<Orders> query = session.createQuery("" +
                    "SELECT o " +
                    "FROM Orders o " +
                    "WHERE o.id = :id");
            query.setParameter("id", id);
            Orders result = query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            session.close();
            return null;
        }
    }

    public static List<Orders> advanceSearch(OrderSearchModel orderSearchModel) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Map<String, Object> queryProperties = new HashMap<>();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // PREPARE QUERY CONDITION
            List<String> conditions = new ArrayList<>();
            String customerName = orderSearchModel.getCustomerName();
            if (customerName != null && !customerName.isEmpty()) {
                conditions.add("t.customer_id IN (select c.id from customer c where c.full_name LIKE :name)");
                queryProperties.put("name", "%" + customerName + "%");
            }

            Date fromDate = orderSearchModel.getFromDate();
            if (fromDate != null) {
                conditions.add("t.created_date >= " + "'" + dateFormat.format(fromDate) + "'");
            }

            Date toDate = orderSearchModel.getToDate();
            if (toDate != null) {
                conditions.add("t.created_date <= " + "'" + dateFormat.format(toDate) + "'");
            }

            String orderStatus = orderSearchModel.getOrderStatus();
            if (orderStatus != null && !orderStatus.isEmpty() && !orderStatus.equals("Tất cả")) {
                conditions.add("t.status = :orderStatus");
                queryProperties.put("orderStatus", orderStatus);
            }

            String orderType = orderSearchModel.getOrderType();
            if (orderType != null && !orderType.isEmpty() && !orderType.equals("Tất cả")) {
                conditions.add("t.type = :type");
                queryProperties.put("type", orderType);
            }

            String queryWhereStr = conditions.size() > 0 ? "where " + String.join(" and ", conditions) : "";
            String queryStr = "SELECT t.* FROM orders t " + queryWhereStr;

            Query<Orders> query = session.createNativeQuery(queryStr, Orders.class);
            query.setProperties(queryProperties);

            List<Orders> result = query.getResultList();

            session.getTransaction().commit();
            session.close();

            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            session.close();
            return null;
        }
    }
}
