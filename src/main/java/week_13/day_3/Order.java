package week_13.day_3;

public class Order {
    private int id;
    private int productId;
    private int quantity;
    private int customerDetails;

    public Order() {}

    // Constructor without the id parameter
    public Order(int productId, int quantity, int customerDetails) {
        this.productId = productId;
        this.quantity = quantity;
        this.customerDetails = customerDetails;
    }

    public Order(int id, int productId, int quantity, int customerDetails) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.customerDetails = customerDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(int customerDetails) {
        this.customerDetails = customerDetails;
    }
}

