package exercises.bankingsystem;

import exercises.bankingsystem.exceptions.InsufficientFundsException;

import java.util.ArrayList;
import java.util.List;


public class Account {
    private String accountHolder;
    private double balance;
    private int accountId;
    private List<Transaction> transactionHistory;

    public Account(String accountHolder, int accountId) {
        this.accountHolder = accountHolder;
        this.accountId = accountId;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public synchronized void deposit(double amount) {

        try {
            if (amount < 0)
                throw new IllegalArgumentException("Amount to deposit should be non-negative.");

            setBalance(getBalance() + amount);
            transactionHistory.add(new Transaction("Deposit", amount));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public synchronized void withdraw(double amount) {
        try {
            if (amount < 0)
                throw new IllegalArgumentException("Amount to withdraw should be non-negative.");
            if (getBalance() >= amount) {
                setBalance(getBalance() - amount);
                transactionHistory.add(new Transaction("Withdrawal", amount));
            } else throw new InsufficientFundsException("Insuficient funds.");

        } catch (IllegalArgumentException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public synchronized void accountStatement() {
        System.out.println(this);
        transactionHistory.forEach(System.out::println);

    }


    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }


    @Override
    public String toString() {
        return """
                *************************************
                           CLIENT DETAILS:
                ====================================
                ACCOUNT HOLDER: %s.
                ACCOUNT ID: %s.
                ACCOUNT BALANCE: $%.2f.
                """.formatted(getAccountHolder(), getAccountId(), getBalance());
    }
}
