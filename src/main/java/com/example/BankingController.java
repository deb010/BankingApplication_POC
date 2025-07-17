package com.example;

import java.util.HashMap;
import java.util.Map;

public class BankingController {
	private final Map<String, UserAccount> accounts = new HashMap<>();

	public String createAccount(String type, String accNo, String name, String password) throws BankingException {
		if (accounts.containsKey(accNo)) {
			throw new BankingException("Account already exists.");
		}

		BankAccount newAcc = type.equalsIgnoreCase("Savings") ? new SavingsAccount(accNo, name, 5000)
				: new CurrentAccount(accNo, name, 5000);

		accounts.put(accNo, new UserAccount(newAcc, name, password));
		return " Account created successfully!";
	}

	public String deposit(String accNo, String name, String password, String amtStr) throws BankingException {
		UserAccount user = validateUser(accNo, name, password);
		double amt;

		try {
			amt = Double.parseDouble(amtStr);
		} catch (NumberFormatException e) {
			throw new BankingException("Invalid amount entered.");
		}

		if (amt <= 0) {
			throw new BankingException("Deposit amount must be greater than 0.");
		}

		user.bankAccount.deposit(amt);
		return " Deposited INR " + amt;
	}

	public String withdraw(String accNo, String name, String password, String amtStr) throws BankingException {
		UserAccount user = validateUser(accNo, name, password);
		double amt;

		try {
			amt = Double.parseDouble(amtStr);
		} catch (NumberFormatException e) {
			throw new BankingException("Invalid amount entered.");
		}

		if (amt <= 0) {
			throw new BankingException("Withdrawal amount must be greater than 0.");
		}

		user.bankAccount.withdraw(amt);
		return " Withdrawn INR " + amt;
	}

	public String showBalance(String accNo, String name, String password) throws BankingException {
		UserAccount user = validateUser(accNo,name,password);
		BankAccount acc = user.bankAccount;

		return "---------------- ACCOUNT DETAILS --------------- \n" + "Account Holder: " + acc.accountHolderName + "\n" + "Account Number: "
				+ acc.accountNumber + "\n" + "Balance: INR " + acc.balance;

		//return "------------ ACCOUNT DETAILS -------------- \n" + showBalance("Account Number : "+ acc.accountNumber,"Account Holder Name : "+ acc.accountHolderName, "Balance : "+ String.valueOf(acc.balance));
	}

	private UserAccount validateUser(String accNo, String name, String password) throws BankingException {
		return accounts.values().stream().filter(u -> u.bankAccount.accountNumber.equals(accNo))
				.filter(u -> u.name.equals(name)).filter(u -> u.password.equals(password)).findFirst()
				.orElseThrow(() -> new BankingException("Invalid Credentials or Account Not Found."));
	}
}
