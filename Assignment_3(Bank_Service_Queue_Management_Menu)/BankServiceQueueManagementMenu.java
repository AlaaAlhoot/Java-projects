import java.util.*;
class Customer {
    String name;
    int id;
    String transactionType; // Deposit او Withdraw او Balance Inquiry
    double amount;
    boolean isPriority;

    public Customer(String name, int id, String transactionType, double amount, boolean isPriority) {
        this.name = name;
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
        this.isPriority = isPriority;
    }

    public String toString() {
        return "Name: " + name + ", ID: " + id + ", Type: " + transactionType + ", Amount: " + amount + ", Priority: " + isPriority;
    }
}

class CustomerQueue {
    class Node {
        Customer data;
        Node next;

        Node(Customer data) {
            this.data = data;
        }
    }

    private Node front;
    private Node rear;
    private int size = 0;

    public void enqueue(Customer data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public void enqueuePriority(Customer data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.next = front;
            front = newNode;
        }
        size++;
    }

    public Customer dequeue() {
        if (isEmpty()) {
            return null;
        }
        Customer data = front.data;
        front = front.next;
        size--;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public void displayAll() {
        Node current = front;
        while (current != null) {
            System.out.println(current.data.toString());
            current = current.next;
        }
    }

    public double getTotalTransactionAmount() {
        double total = 0;
        Node current = front;
        while (current != null) {
            total += current.data.amount;
            current = current.next;
        }
        return total;
    }

    public void countByTransactionType() {
        int depositCount = 0;
        int withdrawCount = 0;

        Node current = front;
        while (current != null) {
            if (current.data.transactionType.equalsIgnoreCase("Deposit")) {
                depositCount++;
            } else if (current.data.transactionType.equalsIgnoreCase("Withdraw")) {
                withdrawCount++;
            }
            current = current.next;
        }

        System.out.println("Deposit Count: " + depositCount);
        System.out.println("Withdraw Count: " + withdrawCount);
    }

    public void reverseQueueAndPrintNames() {
        Stack<String> stack = new Stack<>();
        Node current = front;
        while (current != null) {
            stack.push(current.data.name);
            current = current.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}

public class BankServiceQueueManagementMenu {
    public static void main(String[] args) {
        CustomerQueue queue = new CustomerQueue();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("=========================");
            System.out.println("1. Add new customer");
            System.out.println("2. Serve next customer");
            System.out.println("3. Show number of waiting customers");
            System.out.println("4. Display all waiting customers");
            System.out.println("5. Calculate total transaction amount");
            System.out.println("6. Count Deposit and Withdraw");
            System.out.println("7. Exit");
            System.out.println("8. Reverse queue and print names");
            System.out.print("Choose: ");
            int choice = input.nextInt();

            if (choice == 1) {
                input.nextLine();
                System.out.print("Enter name: ");
                String name = input.nextLine();
                System.out.print("Enter id: ");
                int id = input.nextInt();
                input.nextLine();
                System.out.print("Transaction type (Deposit/Withdraw/Balance Inquiry): ");
                String type = input.nextLine();
                System.out.print("Amount: ");
                double amount = input.nextDouble();
                System.out.print("Priority? (true/false): ");
                boolean priority = input.nextBoolean();

                Customer c = new Customer(name, id, type, amount, priority);
                if (priority) {
                    queue.enqueuePriority(c);
                } else {
                    queue.enqueue(c);
                }
                System.out.println("Customer added.");

            } else if (choice == 2) {
                Customer served = queue.dequeue();
                if (served != null) {
                    System.out.println("Served: " + served);
                } else {
                    System.out.println("Queue empty.");
                }

            } else if (choice == 3) {
                System.out.println("Number of customers: " + queue.size());

            } else if (choice == 4) {
                queue.displayAll();

            } else if (choice == 5) {
                double total = queue.getTotalTransactionAmount();
                System.out.println("Total amount: " + total);

            } else if (choice == 6) {
                queue.countByTransactionType();

            } else if (choice == 7) {
                System.out.println("Bye.");
                break;

            } else if (choice == 8) {
                queue.reverseQueueAndPrintNames();

            } else {
                System.out.println("Wrong choice.");
            }
        }

        input.close();
    }
}
