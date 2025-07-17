package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CurrentAccountTest {

    private CurrentAccount currentAccount;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        currentAccount = new CurrentAccount("CA001", "Debjyoti Sadhukhan", 2000.0);
        System.setOut(new PrintStream(outContent)); // Redirect System.out
    }

    @Test
    void testWithdrawWithinOverdraftLimit() {
        currentAccount.withdraw(2500.0); // Withdraws 2500, balance becomes -500 (within -10000 limit)
        assertEquals(-500.0, currentAccount.balance, "Withdrawal within overdraft should update balance.");
        assertTrue(outContent.toString().contains("Withdrawn INR 2500.0 | New Balance: INR -500.0"), "Withdrawal success message should be printed.");
    }

    @Test
    void testWithdrawExceedingOverdraftLimit() {
        currentAccount.withdraw(15000.0); // Withdraws 15000, balance would be 2000 - 15000 = -13000, which exceeds -10000
        assertEquals(2000.0, currentAccount.balance, "Withdrawal exceeding overdraft should not change balance.");
        assertTrue(outContent.toString().contains("Withdrawal failed! Overdraft limit exceeded INR 10000.0; Current Balance: 2000.0"), "Withdrawal failure message should be printed.");
    }

    @Test
    void testWithdrawExactlyAtOverdraftLimit() {
        currentAccount.withdraw(12000.0); // Withdraws 12000, balance becomes -10000 (exactly at limit)
        assertEquals(-10000.0, currentAccount.balance, "Withdrawal exactly at overdraft limit should update balance.");
        assertTrue(outContent.toString().contains("Withdrawn INR 12000.0 | New Balance: INR -10000.0"), "Withdrawal success message should be printed.");
    }

    // Restore System.out after each test
    @org.junit.jupiter.api.AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
}