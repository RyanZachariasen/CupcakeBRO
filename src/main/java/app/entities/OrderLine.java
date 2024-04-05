package app.entities;

public class OrderLine {
    int orderlineID;
    int quantity;
    int toppingsID;
    int bottomsID ;
    int orderID;


    public OrderLine(int orderlineID, int quantity, int toppingsID, int bottomsID, int orderID) {
        this.orderlineID = orderlineID;
        this.quantity = quantity;
        this.toppingsID = toppingsID;
        this.bottomsID = bottomsID;
        this.orderID = orderID;
    }

    public int getOrderlineID() {
        return orderlineID;
    }

    public void setOrderlineID(int orderlineID) {
        this.orderlineID = orderlineID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getToppingsID() {
        return toppingsID;
    }

    public void setToppingsID(int toppingsID) {
        this.toppingsID = toppingsID;
    }

    public int getBottomsID() {
        return bottomsID;
    }

    public void setBottomsID(int bottomsID) {
        this.bottomsID = bottomsID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderlineID=" + orderlineID +
                ", quantity=" + quantity +
                ", toppingsID=" + toppingsID +
                ", bottomsID=" + bottomsID +
                ", orderID=" + orderID +
                '}';
    }
}
