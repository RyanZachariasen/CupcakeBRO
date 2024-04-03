package app.entities;

public class Toppings {
    private int toppingsID;
    private String toppingname;
    private int toppingprice;

    public Toppings(int toppingsID, String toppingname, int toppingprice) {
        this.toppingsID = toppingsID;
        this.toppingname = toppingname;
        this.toppingprice = toppingprice;
    }

    public int getToppingsID() {
        return toppingsID;
    }

    public String getToppingname() {
        return toppingname;
    }

    public int getToppingprice() {
        return toppingprice;
    }

    @Override
    public String toString() {
        return "Toppings{" +
                "toppingsID=" + toppingsID +
                ", toppingname='" + toppingname + '\'' +
                ", toppingprice=" + toppingprice +
                '}';
    }
}
