import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== CODSOFT TASK 2: STUDENT GRADE CALCULATOR ===");
        System.out.print("Enter number of subjects: ");
        int subjects = sc.nextInt();

        if (subjects <= 0) {
            System.out.println("Number of subjects must be positive.");
            sc.close();
            return;
        }

        double total = 0;

        for (int i = 1; i <= subjects; i++) {
            double mark;
            while (true) {
                System.out.print("Enter mark for Subject " + i + " (0-100): ");
                mark = sc.nextDouble();

                if (mark >= 0 && mark <= 100) break;
                System.out.println("Invalid mark. Enter a value from 0 to 100.");
            }
            total += mark;
        }

        double average = total / subjects;
        String grade;

        if (average >= 90) grade = "A+";
        else if (average >= 80) grade = "A";
        else if (average >= 70) grade = "B";
        else if (average >= 60) grade = "C";
        else if (average >= 50) grade = "D";
        else grade = "F";

        System.out.println("\n=== RESULT ===");
        System.out.printf("Total Marks       : %.2f%n", total);
        System.out.printf("Average Percentage: %.2f%%%n", average);
        System.out.println("Grade             : " + grade);

        sc.close();
    }
}