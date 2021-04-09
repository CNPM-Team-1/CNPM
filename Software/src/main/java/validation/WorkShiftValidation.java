package validation;

import entities.WorkShift;
import entities.WorkTable;
import org.hibernate.SessionFactory;
import repositories.WorkShiftRepository;
import repositories.WorkTableRepository;

import java.util.ArrayList;
import java.util.List;

public class WorkShiftValidation {

    public static List<String> validateInsert(SessionFactory sessionFactory, WorkShift workShift) {
        List<String> msg = new ArrayList<>();

        WorkShift workShiftName = WorkShiftRepository.getByName(sessionFactory, workShift.getName());

        if (workShift.getName() == null || workShift.getName().isEmpty()) {
            msg.add("Chưa điền tên ca làm");
        } else if (workShiftName.getId() != null) {
            msg.add("Tên ca làm đã được sử dụng");
        }

        return msg;
    }

    public static List<String> validateUpdate(SessionFactory sessionFactory, WorkShift workShift) {
        List<String> msg = new ArrayList<>();

        WorkShift workShiftName = WorkShiftRepository.getByName(sessionFactory, workShift.getName());

        if (workShift.getName() == null || workShift.getName().isEmpty()) {
            msg.add("Chưa điền tên ca làm");
        } else if (workShiftName.getId() != null && !workShiftName.getId().equals(workShift.getId())) {
            msg.add("Tên đã được sử dụng");
        }

        return msg;
    }

    public static List<String> validateDelete(SessionFactory sessionFactory, WorkShift workShift) {
        List<String> msg = new ArrayList<>();

        List<WorkTable> workTableList = WorkTableRepository.getByShiftId(sessionFactory, workShift.getId());

        if (workTableList.size() > 0) {
            msg.add("Không được xoá ca làm đang được sử dụng");
        }

        return msg;
    }
}
