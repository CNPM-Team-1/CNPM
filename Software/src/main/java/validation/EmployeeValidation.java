package validation;

import entities.Employee;
import org.hibernate.Session;
import repositories.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

public class EmployeeValidation {

    public static List<String> validateInsert(Session session, Employee employee) {
        List<String> msg = new ArrayList<>();

        Employee employeeName = EmployeeRepository.getByName(session, employee.getFullName());
        Employee employeeEmail = EmployeeRepository.getByEmail(employee.getEmail(), session);
        Employee employeePhone = EmployeeRepository.getByPhone(session, employee.getPhone());

        if (employee.getFullName() == null || employee.getFullName().isEmpty()) {
            msg.add("Chưa điền tên");
        } else if (employeeName != null) {
            msg.add("Tên đã được sử dụng");
        }
        if (employee.getPhone() == null || employee.getPhone().isEmpty()) {
            msg.add("Chưa điền số điện thoại");
        } else if (employeePhone != null) {
            msg.add("Số điện thoại đã được sử dụng");
        }


        return msg;
    }
}
