package JAVAASSIGNMENT2;

import java.math.BigDecimal;
import java.util.*;

public class Wallet {
    private final UUID id;
    private final User owner;
    private BigDecimal balance;
    private final List<StatementEntry> statements;

    public Wallet(User owner) {
        this.id = UUID.randomUUID();
        this.owner = owner;
        this.balance = BigDecimal.ZERO;
        this.statements = new ArrayList<>();
    }

    public UUID getId() { return id; }
    public User getOwner() { return owner; }
    public BigDecimal getBalance() { return balance; }
    public List<StatementEntry> getStatements() { return statements; }

    public void applyTopUp(TopUp t) {
        balance = balance.add(t.getAmount());
        statements.add(new StatementEntry(t.getTimestamp(), "Top-Up",
                "Added " + t.getAmount(), t.getAmount(), balance));
    }

    public void applyPayment(Payment p) {
        balance = balance.subtract(p.getAmount());
        statements.add(new StatementEntry(p.getTimestamp(), "Payment",
                "Paid to " + p.getMerchant(), p.getAmount().negate(), balance));
    }

    public void applyTransferOut(Transfer t) {
        balance = balance.subtract(t.getAmount());
        statements.add(new StatementEntry(t.getTimestamp(), "Transfer Out",
                "Sent to " + t.getToName(), t.getAmount().negate(), balance));
    }

    public void applyTransferIn(Transfer t) {
        balance = balance.add(t.getAmount());
        statements.add(new StatementEntry(t.getTimestamp(), "Transfer In",
                "Received from " + t.getFromName(), t.getAmount(), balance));
    }

    public void applyRefund(Refund r) {
        balance = balance.add(r.getAmount());
        statements.add(new StatementEntry(r.getTimestamp(), "Refund",
                "Refund for txn " + r.getOriginalTxnId(), r.getAmount(), balance));
    }

    @Override
    public String toString() {
        return "Wallet of " + owner.getName() + " | Balance: " + balance;
    }
}
