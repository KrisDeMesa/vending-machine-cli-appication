package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    private String slotLocation;
    private String productName;
    private double price;
    private String itemType;
    private double creditBalance;
    private Scanner input;

    private List<VendingItems> vendingItems = new ArrayList<>();


    public double getCreditBalance() {
        return creditBalance;
    }
    public VendingMachine(Scanner input) {
        this.input = input;
    }
    public void displayItems() {
        System.out.println("DISPLAYING ITEMS!!");
    }
    public double loadCredits(double deposit) {
        creditBalance += deposit;
        return creditBalance;
    }










}
