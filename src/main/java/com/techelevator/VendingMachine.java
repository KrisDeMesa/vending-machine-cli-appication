package com.techelevator;

import com.techelevator.util.ConsoleUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private String slotLocation;
    private String productName;
    private BigDecimal price = BigDecimal.ZERO;
    private String itemType;
    private BigDecimal creditBalance = BigDecimal.ZERO;
    private BigDecimal dollar = BigDecimal.ONE;
    private BigDecimal ten = BigDecimal.TEN;
    private BigDecimal five = BigDecimal.valueOf(5);
    private int quantity = 5;
    private Scanner input;
    private File writeLog = new File("log.txt");
    private TransactionDateTime dateTime = new TransactionDateTime();



    public BigDecimal getCreditBalance() {
        return creditBalance;
    }

    public VendingMachine(Scanner input) {
        this.input = input;
    }

    public void setCreditBalance() {
        this.creditBalance = BigDecimal.ZERO;

    }

    // method here to
    public boolean purchase(Product choice) {
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
    public BigDecimal loadCredits(Scanner input) {
        String choice = input.nextLine();
        int selection = Integer.parseInt(choice);
        try (PrintWriter salesLog = new PrintWriter(new FileOutputStream(writeLog, true))) {
            if (selection == 1) {
                creditBalance = creditBalance.add(dollar);
                salesLog.println(dateTime.getDateTimeOfTransaction() + " FEED MONEY: " + "$1.00 " + "$" + getCreditBalance().setScale(2));

            } else if (selection == 5) {
                creditBalance = creditBalance.add(five);
                salesLog.println(dateTime.getDateTimeOfTransaction() + " FEED MONEY: " + "$5.00 " + "$" + getCreditBalance().setScale(2));

            } else if (selection == 10) {
                creditBalance = creditBalance.add(ten);
                salesLog.println(dateTime.getDateTimeOfTransaction() + " FEED MONEY: " + "$10.00 " + "$" + getCreditBalance().setScale(2));

            } else {
                ConsoleUtility.printError("***************");
                ConsoleUtility.printError("Invalid amount!");
                ConsoleUtility.printError("***************");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }return creditBalance;
    }

    public BigDecimal newBalance(BigDecimal price) {
        creditBalance = creditBalance.subtract(price);
        return creditBalance;
    }
}






