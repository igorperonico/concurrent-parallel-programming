package exercises.bankingsystem;

import exercises.bankingsystem.exceptions.AccountNotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {

    private Map<Integer, Account> accounts;
    private int accountId;

    public Bank() {
        this.accounts = new ConcurrentHashMap<>();
        this.accountId = 0;
    }

    public void openAccount(String accountHolder) {
        accountId++;
        accounts.put(accountId, new Account(accountHolder, accountId));
        System.out.println("Account opened with ID: " + accountId);
    }

    public void closeAccount(int accountId) {
        try {
            if (!accounts.containsKey(accountId))
                throw new AccountNotFoundException("Account with ID " + accountId + " does not exist.");
            accounts.remove(accountId);
            System.out.println("Account closed with ID: " + accountId);
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public Account getAccountById(int accountId) {
        try {
            if (!accounts.containsKey(accountId))
                throw new AccountNotFoundException("Account with ID " + accountId + " does not exist.");
            return accounts.get(accountId);

        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

}
