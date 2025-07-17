package com.example;

public class SavingsAccount extends BankAccount {
	private double minimumBalance = 500;

	public SavingsAccount(String accNumber, String holderName, double initialBalance) {
		super(accNumber, holderName, initialBalance);
	}

	@Override
	public void withdraw(double amount) {
		if ((balance - amount) >= minimumBalance) {
			balance -= amount;
			System.out.println("Withdrawn INR " + amount + " | New Balance: INR " + balance);
		} else {
			System.out.println("Wanted to Withdrawn INR "+ amount + " Withdrawal failed! Minimum balance of INR " + minimumBalance + " must be maintained.");
		}
	}
}
