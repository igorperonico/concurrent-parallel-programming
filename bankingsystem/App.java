package exercises.bankingsystem;

import java.util.ArrayList;
import java.util.List;

public class App {
    public void run() {
        Bank bank = new Bank();

        bank.openAccount("Igor Monteiro");
        bank.getAccountById(1).deposit(15000);

        bank.openAccount("João Silva");
        bank.getAccountById(2).deposit(1000);

        bank.openAccount("Tio Patinhas");
        bank.getAccountById(3).deposit(2000000000);

        List<CustomerThread> threads = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            CustomerThread thread = new CustomerThread(bank, i);
            threads.add(thread);
            thread.start();
        }

        threads.forEach(thread-> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        for (int i = 1; i <= 3; i++) {
            System.out.println("\n===================================");
            System.out.println("Transaction history for account " + i + ":");
            Account account = bank.getAccountById(i);
            account.accountStatement();
        }
    }
}
