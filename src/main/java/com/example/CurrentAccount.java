package com.example;

public class CurrentAccount extends BankAccount {
	private double overdraftLimit = 10000;

	public CurrentAccount(String accNumber, String holderName, double initialBalance) {
		super(accNumber, holderName, initialBalance);
	}

	@Override
	public void withdraw(double amount) {
		if ((balance + overdraftLimit) >= amount) {
			balance -= amount;
			System.out.println("Withdrawn INR " + amount + " | New Balance: INR " + balance);
		} else {
			System.out.println("Wanted to Withdrawn INR "+ amount +" Withdrawal failed! Overdraft limit exceeded INR " + overdraftLimit + "; Current Balance: " + balance);
		}
	}
}
