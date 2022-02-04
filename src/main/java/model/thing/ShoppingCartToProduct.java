package model.thing;

public class ShoppingCartToProduct {
    private Integer id;
    private ShoppingCart shoppingCart;
    private Product product;
    private Integer quantity;
    private Double totalPrice;

    public ShoppingCartToProduct(Integer id, ShoppingCart shoppingCart,
                                 Product product, Integer quantity, Double totalPrice) {
        this.id = id;
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
