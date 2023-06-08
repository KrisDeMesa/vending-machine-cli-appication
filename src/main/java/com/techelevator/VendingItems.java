package com.techelevator;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VendingItems {
    private String slotLocation;
    private String productName;
    private double price;
    private String itemType;
    private Scanner input;
    private String[] itemSplit = new String[4];

    private int quantity = 5;

    private String split = "\\|";

    private String chip = "Chip";
    private String candy= "Candy";
    private String drink = "Drink";
    private String gum = "Gum";


    public VendingItems(Scanner input) {
        this.input = input;
    }
    public VendingItems(String slotLocation, String productName, double price, String itemType) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.itemType = itemType;
    }

    public void convertItems() {
        File items = new File("vendingmachine.csv");

        try (Scanner reader = new Scanner(items)) {
            while (reader.hasNextLine()) {
                String itemSplit = reader.nextLine();
                this.itemSplit = itemSplit.split(split);


            }
        } catch (FileNotFoundException e) {
            System.out.println("Inventory error!");
        }

    }




}
