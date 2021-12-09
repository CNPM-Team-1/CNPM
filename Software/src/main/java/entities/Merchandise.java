package entities;

import dataModel.MerchandiseModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "merchandise")
public class Merchandise {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "branch")
    private String branch;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "import_price")
    private String importPrice;
    @Column(name = "price")
    private Double price;
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "updated_date")
    private Date updatedDate;

    public Merchandise() {
    }

    public MerchandiseModel toMerchandiseModel() {
        MerchandiseModel result = new MerchandiseModel();
        result.setId(this.getId());
        result.setName(this.getName());
        result.setType(this.getType());
        result.setBranch(this.getBranch());
        result.setQuantity(this.getQuantity());
        result.setImportPrice(this.getImportPrice());
        result.setPrice(this.getPrice().toString());
        result.setCreatedDate(this.getCreatedDate());
        result.setUpdatedDate(this.getUpdatedDate());

        return result;
    }

    public Merchandise(String id, String name, String type, String branch, Integer quantity, String importPrice, Double price, Date createdDate, Date updatedDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.branch = branch;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.price = price;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(String importPrice) {
        this.importPrice = importPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
}
