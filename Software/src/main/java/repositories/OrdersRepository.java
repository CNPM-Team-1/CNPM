package repositories;

import entities.Customer;
import entities.Orders;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersRepository {

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
            String sql = "Select o from " + Orders.class.getName() + " o where o.customerId like '%" + keySearch + "%'";
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

    public static List<Orders> getByCustomerIdIn(Session session, List<Customer> customerList) {
        try {
            if (customerList.size() > 0) {
                List<String> customerIds = customerList.stream().map(Customer::getId).collect(Collectors.toList());
                session.beginTransaction();
                Query query = session.createQuery("FROM entities.Orders c WHERE c.customerId IN (:idList)");
                query.setParameterList("idList", customerIds);
                List<Orders> result = query.list();
                session.getTransaction().commit();
                return result;
            } else {
                return null;
            }
        } catch (Exception ex) {
            session.getTransaction().commit();
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static Orders getById(Session session, String id) {
        try {
            session.beginTransaction();
            String sql = "Select o from " + Orders.class.getName() + " o where o.id = '" + id + "'";
            Query<Orders> query = session.createQuery(sql);
            Orders result = query.getSingleResult();
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
