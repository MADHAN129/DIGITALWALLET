package JAVAASSIGNMENT2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {
    private final UUID id;
    private final String merchant;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;

    public Payment(String merchant, BigDecimal amount) {
        this.id = UUID.randomUUID();
        this.merchant = merchant;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getMerchant() { return merchant; }
    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
