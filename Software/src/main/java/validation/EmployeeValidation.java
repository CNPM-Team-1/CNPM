package validation;

import entities.Employee;
import org.hibernate.Session;
import repositories.EmployeeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeValidation {

    public static List<String> validateInsert(Session session, Employee employee) {
        List<String> msg = new ArrayList<>();

        Employee employeeName = EmployeeRepository.getByName(session, employee.getFullName());
        Employee employeePhone = EmployeeRepository.getByPhone(session, employee.getPhone());
        Employee employeeEmail = EmployeeRepository.getByEmail(employee.getEmail(), session);

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
        if (employee.getEmail() == null || employee.getEmail().isEmpty()) {
            msg.add("Chưa điền email");
        } else if (employeeEmail != null) {
            msg.add("Email đã được sử dụng");
        }
        if (employee.getPassword() == null) {
            msg.add("Chưa điền mật khẩu");
        }
        if (employee.getBirthDay() == null) {
            msg.add("Chưa chọn ngày sinh");
        }

        return msg;
    }

    public static List<String> validateUpdate(Session session, Employee employee) {
        try {
            List<String> msg = new ArrayList<>();

            Employee employeeName = EmployeeRepository.getByName(session, employee.getFullName());
            Employee employeePhone = EmployeeRepository.getByPhone(session, employee.getPhone());
            Employee employeeEmail = EmployeeRepository.getByEmail(employee.getEmail(), session);

            // check name
            if (employee.getFullName() == null || employee.getFullName().isEmpty()) {
                msg.add("Chưa điền tên");
            } else if (employeeName != null && !employeeName.getId().equals(employee.getId())) {
                msg.add("Tên đã được sử dụng");
            }
            // check phone
            if (employee.getPhone() == null || employee.getPhone().isEmpty()) {
                msg.add("Chưa điền số điện thoại");
            } else if (employee.getPhone().length() == 10 && employeePhone != null && !employeePhone.getId().equals(employee.getId())) {
                msg.add("SĐT đã được sử dụng");
            }
            // check email
            if (employee.getEmail() == null || employee.getEmail().isEmpty()) {
                msg.add("Chưa điền email");
            } else if (employeeEmail != null && !employeeEmail.getId().equals(employee.getId())) {
                msg.add("Email đã được sử dụng");
            }
            if (employee.getPassword() == null) {
                msg.add("Chưa điền mật khẩu");
            }
            if (employee.getBirthDay() == null) {
                msg.add("Chưa chọn ngày sinh");
            }

            return msg;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }
}
