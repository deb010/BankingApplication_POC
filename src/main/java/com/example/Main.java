package com.example;

public class Main {
	public static void main(String[] args) {
		BankAccount savings = new SavingsAccount("SA123", "Debjyoti Sadhukhan", 5000);
		BankAccount current = new CurrentAccount("CA456", "Tisa Roy", 3000);

		System.out.println("\n--- SAVINGS ACCOUNT ---");
		savings.showBalance();
		savings.deposit(1500);
		savings.withdraw(4000);
		savings.withdraw(3000);

		System.out.println("\n--- CURRENT ACCOUNT ---");
		current.showBalance();
		current.deposit(2000);
		current.withdraw(12000);
		current.withdraw(5000);
	}
}
