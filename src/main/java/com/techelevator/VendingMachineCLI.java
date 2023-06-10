package com.techelevator;

import com.techelevator.util.ConsoleUtility;

import javax.swing.*;
import java.awt.color.ProfileDataException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
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

	public VendingMachineCLI(Scanner userInput) {
		this.userInput = userInput;
	}

	public void returnChange() {
		BigDecimal quarter = BigDecimal.valueOf(.25);
		BigDecimal dime = BigDecimal.valueOf(.10);
		BigDecimal nickel = BigDecimal.valueOf(.05);
		BigDecimal change = vend.getCreditBalance();
		change.divide(quarter);





		/* quarter = .25
			dimes = .10
			nickel = .5
			balance / .25 = whole num quarters
			remainder  gets divided / .10 check how many for times. remainder
			remainder / .5 count how many = dimes.



		 */
	}


//	public void salesLog(File file) {
//		try (PrintWriter salesLog = new PrintWriter(new FileOutputStream(writeLog, true))) {
//			salesLog.println("Product:" + get.getProductName() + "$" + getInfo.getPrice() + "$" + vend.getCreditBalance());
//
//
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		}
//	}
		// Calls display method to display items.
		public void displayItems() {
		Map<String, Product> productPrint = selectProduct.getProducts();
		for (Map.Entry<String, Product> element : productPrint.entrySet()) {
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
						getInfo.updateQuantity(1);
						System.out.println("*Product: " + getInfo.getProductName() + " | Price: $" + getInfo.getPrice() + " | Balance: $" + vend.getCreditBalance() + "*");
						if (getInfo.getItemType().equals("Gum")) {
							System.out.println("Chew Chew, Yum!");

						} else if (getInfo.getItemType().equals("Chip")) {
							System.out.println("Crunch Crunch, Yum!");

						} else if (getInfo.getItemType().equals("Candy")) {
							System.out.println("Munch Munch, Yum!");

						} else {
							System.out.println("Glug Glug, Yum!");
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
			try {
				System.out.println(ConsoleUtility.ANSI_CYAN);
				System.out.println("(1) Display Vending Machine Items");
				System.out.println("(2) Purchase");
				System.out.println("(3) Exit");
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
						System.out.println("Current Money Provided: $" + vend.getCreditBalance());
						System.out.println("(1) Feed Money");
						System.out.println("(2) Select Product");
						System.out.println("(3) Finish Transaction");
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
							thankYou();
							break;
						} else {
							warning();
							run();
						}
					}
					run();
				} else if (input == 3) {
					// menu cli exits
					System.out.println("Exit");
					loopCheck = false;
				} else {
					// Invalid input
					warning();

				}
			} catch (NumberFormatException ex) {
				warning();
			}
		}
	}
	// Thank you banner
	public void thankYou() {
		System.out.println(ConsoleUtility.ANSI_BLUE);
		System.out.println("You received $:" + vend.getCreditBalance());
		System.out.println("************ Thank you ************");
		System.out.println("                                   ");
		System.out.println("                                   ");
		System.out.println("                                   ");
	}
	// Welcome banner
	public void welcome() {
		System.out.println(ConsoleUtility.ANSI_BLUE);
		System.out.println("                                   ");
		System.out.println("************* Welcome *************");
		System.out.println("                                   ");
	}
	// Warning graphics
	public static void warning() {
		ConsoleUtility.printError("***************");
		ConsoleUtility.printError("Invalid input!!");
		ConsoleUtility.printError("***************");
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		VendingMachineCLI cli = new VendingMachineCLI(input);
		cli.run();
	}
}

