package repositories;

import entities.WorkShift;
import entities.WorkTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class WorkTableRepository {

    public static List<WorkTable> getByShiftId(SessionFactory sessionFactory, String shiftId) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query<WorkTable> query = session.createQuery("" +
                    "SELECT w " +
                    "FROM WorkTable w " +
                    "WHERE w.workShift.id = :shiftId");
            query.setParameter("shiftId", shiftId);
            List<WorkTable> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<WorkTable> getAll(SessionFactory sessionFactory) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query<WorkTable> query = session.createQuery("" +
                    "SELECT w " +
                    "FROM WorkTable w");
            List<WorkTable> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<WorkTable> getByEmployeeOrShift(SessionFactory sessionFactory, String searchText) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query<WorkTable> query = session.createQuery("" +
                    "SELECT w " +
                    "FROM WorkTable w " +
                    "WHERE w.employee.fullName LIKE :searchText OR w.workShift.name LIKE :searchText");
            query.setParameter("searchText", "%" + searchText + "%");
            List<WorkTable> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<WorkTable> getByEmployeeNameAndShift(SessionFactory sessionFactory, String employeeName, WorkShift workShift) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query<WorkTable> query = session.createQuery("" +
                    "SELECT w " +
                    "FROM WorkTable w " +
                    "WHERE w.employee.fullName = :employeeName " +
                    "AND w.workShift.id = :workShiftId");
            query.setParameter("employeeName", employeeName);
            query.setParameter("workShiftId", workShift.getId());
            List<WorkTable> result = query.getResultList();
            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
}
