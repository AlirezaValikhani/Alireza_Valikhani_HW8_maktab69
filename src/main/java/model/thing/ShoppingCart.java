package model.thing;

import java.util.List;

public class ShoppingCart {
    private Integer id;
    private List<Product> products;
    private List<Integer> quantity;
    private Double totalPrice;

    public ShoppingCart(Integer id, Double totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
    }

    public ShoppingCart(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ShoppingCart(Integer id, List<Product> products, List<Integer> quantity, Double totalPrice) {
        this.id = id;
        this.products = products;
        this.quantity = quantity;
        this.totalPrice = totalPrice;


    }
}
