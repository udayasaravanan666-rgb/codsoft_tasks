import java.util.*;

public class StudentCourseRegistration {
    static class Student {
        String id, name;
        List<String> registeredCourses = new ArrayList<>();

        Student(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class Course {
        String code, title, description, schedule;
        int capacity;
        List<String> registeredStudents = new ArrayList<>();

        Course(String code, String title, String description, int capacity, String schedule) {
            this.code = code;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.schedule = schedule;
        }

        boolean hasStudent(String studentId) {
            return registeredStudents.contains(studentId);
        }

        boolean register(String studentId) {
            if (hasStudent(studentId) || registeredStudents.size() >= capacity)
                return false;
            registeredStudents.add(studentId);
            return true;
        }

        boolean remove(String studentId) {
            return registeredStudents.remove(studentId);
        }

        int availableSlots() {
            return capacity - registeredStudents.size();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Student> students = new ArrayList<>();
        List<Course> courses = new ArrayList<>(Arrays.asList(
            new Course("CS101", "Java Programming", "Core Java and OOP", 3, "Mon 10:00 AM"),
            new Course("CS102", "Data Structures", "Arrays, lists, stacks and trees", 3, "Tue 11:00 AM"),
            new Course("CS103", "Database Systems", "SQL and database fundamentals", 3, "Wed 2:00 PM")
        ));

        System.out.println("=== CODSOFT TASK 5: STUDENT COURSE REGISTRATION ===");
        System.out.print("Enter Student ID: ");
        String studentId = sc.nextLine().trim();

        System.out.print("Enter Student Name: ");
        String studentName = sc.nextLine().trim();

        Student student = new Student(studentId, studentName);
        students.add(student);

        int choice;
        do {
            System.out.println("\n1. List Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop Course");
            System.out.println("4. Show My Courses");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid choice.");
                sc.next();
                System.out.print("Choose: ");
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> listCourses(courses);

                case 2 -> {
                    listCourses(courses);
                    System.out.print("Enter course code: ");
                    String code = sc.nextLine().toUpperCase();
                    Course course = find(courses, code);

                    if (course == null) {
                        System.out.println("Course not found.");
                    } else if (course.register(student.id)) {
                        student.registeredCourses.add(course.code);
                        System.out.println("Registration successful.");
                    } else {
                        System.out.println("Registration failed. Already registered or course is full.");
                    }
                }

                case 3 -> {
                    System.out.print("Enter course code to drop: ");
                    String code = sc.nextLine().toUpperCase();
                    Course course = find(courses, code);

                    if (course != null && course.remove(student.id)) {
                        student.registeredCourses.remove(course.code);
                        System.out.println("Course dropped successfully.");
                    } else {
                        System.out.println("Course not found or not registered.");
                    }
                }

                case 4 -> {
                    System.out.println("\nStudent: " + student.name + " (" + student.id + ")");
                    System.out.println("My Registered Courses:");
                    if (student.registeredCourses.isEmpty()) {
                        System.out.println("No courses registered.");
                    } else {
                        for (String code : student.registeredCourses) {
                            Course c = find(courses, code);
                            System.out.println(c.code + " - " + c.title);
                        }
                    }
                }

                case 5 -> System.out.println("Thank you.");

                default -> System.out.println("Invalid choice. Select 1 to 5.");
            }
        } while (choice != 5);

        sc.close();
    }

    static void listCourses(List<Course> courses) {
        System.out.println("\nAvailable Courses:");
        for (Course c : courses) {
            System.out.println(
                c.code + " | " + c.title +
                " | " + c.description +
                " | Capacity: " + c.capacity +
                " | Available Slots: " + c.availableSlots() +
                " | Schedule: " + c.schedule
            );
        }
    }

    static Course find(List<Course> courses, String code) {
        for (Course c : courses) {
            if (c.code.equals(code)) return c;
        }
        return null;
    }
}