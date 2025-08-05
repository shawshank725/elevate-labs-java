import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Task2 {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int number;
        System.out.println("Enter 1 to view all students, 2 to delete a student, 3 to add new student, 4 to update student - ");
        char running = 'y';
        while (running == 'y' || running == 'Y') {
            System.out.print("Enter number - ");
            number = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (number) {
                case 1:
                    if (students.size() == 0) {
                        System.out.println("Number of students are 0");
                    } else {
                        for (Student student : students) {
                            System.out.println(student.toString());
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter id of student that is to be deleted - ");
                    int id = sc.nextInt();
                    Student studentFound = findStudent(id, students);
                    if (studentFound != null) {
                        students.remove(studentFound);
                        System.out.println("Student deleted!");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Student name - ");
                    String name = sc.nextLine();
                    System.out.print("Enter Student marks 1 - ");
                    double marks1 = sc.nextDouble();
                    System.out.print("Enter Student marks 2 - ");
                    double marks2 = sc.nextDouble();
                    System.out.print("Enter Student marks 3 - ");
                    double marks3 = sc.nextDouble();
                    Student newStudent = new Student(name, marks1, marks2, marks3);
                    newStudent.setId(findMaxId(students) + 1);
                    try {
                        students.add(newStudent);
                        System.out.println("Added new student");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Failed to add the student");
                    }
                    break;

                case 4:
                    System.out.print("Enter the student id to update - ");
                    int updateId = sc.nextInt();
                    Student studentToUpdate = findStudent(updateId, students);
                    if (studentToUpdate == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.println("Enter 11 to update name, 22 to update marks1, 33 to update marks2, 44 to update marks3 - ");
                    int valueToEdit = sc.nextInt();
                    sc.nextLine();
                    switch (valueToEdit) {
                        case 11:
                            System.out.print("Enter new name - ");
                            String newName = sc.nextLine();
                            studentToUpdate.setName(newName);
                            System.out.println("Name updated.");
                            break;
                        case 22:
                            System.out.print("Enter new marks1 - ");
                            double newMarks1 = sc.nextDouble();
                            studentToUpdate.setMarks1(newMarks1);
                            System.out.println("Marks1 updated.");
                            break;
                        case 33:
                            System.out.print("Enter new marks2 - ");
                            double newMarks2 = sc.nextDouble();
                            studentToUpdate.setMarks2(newMarks2);
                            System.out.println("Marks2 updated.");
                            break;
                        case 44:
                            System.out.print("Enter new marks3 - ");
                            double newMarks3 = sc.nextDouble();
                            studentToUpdate.setMarks3(newMarks3);
                            System.out.println("Marks3 updated.");
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                    break;
            }

            sc.nextLine(); // consume leftover newline
            System.out.print("Do you want to continue? ");
            running = sc.nextLine().charAt(0);
        }
    }

    public static int findMaxId(List<Student> students) {
        int max = 0;
        for (Student student : students) {
            if (student.getId() > max) {
                max = student.getId();
            }
        }
        return max;
    }

    public static Student findStudent(int id, List<Student> students) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}

class Student {
    private int id;
    private String name;
    private double marks1;
    private double marks2;
    private double marks3;

    public Student() {}

    public Student(String name, double marks1, double marks2, double marks3) {
        this.name = name;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
    }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setMarks1(double marks1) { this.marks1 = marks1; }
    public void setMarks2(double marks2) { this.marks2 = marks2; }
    public void setMarks3(double marks3) { this.marks3 = marks3; }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getMarks1() { return marks1; }
    public double getMarks2() { return marks2; }
    public double getMarks3() { return marks3; }

    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Marks 1: " + marks1 +
               ", Marks2: " + marks2 + ", Marks3: " + marks3;
    }
}
