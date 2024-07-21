package exercises.bankingsystem;

import java.util.Random;

public class CustomerThread extends Thread {

    private Bank bank;
    private int accountId;
    private Random random;

    public CustomerThread(Bank bank, int accountId) {
        this.bank = bank;
        this.accountId = accountId;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            int operation = random.nextInt(3);
            Account account = bank.getAccountById(accountId);
            double amount = random.nextDouble(1000) + 1;
            System.out.println("Customer " + Thread.currentThread().getName() + " is performing operation " + operation + " on account " + accountId + " :");

            switch (operation) {
                case 0:
                    account.deposit(amount);
                    System.out.println("Deposited $" + amount + " to account " + accountId);
                    break;
                case 1:
                    account.withdraw(amount);
                    System.out.println("Withdrew $" + amount + " from account " + accountId);
                    break;
                case 2:
                    Thread.currentThread().interrupt();
                    System.out.println("Interrupting thread " + Thread.currentThread().getName());
                    break;

            }

            try {
                Thread.sleep(random.nextInt(100) + 1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " is exiting.");

    }
}
