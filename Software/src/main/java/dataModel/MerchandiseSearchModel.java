package dataModel;

import lombok.Data;

@Data
public class MerchandiseSearchModel {
    private String name;
    private Double fromPrice;
    private Double toPrice;
    private String type;
    private String brand;
    private Boolean inStock;

    public MerchandiseSearchModel() {

    }

    public MerchandiseSearchModel(String name, Double fromPrice, Double toPrice, String type, String brand, Boolean inStock) {
        this.name = name;
        this.fromPrice = fromPrice;
        this.toPrice = toPrice;
        this.type = type;
        this.brand = brand;
        this.inStock = inStock;
    }

    public MerchandiseSearchModel filterData() {
        MerchandiseSearchModel result = new MerchandiseSearchModel();
        result.setName((this.getName() != null && this.getName().isEmpty()) ? null : this.getName());
        result.setFromPrice((this.getFromPrice() != null && this.getFromPrice().isNaN()) ? null : this.getFromPrice());
        result.setToPrice((this.getToPrice() != null && this.getToPrice().isNaN()) ? null : this.getToPrice());
        result.setType((this.getType() != null && (this.getType().isEmpty() || this.getType().equals("Tất cả"))) ? null : this.getType());
        result.setBrand((this.getBrand() != null && (this.getBrand().isEmpty() || this.getBrand().equals("Tất cả"))) ? null : this.getBrand());
        result.setInStock(this.inStock);

        return result;
    }
}
