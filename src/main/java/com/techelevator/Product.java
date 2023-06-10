package com.techelevator;

import java.math.BigDecimal;

public class Product {

    private String slotLocation;
    private String productName;
    private BigDecimal price = BigDecimal.ZERO;
    private String itemType;
    private int quantity = 5;

    public Product(String slotLocation, String productName, BigDecimal price, String itemType, int quantity) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public String getItemType() {
        return itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public int updateQuantity(int quantity) {
        this.quantity -= quantity;
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
