package r.model;

public class Product {
    private int productId;
    private String name;
    private int quantity;
    private String description;

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public Product(int productId, String name, int quantity, String description) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                " \"productId\": " + productId +
                ", \"Name\": " + "\"" + name + "\"" +
                ", \"quantity\": " + "\"" + quantity + "\"" +
                ", \"description\": " + "\"" + description + "\""  +
                '}';
    }
}