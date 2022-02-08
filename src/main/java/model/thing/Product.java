package model.thing;

public class Product {
    private Integer id;
    private String productName;
    private String description;
    private Double price;
    private Integer stock;
    private Category category;

    public Product(Integer id, String productName, String description, Double price) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

    public Product(String productName, String description, Double price, Integer stock) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Product(Integer id, String productName, String description,
                   Double price, Integer stock, Category category) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Product(String productName, String description, Double price, Integer stock, Category category) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public Product(String productName, String description, Double price, Integer stock,Integer id) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
