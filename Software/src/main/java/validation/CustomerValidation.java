package validation;

import entities.Customer;
import entities.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.CustomerRepository;
import repositories.OrdersRepository;
import utils.NumberHelper;
import utils.StringHelper;

import java.util.ArrayList;
import java.util.List;

public class CustomerValidation {

    public static List<String> validateInsert(Session session, Customer customer) {
        List<String> msg = new ArrayList<>();

        Customer customerPhone = CustomerRepository.getByPhone(session, customer.getPhone());
        Customer customerName = CustomerRepository.getByName(session, customer.getFullName());
        Customer customerEmail = CustomerRepository.getByEmail(session, customer.getEmail());

        if (customer.getFullName() == null || customer.getFullName().isEmpty()) {
            msg.add("Chưa điền tên");
        } else if (customerName != null) {
            msg.add("Tên đã được sử dụng");
        }
        if (customer.getPhone() == null || customer.getPhone().isEmpty()) {
            msg.add("Chưa điền số điện thoại");
        } else if (!NumberHelper.isNumber(customer.getPhone())) {
            msg.add("Số điện thoại phải là số");
        } else if (customer.getPhone().length() != 10) {
            msg.add("Số điện thoại phải có 10 chữ số");
        } else if (customerPhone != null) {
            msg.add("Số điện thoại đã được sử dụng");
        }
        if (customer.getType() == null) {
            msg.add("Chưa chọn loại khách hàng");
        }
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            msg.add("Chưa điền email");
        } else if (!StringHelper.isEmail(customer.getEmail())) {
            msg.add("Email không hợp lệ");
        } else if (customerEmail != null) {
            msg.add("Email đã được sử dụng");
        }
        return msg;
    }

    public static List<String> validateUpdate(Session session, Customer customer) {
        List<String> msg = new ArrayList<>();

        Customer customerPhone = CustomerRepository.getByPhone(session, customer.getPhone());
        Customer customerName = CustomerRepository.getByName(session, customer.getFullName());
        Customer customerEmail = CustomerRepository.getByEmail(session, customer.getEmail());

        // check name
        if (customer.getFullName() == null || customer.getFullName().isEmpty()) {
            msg.add("Chưa điền tên");
        } else if (customerName != null && !customerName.getId().equals(customer.getId())) {
            msg.add("Tên đã được sử dụng");
        }
        // check phone
        if (customer.getPhone() == null || customer.getPhone().isEmpty()) {
            msg.add("Chưa điền số điện thoại");
        } else if (!NumberHelper.isNumber(customer.getPhone())) {
            msg.add("Số điện thoại phải là số");
        } else if (customer.getPhone().length() != 10) {
            msg.add("Số điện thoại phải có 10 chữ số");
        } else if (customerPhone != null && !customerPhone.getId().equals(customer.getId())) {
            msg.add("Số điện thoại đã được sử dụng");
        }
        // check type
        if (customer.getType() == null) {
            msg.add("Chưa chọn loại khách hàng");
        }
        // check email
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            msg.add("Chưa điền email");
        } else if (!StringHelper.isEmail(customer.getEmail())) {
            msg.add("Email không hợp lệ");
        } else if (customerEmail != null && !customerEmail.getId().equals(customer.getId())) {
            msg.add("Email đã được sử dụng");
        }
        session.getTransaction().commit();

        return msg;
    }

    public static List<String> validateDelete(SessionFactory sessionFactory, Customer customer) {
        List<String> msg = new ArrayList<>();
        Session session;

        session = sessionFactory.openSession();
        List<Orders> ordersList = OrdersRepository.getByCustomerName(session, customer.getFullName());

        if (ordersList != null && ordersList.size() != 0) {
            msg.add("Không được xoá khách hàng có trong đơn hàng");
        }

        return msg;
    }
}
