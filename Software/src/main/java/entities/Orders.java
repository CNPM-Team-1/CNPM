package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "type")
    private String type;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "status")
    private String status;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;

    public Orders() {
    }

    public Orders(String id, String type, String customerId, String status, String createdDate, Date updatedDate) {
        this.id = id;
        this.type = type;
        this.customerId = customerId;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
