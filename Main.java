import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(10000);
        Scanner scanner = new Scanner(System.in);

        ATM atm = new ATM(account, scanner);
        atm.start();

        scanner.close();
    }
}