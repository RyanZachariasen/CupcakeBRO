package app.entities;

public class OrderLineDetails {
    private int quantity;
    private String toppingName;
    private int toppingPrice;
    private String bottomName;
    private int bottomPrice;
    private int totalPrice;

    public OrderLineDetails(int quantity, String toppingName, int toppingPrice, String bottomName, int bottomPrice, int totalPrice) {
        this.quantity = quantity;
        this.toppingName = toppingName;
        this.toppingPrice = toppingPrice;
        this.bottomName = bottomName;
        this.bottomPrice = bottomPrice;
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public int getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(int toppingPrice) {
        this.toppingPrice = toppingPrice;
    }

    public String getBottomName() {
        return bottomName;
    }

    public void setBottomName(String bottomName) {
        this.bottomName = bottomName;
    }

    public int getBottomPrice() {
        return bottomPrice;
    }

    public void setBottomPrice(int bottomPrice) {
        this.bottomPrice = bottomPrice;
    }
    public int getTotalPrice() {
        return quantity*(bottomPrice+toppingPrice);
    }

    @Override
    public String toString() {
        return "OrderLineDetails{" +
                "quantity=" + quantity +
                ", toppingName='" + toppingName + '\'' +
                ", toppingPrice=" + toppingPrice +
                ", bottomName='" + bottomName + '\'' +
                ", bottomPrice=" + bottomPrice +
                '}';
    }
}
