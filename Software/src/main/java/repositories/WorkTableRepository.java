package repositories;

import entities.WorkShift;
import entities.WorkTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class WorkTableRepository {

    public static List<WorkTable> getByShiftId(String shiftId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<WorkTable> query = session.createQuery("" +
                "SELECT w " +
                "FROM WorkTable w " +
                "WHERE w.workShift.id = :shiftId");
        query.setParameter("shiftId", shiftId);
        List<WorkTable> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<WorkTable> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<WorkTable> query = session.createQuery("" +
                "SELECT w " +
                "FROM WorkTable w");
        List<WorkTable> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<WorkTable> getByEmployeeOrShift(String searchText) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<WorkTable> query = session.createQuery("" +
                "SELECT w " +
                "FROM WorkTable w " +
                "WHERE w.employee.fullName LIKE :searchText OR w.workShift.name LIKE :searchText");
        query.setParameter("searchText", "%" + searchText + "%");
        List<WorkTable> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static List<WorkTable> getByEmployeeNameAndShift(String employeeName, WorkShift workShift) {
        Session session = HibernateUtils.getSessionFactory().openSession();
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
        session.close();
        return result;
    }
}
