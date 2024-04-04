package app.entities;

public class Order {
    int orderID;
    int userID;
public Order(int orderID, int userID){
this.orderID = orderID;
this.userID = userID;
}

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                '}';
    }
}
