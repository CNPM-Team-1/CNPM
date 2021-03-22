package dataModel;

import java.util.Date;

public class MerchandiseModel {
    private String id;
    private String name;
    private String type;
    private String branch;
    private String importPrice;
    private String price;
    private Date createdDate;
    private Date updatedDate;

    public MerchandiseModel() {
    }

    public MerchandiseModel(String id, String name, String type, String branch, String importPrice, String price, Date createdDate, Date updatedDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.branch = branch;
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

    public String getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(String importPrice) {
        this.importPrice = importPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
}
