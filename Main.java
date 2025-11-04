package JAVAASSIGNMENT2;

import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Wallet> wallets = new ArrayList<>();

        System.out.println("=== DIGITAL WALLET SYSTEM ===");

        // Create initial user
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your email: ");
        String email = sc.nextLine();

        Wallet currentWallet = new Wallet(new User(name, email));
        wallets.add(currentWallet);

        System.out.println("\nWallet created for " + name + "! Balance: 0");

        int choice;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Top Up");
            System.out.println("2. Payment");
            System.out.println("3. Refund");
            System.out.println("4. Transfer");
            System.out.println("5. View Statements");
            System.out.println("6. Create Another Wallet");
            System.out.println("7. Switch Wallet");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter top-up amount: ");
                    BigDecimal amt = sc.nextBigDecimal();
                    sc.nextLine();
                    if (amt.compareTo(BigDecimal.ZERO) <= 0) {
                        System.out.println("Amount must be positive!");
                        break;
                    }
                    currentWallet.applyTopUp(new TopUp(amt));
                    System.out.println("✅ Top-up successful! New balance: " + currentWallet.getBalance());
                }
                case 2 -> {
                    System.out.print("Enter merchant name: ");
                    String merchant = sc.nextLine();
                    System.out.print("Enter payment amount: ");
                    BigDecimal payAmt = sc.nextBigDecimal();
                    sc.nextLine();

                    if (payAmt.compareTo(BigDecimal.ZERO) <= 0) {
                        System.out.println("Amount must be positive!");
                        break;
                    }
                    if (payAmt.compareTo(currentWallet.getBalance()) > 0) {
                        System.out.println("❌ Insufficient balance!");
                        break;
                    }
                    currentWallet.applyPayment(new Payment(merchant, payAmt));
                    System.out.println("✅ Payment successful! New balance: " + currentWallet.getBalance());
                }
                case 3 -> {
                    System.out.print("Enter refund amount: ");
                    BigDecimal refundAmt = sc.nextBigDecimal();
                    sc.nextLine();
                    System.out.print("Enter original transaction ID: ");
                    String txn = sc.nextLine().trim();

                    if (refundAmt.compareTo(BigDecimal.ZERO) <= 0) {
                        System.out.println("❌ Amount must be positive!");
                        break;
                    }
                    if (txn.isEmpty()) {
                        System.out.println("❌ Refund failed: Transaction ID cannot be empty!");
                        break;
                    }

                    currentWallet.applyRefund(new Refund(refundAmt, txn));
                    System.out.println("✅ Refund successful! New balance: " + currentWallet.getBalance());
                }

                
                case 4 -> {
                    if (wallets.size() < 2) {
                        System.out.println("❌ No other wallets to transfer to!");
                        break;
                    }
                    System.out.println("Select wallet to transfer TO:");
                    for (int i = 0; i < wallets.size(); i++) {
                        System.out.println((i + 1) + ". " + wallets.get(i));
                    }
                    System.out.print("Enter number: ");
                    int idx = sc.nextInt() - 1;
                    sc.nextLine();
                    if (idx < 0 || idx >= wallets.size()) {
                        System.out.println("Invalid choice!");
                        break;
                    }
                    Wallet target = wallets.get(idx);
                    if (target == currentWallet) {
                        System.out.println("❌ Cannot transfer to your own wallet!");
                        break;
                    }

                    System.out.print("Enter transfer amount: ");
                    BigDecimal tAmt = sc.nextBigDecimal();
                    sc.nextLine();

                    if (tAmt.compareTo(BigDecimal.ZERO) <= 0) {
                        System.out.println("Amount must be positive!");
                        break;
                    }
                    if (tAmt.compareTo(currentWallet.getBalance()) > 0) {
                        System.out.println("❌ Insufficient balance!");
                        break;
                    }

                    Transfer t = new Transfer(currentWallet.getOwner().getName(), target.getOwner().getName(), tAmt);
                    currentWallet.applyTransferOut(t);
                    target.applyTransferIn(t);
                    System.out.println("✅ Transfer successful!");
                    System.out.println("Your new balance: " + currentWallet.getBalance());
                }
                case 5 -> {
                    System.out.println("\n=== Statements for " + currentWallet.getOwner().getName() + " ===");
                    currentWallet.getStatements().forEach(System.out::println);
                    System.out.println("Current balance: " + currentWallet.getBalance());
                }
                case 6 -> {
                    System.out.print("Enter name for new user: ");
                    String n = sc.nextLine();
                    System.out.print("Enter email: ");
                    String e = sc.nextLine();
                    Wallet newWallet = new Wallet(new User(n, e));
                    wallets.add(newWallet);
                    System.out.println("✅ Wallet created for " + n + "! Balance: 0");
                }
                case 7 -> {
                    System.out.println("Select wallet to switch to:");
                    for (int i = 0; i < wallets.size(); i++) {
                        System.out.println((i + 1) + ". " + wallets.get(i));
                    }
                    System.out.print("Enter number: ");
                    int idx = sc.nextInt() - 1;
                    sc.nextLine();
                    if (idx < 0 || idx >= wallets.size()) {
                        System.out.println("Invalid choice!");
                        break;
                    }
                    currentWallet = wallets.get(idx);
                    System.out.println("✅ Switched to wallet of " + currentWallet.getOwner().getName());
                }
                case 8 -> System.out.println("👋 Goodbye!");
                default -> System.out.println("Invalid option!");
            }

        } while (choice != 8);

        sc.close();
    }
}
