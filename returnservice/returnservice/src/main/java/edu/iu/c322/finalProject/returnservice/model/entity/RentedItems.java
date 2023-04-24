package edu.iu.c322.finalProject.returnservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "rentedItems")
public class RentedItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rentedItemsGen")
    @SequenceGenerator(name = "rentedItemsGen", sequenceName = "rentedItemsSeq", allocationSize = 1)
    private int id;

    private int order_Id;

    private int customer_Id;


    private int itemId;


    private String name;

    private double price;
    @NotNull(message = "quantity should not be null")
    private Integer quantity;

    private String dateRented;

    private String returnByDate;

    public RentedItems() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(int order_Id) {
        this.order_Id = order_Id;
    }

    public int getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(int customer_Id) {
        this.customer_Id = customer_Id;
    }

    public String getDateRented() {
        return dateRented;
    }

    public void setDateRented(String dateRented) {
        this.dateRented = dateRented;
    }

    public String getReturnByDate() {
        return returnByDate;
    }

    public void setReturnByDate(String returnByDate) {
        this.returnByDate = returnByDate;
    }
}
