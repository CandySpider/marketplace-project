package r.model;

public class Product {
    private int productId;
    private String name;
    private int quantity;
    private String description;
    private int orderId;
    private String category;

    public String getCategory() {
        return category;
    }

    public int getOrderId() {
        return orderId;
    }

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

    public Product(int productId,int orderId,String category,String name, int quantity, String description) {
        this.productId = productId;
        this.orderId=orderId;
        this.category=category;
        this.name = name;
        this.quantity = quantity;
        this.description = description;

    }

    @Override
    public String toString() {
        return "{" +
                " \"productId\": " + productId +
                ", \"orderId\": "  + orderId  +
                ", \"category\": " + "\"" + category + "\"" +
                ", \"name\": " + "\"" + name + "\"" +
                ", \"quantity\": " + "\"" + quantity + "\"" +
                ", \"description\": " + "\"" + description + "\""  +
                '}';
    }
}