package com.techelevator;

public class Products {

    private String slotLocation;
    private String productName;
    private double price;
    private String itemType;

    private Products[] product = new Products[]{};

    public Products(String slotLocation, String productName, double price, String itemType) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.itemType = itemType;
    }
    public Products(String productName, double price, String itemType) {
        this.productName = productName;
        this.price = price;
        this.itemType = itemType;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getItemType() {
        return itemType;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
