package validation;

import entities.Merchandise;
import org.hibernate.Session;
import repositories.MerchandiseRepository;

import java.util.ArrayList;
import java.util.List;

public class MerchandiseValidation {

    public static List<String> validateInsert(Session session, Merchandise merchandise) {
        List<String> msg = new ArrayList<>();

        Merchandise merchandiseName = MerchandiseRepository.getByName(session, merchandise.getName());

        if (merchandise.getName() == null || merchandise.getName().isEmpty()) {
            msg.add("Chưa điền tên");
        } else if (merchandiseName != null) {
            msg.add("Tên đã được sử dụng");
        }
        if (merchandise.getBranch() == null || merchandise.getBranch().isEmpty()) {
            msg.add("Chưa điền thương hiệu");
        }
        if (merchandise.getType() == null || merchandise.getType().isEmpty()) {
            msg.add("Chưa điền loại");
        }
        if (merchandise.getImportPrice() == null) {
            msg.add("Chưa điền giá mua");
        }
        if (merchandise.getPrice() == null) {
            msg.add("Chưa điền giá bán");
        }

        return msg;
    }

    public static List<String> validateUpdate(Session session, Merchandise merchandise) {
        List<String> msg = new ArrayList<>();

        Merchandise merchandiseName = MerchandiseRepository.getByName(session, merchandise.getName());

        // Check name
        if (merchandise.getName() == null || merchandise.getName().isEmpty()) {
            msg.add("Chưa điền tên");
        } else if (merchandiseName != null && merchandiseName.getId().equals(merchandise.getId())) {
            msg.add("Tên đã được sử dụng");
        }

        return msg;
    }
}
