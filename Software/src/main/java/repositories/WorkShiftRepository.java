package repositories;

import entities.WorkShift;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class WorkShiftRepository {

    public static List<WorkShift> getAll(SessionFactory sessionFactory) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query<WorkShift> query = session.createQuery("" +
                    "SELECT w " +
                    "FROM WorkShift w");
            List<WorkShift> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    public static WorkShift getByName(SessionFactory sessionFactory, String name) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query<WorkShift> query = session.createQuery("" +
                    "SELECT w " +
                    "FROM WorkShift w " +
                    "WHERE w.name = :name");
            query.setParameter("name", name);
            WorkShift result = query.getSingleResult();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new WorkShift();
        }
    }

    public static List<WorkShift> getLikeName(SessionFactory sessionFactory, String name) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query<WorkShift> query = session.createQuery("" +
                    "SELECT w " +
                    "FROM WorkShift w " +
                    "WHERE w.name like :name");
            query.setParameter("name", "%" + name + "%");
            List<WorkShift> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
}
