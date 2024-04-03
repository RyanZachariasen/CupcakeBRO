package app.entities;

public class Bottoms {
    private int bottomsID;
    private String bottomsName;
    private int bottomsPrice;
    public Bottoms(int bottomsID, String bottomsName, int bottomsPrice ){
    this.bottomsID=bottomsID;
    this.bottomsName=bottomsName;
    this.bottomsPrice=bottomsPrice;

    }

    public int getBottomsID() {
        return bottomsID;
    }

    public void setBottomsID(int bottomsID) {
        this.bottomsID = bottomsID;
    }

    public String getBottomsName() {
        return bottomsName;
    }

    public void setBottomsName(String bottomsName) {
        this.bottomsName = bottomsName;
    }

    public int getBottomsPrice() {
        return bottomsPrice;
    }

    public void setBottomsPrice(int bottomsPrice) {
        this.bottomsPrice = bottomsPrice;
    }

    @Override
    public String toString() {
        return "Bottoms ID "+bottomsID+" Bottoms name : "+bottomsName+" Bottoms Price : "+bottomsPrice;
    }
}
