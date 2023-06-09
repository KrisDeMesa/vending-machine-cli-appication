package com.techelevator;

import com.techelevator.util.ConsoleUtility;

import java.awt.color.ProfileDataException;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	// Use this for keyboard input - it is initialized in the constructor
	private Scanner userInput;

	public VendingMachineCLI(Scanner userInput) {
		this.userInput = userInput;
	}

	public void displayItems() {
		VendingItems displayItems = new VendingItems(userInput);
		Map<String, Product> productPrint = displayItems.getProducts();
		for (Map.Entry<String, Product> element : productPrint.entrySet()) {
			System.out.println(element);
		}
	}
	public Product chooseItem(Scanner input) {
		VendingItems selectProduct = new VendingItems(userInput);
		Map<String, Product> productMap = selectProduct.getProducts();
		System.out.println("Choose an item.");
		String choice = input.nextLine();
		Product getInfo = selectProduct.getProducts().get(choice);
		try {
			if (productMap.containsKey(choice.toUpperCase())) {
				System.out.println(getInfo.getProductName() + " " + getInfo.getItemType() + " " + getInfo.getPrice() + " selected.");
			} else if (selectProduct.getQuantity() == 0) {
				System.out.println("Item is sold out");
			}
		} catch (NumberFormatException ex) {
			System.out.println("Product doesn't exit.");
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
				VendingMachine vend = new VendingMachine(userInput);
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
							vend.loadCredits(1);
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
							ConsoleUtility.printError("Invalid input!");
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
					ConsoleUtility.printError("Invalid input!");
				}
			}catch (NumberFormatException ex) {
				ConsoleUtility.printError("Invalid input!");
			}
		}
	}

	public void thankYou(){
		System.out.println(ConsoleUtility.ANSI_BLUE );
		System.out.println("************ Thank you ************");
		System.out.println("                                   ");
		System.out.println("                                   ");
		System.out.println("                                   ");
	}

	public void welcome(){
		System.out.println(ConsoleUtility.ANSI_BLUE  );
		System.out.println("                                   ");
		System.out.println("************* Welcome *************");
		System.out.println("                                   ");
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		VendingMachineCLI cli = new VendingMachineCLI(input);
		cli.run();

//		VendingItems items = new VendingItems(input);
////
////
//		Map<String, Product> productMap = items.getProducts();
//		Product retrieved = productMap.containsKey()
//		System.out.println(retrieved.);

//		System.out.println("We just retrieved a " + retrieved.getProductName() + " which cost $" + retrieved.getPrice());
	}
}
