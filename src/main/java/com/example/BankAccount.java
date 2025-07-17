package com.example;

public abstract class BankAccount {
	protected String accountNumber;
	protected String accountHolderName;
	protected double balance;

	public BankAccount(String accNumber, String holderName, double initialBalance) {
		this.accountNumber = accNumber;
		this.accountHolderName = holderName;
		this.balance = initialBalance;
	}

	public void deposit(double amount) {
		balance += amount;
		System.out.println("Deposited INR " + amount + " | New Balance: INR " + balance);
	}

	public void showBalance() {
		System.out.println("Account Holder: " + accountHolderName);
		System.out.println("Account Number: " + accountNumber);
		System.out.println("Current Balance: INR " + balance);
	}

	public abstract void withdraw(double amount);
}
