package JAVAASSIGNMENT2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StatementEntry {
    private final LocalDateTime timestamp;
    private final String type;
    private final String details;
    private final BigDecimal amount;
    private final BigDecimal balanceAfter;

    public StatementEntry(LocalDateTime ts, String type, String details, BigDecimal amount, BigDecimal balanceAfter) {
        this.timestamp = ts;
        this.type = type;
        this.details = details;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + type + " - " + details +
                " | Amount: " + amount + " | Balance: " + balanceAfter;
    }
}
