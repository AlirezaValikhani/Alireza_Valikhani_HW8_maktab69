package model.human;

import model.thing.ShoppingCart;

public class Customer extends User {
    private String address;
    private Double balance;
    private ShoppingCart shoppingCart;

    public Customer(Integer id, String firstName,
                    String lastname, String userName,
                    String password, String email,
                    String address, Double balance, ShoppingCart shoppingCart) {
        super(id, firstName, lastname, userName, password, email);
        this.address = address;
        this.balance = balance;
        this.shoppingCart = shoppingCart;
    }

    public Customer(String firstName, String lastname, String userName,
                    String password, String email, String address,
                    Double balance, ShoppingCart shoppingCart) {
        super(firstName, lastname, userName, password, email);
        this.address = address;
        this.balance = balance;
        this.shoppingCart = shoppingCart;
    }

    public Customer(Integer id, String firstName, String lastname, String userName, String password, Double balance) {
        super(id, firstName, lastname, userName, password);
        this.balance = balance;
    }

    public Customer(Integer id) {
        super(id);
    }

    public Customer(Double balance) {
        super();
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
