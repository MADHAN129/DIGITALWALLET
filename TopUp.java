package JAVAASSIGNMENT2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TopUp {
    private final UUID id;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;

    public TopUp(BigDecimal amount) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
