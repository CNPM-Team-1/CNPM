package validation;

import entities.EmployeeRoles;
import entities.Roles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.EmployeeRolesRepository;
import repositories.RolesRepository;

import java.util.ArrayList;
import java.util.List;

public class RolesValidation {

    public static List<String> validateInsert(SessionFactory sessionFactory, Roles roles) {
        List<String> msg = new ArrayList<>();
        Session session;

        session = sessionFactory.openSession();
        Roles rolesName = RolesRepository.getByName(session, roles.getName());

        if (roles.getName() == null || roles.getName().isEmpty()) {
            msg.add("Chưa điền tên chức vụ");
        } else if (rolesName != null) {
            msg.add("Tên đã được sử dụng");
        }

        return msg;
    }

    public static List<String> validateUpdate(SessionFactory sessionFactory, Roles roles) {
        List<String> msg = new ArrayList<>();
        Session session;

        session = sessionFactory.openSession();
        Roles rolesName = RolesRepository.getByName(session, roles.getName());

        if (roles.getName() == null || roles.getName().isEmpty()) {
            msg.add("Chưa điền tên");
        } else if (rolesName != null && !rolesName.getId().equals(roles.getId())) {
            msg.add("Tên đã được sử dụng");
        }

        return msg;
    }

    public static List<String> validateDelete(SessionFactory sessionFactory, Roles roles) {
        List<String> msg = new ArrayList<>();
        Session session;

        // Check if someone is using role
        session = sessionFactory.openSession();
        List<EmployeeRoles> employeeRolesList = EmployeeRolesRepository.getByRolesId(session, roles.getId());
        if (employeeRolesList != null && employeeRolesList.size() > 0) {
            msg.add("Chức vụ đang được sử dụng");
        }

        return msg;
    }
}
