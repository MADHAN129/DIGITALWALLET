package JAVAASSIGNMENT2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Refund {
    private final UUID id;
    private final BigDecimal amount;
    private final String originalTxnId;
    private final LocalDateTime timestamp;

    public Refund(BigDecimal amount, String originalTxnId) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.originalTxnId = originalTxnId;
        this.timestamp = LocalDateTime.now();
    }

    public BigDecimal getAmount() { return amount; }
    public String getOriginalTxnId() { return originalTxnId; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
