package model.thing;

import model.human.Customer;

import java.sql.Date;
import java.util.List;

public class Order {
    private Integer id;
    private Date orderDate;
    private Customer customer;
    private Status status;
    private List<Product> products;
    private List<Integer> quantity;
    private Double totalPrice;

    public Order(Integer id, Date orderDate, Customer customer, Status status) {
        this.id = id;
        this.orderDate = orderDate;
        this.customer = customer;
        this.status = status;
    }

    public Order(int id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
