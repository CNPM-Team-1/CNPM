package entities;

import javax.persistence.*;
import java.util.Date;

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
    @Column(name = "import_price")
    private Integer importPrice;
    @Column(name = "price")
    private Integer price;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;

    public Merchandise() {
    }

    public Merchandise(String id, String name, String type, String branch, Integer importPrice, Integer price, Date createdDate, Date updatedDate) {
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

    public Integer getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Integer importPrice) {
        this.importPrice = importPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
