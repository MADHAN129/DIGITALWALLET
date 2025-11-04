package JAVAASSIGNMENT2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transfer {
    private final UUID id;
    private final String fromName;
    private final String toName;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;

    public Transfer(String fromName, String toName, BigDecimal amount) {
        this.id = UUID.randomUUID();
        this.fromName = fromName;
        this.toName = toName;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getFromName() { return fromName; }
    public String getToName() { return toName; }
    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
