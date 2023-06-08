package com.techelevator;

public class Product {

    private String slotLocation;
    private String productName;
    private double price;
    private String itemType;


    public Product(String slotLocation, String productName, double price, String itemType) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.itemType = itemType;
    }
    public Product(String productName, double price, String itemType) {
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

    @Override
    public String toString() {
        return "Product{" +
                "slotLocation='" + slotLocation + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", itemType='" + itemType + '\'' +
                '}';
    }
}
