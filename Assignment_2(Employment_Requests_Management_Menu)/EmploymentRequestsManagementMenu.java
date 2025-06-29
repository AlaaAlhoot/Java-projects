
import java.util.*;

class Child {
    String name;
    int age;
    char gender;

    public Child(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String toString() {
        return "Child Name: " + name + ", Age: " + age + ", Gender: " + gender;
    }
}

class EmploymentRequest {
    String applicantName;
    int applicantAge;
    double salary;
    char applicantGender;
    String applicantJob;
    Date applicationDate;
    boolean isMarried;
    Child[] children;

    public EmploymentRequest(String name, int age, double salary, char gender, String job, Date date, boolean married, Child[] children) {
        this.applicantName = name;
        this.applicantAge = age;
        this.salary = salary;
        this.applicantGender = gender;
        this.applicantJob = job;
        this.applicationDate = date;
        this.isMarried = married;
        this.children = children;
    }

    public String toString() {
        String s = "Name: " + applicantName + ", Age: " + applicantAge + ", Salary: " + salary + ", Gender: " + applicantGender +
                ", Job: " + applicantJob + ", Date: " + applicationDate + ", Married: " + isMarried;

        if (children != null && children.length > 0) {
            s += "\nChildren:\n";
            for (Child c : children) {
                s += c.toString() + "\n";
            }
        } else {
            s += "\nNo children.";
        }

        return s;
    }
}

class LinkedListStack {
    class Node {
        EmploymentRequest data;
        Node next;

        Node(EmploymentRequest data) {
            this.data = data;
        }
    }

    private Node top;
    private int size = 0;

    public void push(EmploymentRequest data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public EmploymentRequest pop() {
        if (isEmpty()) {
            return null;
        }
        EmploymentRequest data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public EmploymentRequest peek() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void displayAll() {
        Node current = top;
        while (current != null) {
            System.out.println(current.data.toString());
            System.out.println("-----------");
            current = current.next;
        }
    }

    public EmploymentRequest[] findMinMaxSalary() {
        if (isEmpty()) {
            return null;
        }

        Node current = top;
        EmploymentRequest min = current.data;
        EmploymentRequest max = current.data;

        while (current != null) {
            if (current.data.salary < min.salary) {
                min = current.data;
            }
            if (current.data.salary > max.salary) {
                max = current.data;
            }
            current = current.next;
        }

        return new EmploymentRequest[]{min, max};
    }

    public EmploymentRequest findByName(String name) {
        Node current = top;
        while (current != null) {
            if (current.data.applicantName.equalsIgnoreCase(name)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public int countTotalChildren() {
        int total = 0;
        Node current = top;
        while (current != null) {
            if (current.data.children != null) {
                total += current.data.children.length;
            }
            current = current.next;
        }
        return total;
    }
}

public class EmploymentRequestsManagementMenu {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("====================");
            System.out.println("1. Add new application");
            System.out.println("2. Remove latest application");
            System.out.println("3. Display total number of applications");
            System.out.println("4. Show application with minimum and maximum salary");
            System.out.println("5. Search for an applicant by name");
            System.out.println("6. Display all applications");
            System.out.println("7. Exit");
            System.out.println("8. Print total number of children");
            System.out.print("Choose: ");
            int choice = input.nextInt();

            if (choice == 1) {
                input.nextLine();
                System.out.print("Enter name: ");
                String name = input.nextLine();

                System.out.print("Enter age: ");
                int age = input.nextInt();

                System.out.print("Enter salary: ");
                double salary = input.nextDouble();

                System.out.print("Enter gender (M/F): ");
                char gender = input.next().charAt(0);

                input.nextLine();
                System.out.print("Enter job: ");
                String job = input.nextLine();

                Date date = new Date();

                System.out.print("Is married? (true/false): ");
                boolean married = input.nextBoolean();

                System.out.print("Number of children: ");
                int numChildren = input.nextInt();

                Child[] children = new Child[numChildren];
                for (int i = 0; i < numChildren; i++) {
                    input.nextLine();
                    System.out.print("Child " + (i+1) + " name: ");
                    String cname = input.nextLine();
                    System.out.print("Child " + (i+1) + " age: ");
                    int cage = input.nextInt();
                    System.out.print("Child " + (i+1) + " gender (M/F): ");
                    char cgender = input.next().charAt(0);
                    children[i] = new Child(cname, cage, cgender);
                }

                EmploymentRequest req = new EmploymentRequest(name, age, salary, gender, job, date, married, children);
                stack.push(req);
                System.out.println("Application added.");

            } else if (choice == 2) {
                EmploymentRequest removed = stack.pop();
                if (removed != null) {
                    System.out.println("Removed: " + removed.applicantName);
                } else {
                    System.out.println("Stack is empty.");
                }

            } else if (choice == 3) {
                System.out.println("Total applications: " + stack.size());

            } else if (choice == 4) {
                EmploymentRequest[] minMax = stack.findMinMaxSalary();
                if (minMax != null) {
                    System.out.println("Min salary:\n" + minMax[0]);
                    System.out.println("Max salary:\n" + minMax[1]);
                } else {
                    System.out.println("Stack empty.");
                }

            } else if (choice == 5) {
                input.nextLine();
                System.out.print("Enter name: ");
                String searchName = input.nextLine();
                EmploymentRequest found = stack.findByName(searchName);
                if (found != null) {
                    System.out.println(found);
                } else {
                    System.out.println("Not found.");
                }

            } else if (choice == 6) {
                stack.displayAll();

            } else if (choice == 7) {
                System.out.println("Bye.");
                break;

            } else if (choice == 8) {
                int totalChildren = stack.countTotalChildren();
                System.out.println("Total children: " + totalChildren);

            } else {
                System.out.println("Wrong choice.");
            }
        }

        input.close();
    }
}
