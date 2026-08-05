package model;

public class OrderItem {
    private int id;
    private int orderId;
    private String itemName;
    private int quantity;
    private double price;
    private String note;

    public OrderItem() {}

    public OrderItem(int id, int orderId, String itemName, int quantity, double price, String note) {
        this.id = id;
        this.orderId = orderId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.note = note;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}