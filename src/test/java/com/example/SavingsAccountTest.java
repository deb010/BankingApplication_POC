package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {

    private SavingsAccount savingsAccount;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        savingsAccount = new SavingsAccount("SA001", "Debjyoti Sadhukhan", 1000.0);
        System.setOut(new PrintStream(outContent)); // Redirect System.out
    }

    @Test
    void testWithdrawWithinMinimumBalance() {
        savingsAccount.withdraw(300.0); // Balance becomes 700, which is >= 500
        assertEquals(700.0, savingsAccount.balance, "Withdrawal within minimum balance should update balance.");
        assertTrue(outContent.toString().contains("Withdrawn INR 300.0 | New Balance: INR 700.0"), "Withdrawal success message should be printed.");
    }

    @Test
    void testWithdrawExceedingMinimumBalance() {
        savingsAccount.withdraw(600.0); // Balance would become 400, which is < 500
        assertEquals(1000.0, savingsAccount.balance, "Withdrawal exceeding minimum balance should not change balance.");
        assertTrue(outContent.toString().contains("Withdrawal failed! Minimum balance of INR 500.0 must be maintained."), "Withdrawal failure message should be printed.");
    }

    @Test
    void testWithdrawExactlyToMinimumBalance() {
        savingsAccount.withdraw(500.0); // Balance becomes 500, which is >= 500
        assertEquals(500.0, savingsAccount.balance, "Withdrawal exactly to minimum balance should update balance.");
        assertTrue(outContent.toString().contains("Withdrawn INR 500.0 | New Balance: INR 500.0"), "Withdrawal success message should be printed.");
    }

    // Restore System.out after each test
    @org.junit.jupiter.api.AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
}