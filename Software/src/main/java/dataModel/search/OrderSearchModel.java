package dataModel.search;

import lombok.Data;

import java.util.Date;

@Data
public class OrderSearchModel {
    private String customerName;
    private String orderStatus;
    private String orderType;
    private Date fromDate;
    private Date toDate;
}
