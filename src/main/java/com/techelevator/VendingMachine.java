package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private String slotLocation;
    private String productName;
    private double price;
    private String itemType;
    private double creditBalance;
    private int quantity;
    private Scanner input;

    private List<VendingItems> vendingItems = new ArrayList<>();


    public double getCreditBalance() {
        return creditBalance;
    }
    public VendingMachine(Scanner input) {
        this.input = input;

    }
    public Product displayItems() {
        VendingItems displayItems = new VendingItems(input);
         Map<String, Product> productPrint = displayItems.getProducts();
         Product retrieved = productPrint.get("A1");
         return retrieved;
               //NEEDD LOOOOOPPPP ABAOVE
        // VendingItems items = new VendingItems(input);
//
//
//		Map<String, Product> productMap = items.getProducts();
//		Product retrieved = productMap.get("D2");
//		System.out.println(retrieved);

//		System.out.println("We just retrieved a " + retrieved.getProductName() + " which cost $" + retrieved.getPrice());

    }
    public double loadCredits(double deposit) {
        creditBalance += deposit;
        return creditBalance;
    }


}
