package validation;

import entities.Customer;
import org.hibernate.Session;
import repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerValidation {

    public static List<String> validateInsert(Session session, Customer customer) {
        List<String> msg = new ArrayList<>();

        if (customer.getFullName() == null || customer.getFullName().isEmpty()) {
            msg.add("Chưa điền tên");
        } else if (CustomerRepository.getByName(session, customer.getFullName()).size() > 0) {
            msg.add("Tên không khả dụng");
        }
        if (customer.getPhone() == null || customer.getPhone().isEmpty()) {
            msg.add("Chưa điền số điện thoại");
        } else if (customer.getPhone().length() != 10 || CustomerRepository.getByPhone(session, customer.getPhone()).size() > 0) {
            msg.add("Số điện thoại không khả dụng");
        }
        if (customer.getType() == null) {
            msg.add("Chưa chọn loại khách hàng");
        }
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            msg.add("Chưa điền email");
        } else if (CustomerRepository.getByEmail(session, customer.getEmail()).size() > 0) {
            msg.add("Email không khả dụng");
        }

        return msg;
    }

    public static List<String> validateUpdate(Session session, Customer customer) {
        List<String> msg = new ArrayList<>();

        // TODO: add validate update

        return msg;
    }

    public static List<String> validateDelete(Session session, String id) {
        List<String> msg = new ArrayList<>();

        // TODO: add validate delete

        return msg;
    }
}
