package exercises.bankingsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type;
    private double amount;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return """
                *************************************
                        TRANSACTION DETAILS:
                ====================================
                TYPE: %s
                AMOUNT: $%.2f
                DATE AND TIME: %s
                ************************************
                """.formatted(type, amount, dateTime.format(formatter));
    }
}
