package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingItems {
    private String slotLocation;
    private String productName;
    private BigDecimal price;
    private String itemType;
    private Scanner input;
    private int quantity = 5;
    private String[] itemSplit = new String[4];
    private String split = "\\|";
    private TreeMap<String, Product> products = new TreeMap<>();
    public VendingItems(Scanner input) {
        this.input = input;
        convertItems();
    }
    public VendingItems(String slotLocation, String productName, BigDecimal price, String itemType) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.itemType = itemType;
    }
    public TreeMap<String, Product> getProducts() {
        return products;
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


    public void convertItems() {
        File items = new File("vendingmachine.csv");
        try (Scanner reader = new Scanner(items)) {
            while (reader.hasNextLine()) {
                String itemSplit = reader.nextLine();
                this.itemSplit = itemSplit.split(split);
                String slotLocation = this.itemSplit[0];
                String productName = this.itemSplit[1];
                BigDecimal price = new BigDecimal(this.itemSplit[2]);
                String itemType = this.itemSplit[3];
                Product newProducts = new Product(slotLocation, productName, price, itemType, quantity);
                products.put(newProducts.getSlotLocation(), newProducts);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Inventory error!");
        }
    }
}
