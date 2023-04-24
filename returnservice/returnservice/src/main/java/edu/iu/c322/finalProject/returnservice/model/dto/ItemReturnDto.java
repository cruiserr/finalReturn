package edu.iu.c322.finalProject.returnservice.model.dto;

public class ItemReturnDto {

    private int orderId;

    private String dateReceived;

    private int latePenalty;

    private int siteFee;

    private double customerTotal;

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
