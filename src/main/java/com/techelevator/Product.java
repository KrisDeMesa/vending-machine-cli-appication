package com.techelevator;

public class Product {

    private String slotLocation;
    private String productName;
    private double price;
    private String itemType;
    private int quantity;

    public Product(String slotLocation, String productName, double price, String itemType, int quantity) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.itemType = itemType;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "slotLocation='" + slotLocation + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", itemType='" + itemType + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
