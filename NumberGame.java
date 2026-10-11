import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int rounds = 0, wins = 0, totalAttempts = 0;
        boolean playAgain = true;
        final int MAX_ATTEMPTS = 7;

        System.out.println("=== CODSOFT TASK 1: NUMBER GAME ===");

        while (playAgain) {
            int number = random.nextInt(100) + 1;
            int attempts = 0;
            boolean won = false;
            rounds++;

            System.out.println("\nRound " + rounds);
            System.out.println("Guess a number between 1 and 100.");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts.");

            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Enter a whole number.");
                    sc.next();
                    continue;
                }

                int guess = sc.nextInt();

                if (guess < 1 || guess > 100) {
                    System.out.println("Enter a number from 1 to 100.");
                    continue;
                }

                attempts++;
                totalAttempts++;

                if (guess == number) {
                    won = true;
                    wins++;
                    System.out.println("Correct! You won in " + attempts + " attempt(s).");
                    break;
                } else if (guess < number) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                System.out.println("Attempts remaining: " + (MAX_ATTEMPTS - attempts));
            }

            if (!won) {
                System.out.println("You lost. The correct number was " + number + ".");
            }

            int score = won ? (MAX_ATTEMPTS - attempts + 1) * 10 : 0;
            System.out.println("Round score: " + score);

            System.out.print("Play another round? (Y/N): ");
            playAgain = sc.next().equalsIgnoreCase("Y");
        }

        System.out.println("\n=== FINAL RESULTS ===");
        System.out.println("Rounds played : " + rounds);
        System.out.println("Rounds won    : " + wins);
        System.out.println("Total attempts: " + totalAttempts);
        System.out.printf("Win rate      : %.2f%%%n", rounds == 0 ? 0 : wins * 100.0 / rounds);
        sc.close();
    }
}