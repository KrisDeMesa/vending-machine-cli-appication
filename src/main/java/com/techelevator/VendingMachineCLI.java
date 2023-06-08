package com.techelevator;

import java.util.Scanner;

public class VendingMachineCLI {

	// Use this for keyboard input - it is initialized in the constructor
	private Scanner userInput;

	public VendingMachineCLI(Scanner userInput) {
		this.userInput = userInput;
	}

	public void run() {
		VendingItems test = new VendingItems(userInput);
		test.convertItems();
		// Add a loop here for your menu
		boolean loopCheck = true;
		while (loopCheck) {
			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");
			String choice = userInput.nextLine();
			int input = Integer.parseInt(choice);
			VendingMachine vend = new VendingMachine(userInput);
			// if input is 1
			if (input == 1) {
				// call vending machine method Display items when input is 1
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
						vend.loadCredits(2.0);
					} else if (purchaseInput == 2) {
						// goes into sub menu product
					} else if (purchaseInput == 3) {
						// finish transaction
						break;
					} else {
						System.out.println("Invalid input!");
					}
				}
			} else if (input == 3) {
				// menu cli exits
				System.out.println("Exit");
				loopCheck = false;
			} else {
				// Invalid input
				System.out.println("Invalid input");
			}

		}
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		VendingMachineCLI cli = new VendingMachineCLI(input);
		cli.run();
	}
}
