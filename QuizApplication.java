import java.util.*;
import java.util.concurrent.*;

public class QuizApplication {
    static class Question {
        String text;
        String[] options;
        int correct;

        Question(String text, String[] options, int correct) {
            this.text = text;
            this.options = options;
            this.correct = correct;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Question> questions = Arrays.asList(
            new Question("Which keyword is used to inherit a class in Java?",
                    new String[]{"implements", "extends", "inherits", "super"}, 2),
            new Question("Which method is the entry point of a Java program?",
                    new String[]{"start()", "run()", "main()", "init()"}, 3),
            new Question("Which collection does not allow duplicate elements?",
                    new String[]{"List", "Set", "ArrayList", "Vector"}, 2),
            new Question("Which type stores true or false?",
                    new String[]{"int", "char", "boolean", "double"}, 3),
            new Question("Which keyword creates an object?",
                    new String[]{"new", "create", "object", "make"}, 1)
        );

        final int TIME_LIMIT_SECONDS = 10;
        int score = 0;
        int correctCount = 0;

        System.out.println("=== CODSOFT TASK 4: QUIZ APPLICATION ===");
        System.out.println("You have " + TIME_LIMIT_SECONDS + " seconds per question.");

        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);

            System.out.println("\nQuestion " + (i + 1) + ": " + q.text);
            for (int j = 0; j < q.options.length; j++) {
                System.out.println((j + 1) + ". " + q.options[j]);
            }

            System.out.print("Your answer: ");

            Future<String> future = executor.submit(sc::nextLine);

            try {
                String input = future.get(TIME_LIMIT_SECONDS, TimeUnit.SECONDS).trim();

                if (input.matches("[1-4]")) {
                    int answer = Integer.parseInt(input);

                    if (answer == q.correct) {
                        System.out.println("Correct!");
                        score += 10;
                        correctCount++;
                    } else {
                        System.out.println("Incorrect. Correct answer: " + q.correct);
                    }
                } else {
                    System.out.println("Invalid option. Correct answer: " + q.correct);
                }
            } catch (TimeoutException e) {
                future.cancel(true);
                System.out.println("\nTime limit exceeded! Correct answer: " + q.correct);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Quiz interrupted.");
                break;
            } catch (ExecutionException e) {
                System.out.println("Unable to read the answer.");
                break;
            }
        }

        executor.shutdownNow();

        System.out.println("\n=== FINAL RESULT ===");
        System.out.println("Correct answers: " + correctCount + "/" + questions.size());
        System.out.println("Final score    : " + score + "/" + (questions.size() * 10));

        sc.close();
    }
}