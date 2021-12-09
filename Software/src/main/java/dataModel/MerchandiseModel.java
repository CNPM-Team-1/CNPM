package dataModel;

import entities.Merchandise;
import lombok.Data;
import utils.NumberHelper;
import utils.StringHelper;

import java.util.Date;

@Data
public class MerchandiseModel {
    private String id;
    private String name;
    private String type;
    private String branch;
    private Integer quantity;
    private String importPrice;
    private String price;
    private Date createdDate;
    private Date updatedDate;

    public Merchandise toMerchandise() {
        Merchandise result = new Merchandise();
        result.setId(this.getId());
        result.setName(this.getName());
        result.setType(this.getType());
        result.setBranch(this.getBranch());
        result.setQuantity(this.getQuantity());
        result.setImportPrice(this.getImportPrice());
        result.setPrice(Double.valueOf(NumberHelper.removeComma(this.getPrice())));
        result.setCreatedDate(this.getCreatedDate());
        result.setUpdatedDate(this.getUpdatedDate());

        return result;
    }
}
