package validation;

import entities.Roles;
import org.hibernate.Session;
import repositories.RolesRepository;

import java.util.ArrayList;
import java.util.List;

public class RolesValidation {

    public static List<String> validateInsert(Session session, Roles roles) {
        List<String> msg = new ArrayList<>();

        Roles rolesName = RolesRepository.getByName(session, roles.getName());

        if (roles.getName() == null || roles.getName().isEmpty()) {
            msg.add("Chưa điền tên chức vụ");
        } else if (rolesName != null) {
            msg.add("Tên đã được sử dụng");
        }

        return msg;
    }

    public static List<String> validateUpdate(Session session, Roles roles) {
        List<String> msg = new ArrayList<>();

        Roles rolesName = RolesRepository.getByName(session, roles.getName());

        if (roles.getName() == null || roles.getName().isEmpty()) {
            msg.add("Chưa điền tên");
        } else if (rolesName != null && !rolesName.getId().equals(roles.getId())) {
            msg.add("Tên đã được sử dụng");
        }
        session.getTransaction().commit();

        return msg;
    }

    // TODO add validate delete
}
