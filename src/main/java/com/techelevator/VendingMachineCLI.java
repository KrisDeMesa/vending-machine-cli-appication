package com.techelevator;

import com.techelevator.util.ConsoleUtility;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.color.ProfileDataException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.Bidi;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	// Use this for keyboard input - it is initialized in the constructor
	private Scanner userInput;

	private VendingMachine vend = new VendingMachine(userInput);
	private VendingItems selectProduct = new VendingItems(userInput);
	private Map<String, Product> productMap = selectProduct.getProducts();
	private File writeLog = new File("log.txt");
	private TransactionDateTime dateTime = new TransactionDateTime();

	public VendingMachineCLI(Scanner userInput) {
		this.userInput = userInput;
	}
	// Method gets current balance and converts it to coins
	public void returnChange() {
		BigDecimal[] quartersWithDimeRemainder;
		BigDecimal[] dimesWithNickelRemainder;
		BigDecimal quarter = BigDecimal.valueOf(.25);
		BigDecimal dime = BigDecimal.valueOf(.1);
		BigDecimal nickel = BigDecimal.valueOf(.05);
		BigDecimal change = vend.getCreditBalance();
		quartersWithDimeRemainder = change.divideAndRemainder(quarter);
		dimesWithNickelRemainder = quartersWithDimeRemainder[1].divideAndRemainder(dime);
		BigDecimal nickelChange = dimesWithNickelRemainder[1].divide(nickel);
		System.out.println(ConsoleUtility.ANSI_BLUE);
		System.out.println("*******************************************");
		System.out.println("                You received               ");
		System.out.println("*******************************************");
		System.out.println("** Quarters: " + quartersWithDimeRemainder[0].toPlainString() + " | Dimes: " + dimesWithNickelRemainder[0].setScale(0).toPlainString() + " | Nickels : " + nickelChange.toPlainString() + " **");
		try (PrintWriter salesLog = new PrintWriter(new FileOutputStream(writeLog, true))) {
			salesLog.println(dateTime.getDateTimeOfTransaction() + " GIVE CHANGE: " + vend.getCreditBalance() + " $0.00");

		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		vend.setCreditBalance();

		System.out.println("Credit balance: $" + vend.getCreditBalance());

	}
	// Write and append a sales log of all the sales transactions
	public void salesLog(Product sales) {
		Product salesInfo = sales;
		try (PrintWriter salesLog = new PrintWriter(new FileOutputStream(writeLog, true))) {
			salesLog.println(
					dateTime.getDateTimeOfTransaction() + " "
					+ salesInfo.getProductName() + " "
					+ salesInfo.getSlotLocation() +  " $"
					+ salesInfo.getPrice() + " $"
					+ vend.getCreditBalance());

		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}


		// Calls display method to display items.
		public void displayItems() {
		Map<String, Product> productPrint = selectProduct.getProducts();
		for (Map.Entry<String, Product> element : productPrint.entrySet()) {
			System.out.println(ConsoleUtility.ANSI_BLUE);
			System.out.println(element);
		}
	}


	// Gets input from user to validate choice
	public Product chooseItem(Scanner input) {
		System.out.println("Choose an item.");
		String choice = input.nextLine();
		try {
			if (productMap.containsKey(choice.toUpperCase())) {
				Product getInfo = productMap.get(choice.toUpperCase());
				if (getInfo.getQuantity() > 0) {
					if (vend.purchase(getInfo)) {
						vend.newBalance(getInfo.getPrice());
						salesLog(getInfo);
						getInfo.updateQuantity(1);
						System.out.println("*****                                 *****");
						System.out.println("***** Vending machine dispensing..... *****");
						System.out.println("*****                                 *****");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException exception) {
							System.out.println("Vending error!");
						}
						System.out.println("");
						System.out.println(
								"*Product: " + getInfo.getProductName()
								+ " | Price: $" + getInfo.getPrice()
								+ " | Balance: $" + vend.getCreditBalance()
								+ " | Quantity: x 1*");
						if (getInfo.getItemType().equals("Gum")) {
							System.out.println("			   ");
							System.out.println("***************");
							System.out.println("Chew Chew, Yum!");
							System.out.println("***************");
							System.out.println("			   ");

						} else if (getInfo.getItemType().equals("Chip")) {
							System.out.println("			       ");
							System.out.println("*******************");
							System.out.println("Crunch Crunch, Yum!");
							System.out.println("*******************");
							System.out.println("			       ");

						} else if (getInfo.getItemType().equals("Candy")) {
							System.out.println("			     ");
							System.out.println("*****************");
							System.out.println("Munch Munch, Yum!");
							System.out.println("*****************");

						} else {
							System.out.println("			   ");
							System.out.println("***************");
							System.out.println("Glug Glug, Yum!");
							System.out.println("***************");
						}
					} else {
						System.out.println("Not enough money, insert more cash");
					}
				} else {
					ConsoleUtility.printError("****************");
					ConsoleUtility.printError("Item is sold out");
					ConsoleUtility.printError("****************");
				}
			} else {
				ConsoleUtility.printError("*********************");
				ConsoleUtility.printError("Product doesn't exit.");
				ConsoleUtility.printError("*********************");
			}
		} catch (NumberFormatException ex) {
			ConsoleUtility.printError("*********************");
			ConsoleUtility.printError("Product doesn't exit.");
			ConsoleUtility.printError("*********************");
		}
		return null;
	}

	public void run() {
		// Add a loop here for your menu
		boolean loopCheck = true;
		while (loopCheck) {
			welcome();
			mainMenu();
			try {
				String choice = userInput.nextLine();
				int input = Integer.parseInt(choice);
				//VendingMachine vend = new VendingMachine(userInput);
				// if input is 1
				if (input == 1) {
					// call vending machine method Display items when input is 1
					displayItems();
				} else if (input == 2) {
					// vending machine sub menu
					loopCheck = false;
					boolean purchaseLoop = true;
					while (purchaseLoop) {
						subMenu();
						String purchaseMenu = userInput.nextLine();
						int purchaseInput = Integer.parseInt(purchaseMenu);
						if (purchaseInput == 1) {
							System.out.println("Select an amount: $1, $5, $10");
							vend.loadCredits(userInput);
						} else if (purchaseInput == 2) {
							boolean selectMenu = true;
							displayItems();
							chooseItem(userInput);
							// goes into sub menu product
						} else if (purchaseInput == 3) {
							// finish transaction
							returnChange();
							thankYou();
							break;
						} else {
							warning();
						}
					}
					run();
				} else if (input == 3) {
					System.out.println("*********** Exiting... ***********");
					// menu cli exits
					try {
						Thread.sleep(2000);

					} catch (InterruptedException exception) {
						System.out.println("Vending error!");
					}
					loopCheck = false;
				} else {
					// Invalid input
					warning();
					run();

				}
			} catch (NumberFormatException ex) {
				loopCheck = true;
				warning();
			}
		}
	}
	// Thank you banner
	public void thankYou() {
		System.out.println(ConsoleUtility.ANSI_BLUE);
		System.out.println("**************** THANK YOU ****************");
		System.out.println("                                           ");
		System.out.println("                                           ");
		System.out.println("                                  		   ");
	}
	// Welcome banner
	public void welcome() {
		System.out.println(ConsoleUtility.ANSI_BLUE);
		System.out.println("                                   		   ");
		System.out.println("***************** WELCOME *****************");
		System.out.println("                                  		   ");
	}
	// Warning graphics
	public static void warning() {
		ConsoleUtility.printError("***************");
		ConsoleUtility.printError("Invalid input!!");
		ConsoleUtility.printError("***************");
	}
	public void subMenu() {
		System.out.println(ConsoleUtility.ANSI_BLUE);
		System.out.println("********************************");
		System.out.println("Current Money Provided: $" + vend.getCreditBalance());
		System.out.println("********************************");
		System.out.println(ConsoleUtility.ANSI_CYAN);
		System.out.println("(1) Feed Money");
		System.out.println("(2) Select Product");
		System.out.println("(3) Finish Transaction");
	}
	public void mainMenu() {
		System.out.println(ConsoleUtility.ANSI_CYAN);
		System.out.println("(1) Display Vending Machine Items");
		System.out.println("(2) Purchase");
		System.out.println("(3) Exit");
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		VendingMachineCLI cli = new VendingMachineCLI(input);
		cli.run();
	}
}

