package validation;

import entities.Imports;
import entities.Orders;
import entities.Receipt;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.ImportsRepository;
import repositories.ReceiptRepository;

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

    public static List<String> validateDelete(SessionFactory sessionFactory, Orders orders) {
        List<String> msg = new ArrayList<>();

        Session session;
        session = sessionFactory.openSession();
        List<Receipt> receiptList = ReceiptRepository.getAll(session);
        session = sessionFactory.openSession();
        List<Imports> importsList = ImportsRepository.getAll(session);

        if (orders.getStatus().equals("Hoàn tất")) {
            msg.add("Không được xoá đơn hàng đã hoàn tất");
        }
        if (receiptList != null) {
            if (receiptList.stream().anyMatch(t -> t.getOrders().getId().equals(orders.getId()))) {
                msg.add("Không được xoá đơn hàng có hoá đơn");
            }
        }
        if (importsList != null) {
            if (importsList.stream().anyMatch(t -> t.getOrders().getId().equals(orders.getId()))) {
                msg.add("Không được xoá đơn hàng có phiếu nhập hàng");
            }
        }

        return msg;
    }
}
