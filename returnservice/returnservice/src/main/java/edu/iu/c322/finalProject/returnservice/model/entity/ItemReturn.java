package edu.iu.c322.finalProject.returnservice.model.entity;

import edu.iu.c322.finalProject.returnservice.model.dto.ItemReturnDto;
import jakarta.persistence.*;

@Entity
@Table(name = "itemReturn")
public class ItemReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "returnGen")
    @SequenceGenerator(name = "returnGen", sequenceName = "returnSeq", allocationSize = 1)
    private int id;


    private int orderId;

    private String dateReceived;

    private int latePenalty;

    private int siteFee;

    private double customerTotal;


    public ItemReturn(){

    }

    public ItemReturn(ItemReturnDto itemReturnDto){
        this.orderId = itemReturnDto.getOrderId();
        this.dateReceived = itemReturnDto.getDateReceived();
        this.latePenalty = itemReturnDto.getLatePenalty();
        this.siteFee = itemReturnDto.getSiteFee();
        this.customerTotal = itemReturnDto.getCustomerTotal();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public int getLatePenalty() {
        return latePenalty;
    }

    public void setLatePenalty(int latePenalty) {
        this.latePenalty = latePenalty;
    }

    public int getSiteFee() {
        return siteFee;
    }

    public void setSiteFee(int siteFee) {
        this.siteFee = siteFee;
    }

    public double getCustomerTotal() {
        return customerTotal;
    }

    public void setCustomerTotal(double customerTotal) {
        this.customerTotal = customerTotal;
    }
}
