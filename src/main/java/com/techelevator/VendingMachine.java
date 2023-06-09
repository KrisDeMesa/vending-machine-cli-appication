package com.techelevator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private String slotLocation;
    private String productName;
    private BigDecimal price;
    private String itemType;
    private BigDecimal creditBalance;
    private int quantity;
    private Scanner input;




    public BigDecimal getCreditBalance() {
        return creditBalance;
    }
    public VendingMachine(Scanner input) {
        this.input = input;

    }
    // method here to
    public boolean purchase (Product choice) {
        boolean canBuy = false;
        if (choice.getPrice().compareTo(creditBalance) == 1) {
            canBuy = false;
            return canBuy;

        } else if (choice.getPrice().compareTo(creditBalance) == -1) {
            canBuy = true;
            return canBuy;

        } else if (choice.getPrice().compareTo(creditBalance) == 0) {
            canBuy = true;
            return canBuy;
        }
        return canBuy;

    }


               //NEEDD LOOOOOPPPP ABAOVE
        // VendingItems items = new VendingItems(input);
//
//
//		Map<String, Product> productMap = items.getProducts();
//		Product retrieved = productMap.get("D2");
//		System.out.println(retrieved);

//		System.out.println("We just retrieved a " + retrieved.getProductName() + " which cost $" + retrieved.getPrice());
    public BigDecimal loadCredits(BigDecimal deposit) {
        creditBalance = creditBalance.add(deposit);
        return creditBalance;
    }
    public BigDecimal newBalance(BigDecimal price){
        creditBalance = creditBalance.add(price);
        return creditBalance;
    }


}




