import java.util.Scanner;

public class ATM {
    private final BankAccount account;
    private final Scanner sc;

    public ATM(BankAccount account, Scanner sc) {
        this.account = account;
        this.sc = sc;
    }

    public void start() {
        int choice;

        do {
            System.out.println("\n=== CODSOFT TASK 3: ATM INTERFACE ===");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid option.");
                sc.next();
                System.out.print("Choose an option: ");
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> withdraw();
                case 2 -> deposit();
                case 3 -> System.out.printf("Current balance: Rs. %.2f%n", account.getBalance());
                case 4 -> System.out.println("Thank you for using the ATM.");
                default -> System.out.println("Please choose 1 to 4.");
            }
        } while (choice != 4);
    }

    private void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        double amount = sc.nextDouble();

        if (account.withdraw(amount))
            System.out.printf("Withdrawal successful. Balance: Rs. %.2f%n", account.getBalance());
        else
            System.out.println("Withdrawal failed. Check amount and available balance.");
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ");
        double amount = sc.nextDouble();

        if (account.deposit(amount))
            System.out.printf("Deposit successful. Balance: Rs. %.2f%n", account.getBalance());
        else
            System.out.println("Deposit failed. Amount must be positive.");
    }
}