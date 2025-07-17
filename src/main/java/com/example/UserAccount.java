package com.example;

public class UserAccount {
	public BankAccount bankAccount;
	public String name;
	public String password;

	public UserAccount(BankAccount account, String name, String password) {
		this.bankAccount = account;
		this.name = name;
		this.password = password;
	}

	public boolean isAuthenticated(String inputName, String inputPassword) {
		return this.name.equals(inputName) && this.password.equals(inputPassword);
	}
}
