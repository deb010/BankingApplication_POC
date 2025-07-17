package com.example;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BankingUI extends JFrame {
	private JTextField nameField, accountNumberField, amountField;
	private JPasswordField passwordField;
	private JComboBox<String> accountTypeComboBox;
	private JTextArea outputArea;
	private final BankingController controller = new BankingController();

	public BankingUI() {
		setTitle("Secure Banking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setLayout(null);
		setLocationRelativeTo(null);

		addLabel("Account Type:", 30, 20);
		accountTypeComboBox = new JComboBox<>(new String[] { "Savings", "Current" });
		accountTypeComboBox.setBounds(160, 20, 150, 25);
		add(accountTypeComboBox);

		addLabel("Account Number:", 30, 60);
		accountNumberField = addTextField(160, 60);

		addLabel("Account Holder Name:", 30, 100);
		nameField = addTextField(160, 100);

		addLabel("Password:", 30, 140);
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 140, 150, 25);
		add(passwordField);

		addLabel("Amount:", 30, 180);
		amountField = addTextField(160, 180);

		// Buttons with exception handling
		addButton("Create Account", 30, 220, e -> run(
				() -> controller.createAccount(getAccountType(), getAccountNumber(), getName(), getPassword())));

		addButton("Deposit", 190, 220,
				e -> run(() -> controller.deposit(getAccountNumber(), getName(), getPassword(), getAmount())));

		addButton("Withdraw", 300, 220,
				e -> run(() -> controller.withdraw(getAccountNumber(), getName(), getPassword(), getAmount())));

		addButton("Show Balance", 30, 260,
				e -> run(() -> controller.showBalance(getAccountNumber(), getName(), getPassword()))).setSize(370, 30);

		outputArea = new JTextArea();
		outputArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(outputArea);
		scrollPane.setBounds(30, 310, 400, 130);
		add(scrollPane);
	}

	// Run wrapper to handle BankingException
	private void run(Action action) {
		try {
			outputArea.setText(action.execute());
		} catch (BankingException e) {
			outputArea.setText("..... " + e.getMessage());
		}
	}

	// Functional interface
	@FunctionalInterface
	interface Action {
		String execute() throws BankingException;
	}

	// Utility methods
	private JLabel addLabel(String text, int x, int y) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, 150, 25);
		add(label);
		return label;
	}

	private JTextField addTextField(int x, int y) {
		JTextField field = new JTextField();
		field.setBounds(x, y, 150, 25);
		add(field);
		return field;
	}

	private JButton addButton(String text, int x, int y, java.awt.event.ActionListener listener) {
		JButton btn = new JButton(text);
		btn.setBounds(x, y, 150, 30);
		btn.addActionListener(listener);
		add(btn);
		return btn;
	}

	private String getAccountNumber() {
		return accountNumberField.getText().trim();
	}

	public String getName() {
		return nameField.getText().trim();
	}

	private String getPassword() {
		return new String(passwordField.getPassword()).trim();
	}

	private String getAmount() {
		return amountField.getText().trim();
	}

	private String getAccountType() {
		return accountTypeComboBox.getSelectedItem().toString();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new BankingUI().setVisible(true));
	}
}
