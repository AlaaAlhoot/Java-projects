import java.util.Scanner;

// This class represents a single task in the to-do list
class Task {
    int id; // this is the task ID
    String name; // this is the name of the task
    boolean isCompleted; // true if task is done
    int duration; // how long the task takes (in minutes maybe)

    Task next; // pointer to the next task

    // constructor to create a task
    public Task(int id, String name, boolean isCompleted, int duration) {
        this.id = id;
        this.name = name;
        this.isCompleted = isCompleted;
        this.duration = duration;
        this.next = null; // at the start, this task does not point to anything
    }
}

// this is the main class
public class SimpleToDoList {

    static Task head = null; // this is the first task in the list

    // function to add a task to the end
    public static void addTask(int id, String name, boolean isCompleted, int duration) {
        Task newTask = new Task(id, name, isCompleted, duration); // I create a new task
        if (head == null) {
            head = newTask; // if list is empty, this is the first task
        } else {
            Task temp = head;
            while (temp.next != null) {
                temp = temp.next; // I move to the end
            }
            temp.next = newTask; // I add the task at the end
        }
        System.out.println("Task added.");
    }

    // function to delete a task by ID
    public static void deleteTask(int id) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.id == id) {
            head = head.next; // if it's the first task, I remove it
            System.out.println("Task deleted.");
            return;
        }

        Task temp = head;
        while (temp.next != null && temp.next.id != id) {
            temp = temp.next; // I look for the task
        }

        if (temp.next == null) {
            System.out.println("Task not found.");
        } else {
            temp.next = temp.next.next; // I skip the task to delete it
            System.out.println("Task deleted.");
        }
    }

    // function to search for a task by ID
    public static void searchTask(int id) {
        Task temp = head;
        while (temp != null) {
            if (temp.id == id) {
                System.out.println("Task Name: " + temp.name);
                System.out.println("Status: " + (temp.isCompleted ? "Completed" : "Not Completed"));
                return;
            }
            temp = temp.next;
        }
        System.out.println("Task not found.");
    }

    // function to print completed tasks
    public static void printCompletedTasks() {
        Task temp = head;
        System.out.println("Completed Tasks:");
        while (temp != null) {
            if (temp.isCompleted) {
                System.out.println("- " + temp.name + " (Duration: " + temp.duration + " mins)");
            }
            temp = temp.next;
        }
    }

    // function to print not completed tasks
    public static void printNotCompletedTasks() {
        Task temp = head;
        System.out.println("Not Completed Tasks (Reminder):");
        while (temp != null) {
            if (!temp.isCompleted) {
                System.out.println("- " + temp.name + " (Duration: " + temp.duration + " mins)");
            }
            temp = temp.next;
        }
    }

    // main function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // I use Scanner to get input from the user

        while (true) {
            // I show options to the user
            System.out.println("\nMenu:");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Search Task");
            System.out.println("4. Show Completed Tasks");
            System.out.println("5. Show Not Completed Tasks");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt(); // user chooses a number

            if (choice == 1) {
                // I ask the user to enter task data
                System.out.print("Enter Task ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // I consume the newline

                System.out.print("Enter Task Name: ");
                String name = scanner.nextLine();

                System.out.print("Is Task Completed? (true/false): ");
                boolean isCompleted = scanner.nextBoolean();

                System.out.print("Enter Task Duration (in minutes): ");
                int duration = scanner.nextInt();

                // I call the function to add the task
                addTask(id, name, isCompleted, duration);

            } else if (choice == 2) {
                System.out.print("Enter Task ID to delete: ");
                int id = scanner.nextInt();
                deleteTask(id);

            } else if (choice == 3) {
                System.out.print("Enter Task ID to search: ");
                int id = scanner.nextInt();
                searchTask(id);

            } else if (choice == 4) {
                printCompletedTasks();

            } else if (choice == 5) {
                printNotCompletedTasks();

            } else if (choice == 6) {
                System.out.println("Goodbye!");
                break; // I stop the program

            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close(); // I close the scanner at the end
    }
}
