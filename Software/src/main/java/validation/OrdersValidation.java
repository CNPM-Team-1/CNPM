package validation;

import entities.Orders;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class OrdersValidation {

    public static List<String> validateUpdate(Session session, Orders orders) {
        List<String> msg = new ArrayList<>();

        if (orders.getStatus().equals("Hoàn tất")) {
            msg.add("Không được cập nhật đơn hàng đã hoàn tất");
        }

        return msg;
    }

    public static List<String> validateDelete(Session session, Orders orders) {
        List<String> msg = new ArrayList<>();

        if (orders.getStatus().equals("Hoàn tất")) {
            msg.add("Không được xoá đơn hàng đã hoàn tất");
        }

        return msg;
    }
}
