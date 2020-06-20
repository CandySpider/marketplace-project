package r.model;

public class Order {
    private int orderId;
    private int clientId;
    private int staffId;
    private String status;

    public int getOrderId() {
        return orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getStaffId() {
        return staffId;
    }

    public String getStatus() {
        return status;
    }

    public Order(int orderId, int clientId, int staffId, String status) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.staffId = staffId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                " \"orderId\": " + orderId +
                ", \"clientId\": " + "\"" + clientId + "\"" +
                ", \"staffId\": " + "\"" + staffId + "\"" +
                ", \"status\": " + "\"" + status + "\""  +
                '}';
    }
}