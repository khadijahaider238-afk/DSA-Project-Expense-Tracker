import java.util.Scanner;
public class Main {

 public static void main(String[] args) {
   Scanner scanner = new Scanner(System.in);
     ExpenseManager manager = new ExpenseManager();
      System.out.println("║        EXPENSE TRACKER - DSA         ║");

      boolean running = true;

   while (running) {
        printMenu();
        System.out.print("Enter your choice: ");

        int choice;
       try {
            choice = Integer.parseInt(scanner.nextLine().trim());
         } catch (NumberFormatException e) {
              System.out.println("\n[ERROR] Please enter a valid number.");
             continue;
            }

            switch (choice) {
                case 1 -> addExpense(scanner, manager);
                case 2 -> manager.viewAllExpenses();
                case 3 -> searchExpense(scanner, manager);
                case 4 -> manager.sortByAmount();
                case 5 -> filterByCategory(scanner, manager);
                case 6 -> manager.undoLastExpense();
                case 7 -> manager.showCategorySummary();
                case 8 -> {
                    System.out.println("\nThank you for using Expense Tracker. Goodbye!");
                    running = false;
                }
                default -> System.out.println("\n[ERROR] Invalid choice. Please enter 1-8.");
            }
        }

        scanner.close();
    }

    /**
     * Prints the main menu options
     */
    private static void printMenu() {
    

        System.out.println("              MAIN MENU");
        System.out.println("  1. Add New Expense");
        System.out.println("  2. View All Expenses");
        System.out.println("  3. Search Expense by ID");
        System.out.println("  4. Sort Expenses by Amount");
        System.out.println("  5. Filter by Category");
        System.out.println("  6. Undo Last Expense");
        System.out.println("  7. Category Summary");
        System.out.println("  8. Exit");
        System.out.println("========================================");
    }

    private static void addExpense(Scanner scanner, ExpenseManager manager) {
        System.out.println("\n--- ADD NEW EXPENSE ---");

        System.out.print("Enter title       : ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("[ERROR] Title cannot be empty.");
            return;
        }

        double amount;
        System.out.print("Enter amount (Rs.): ");
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println("[ERROR] Amount must be greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid amount. Please enter a number.");
            return;
        }

        System.out.print("Enter category    : ");
        String category = scanner.nextLine().trim();

        if (category.isEmpty()) {
            System.out.println("[ERROR] Category cannot be empty.");
            return;
        }

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();

        if (date.isEmpty()) {
            System.out.println("[ERROR] Date cannot be empty.");
            return;
        }

        manager.addExpense(title, amount, category, date);
    }
    private static void searchExpense(Scanner scanner, ExpenseManager manager) {
        System.out.println("\n--- SEARCH BY ID ---");
        System.out.print("Enter Expense ID: ");

        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid ID. Please enter a number.");
            return;
        }

        manager.searchById(id);
    }
    private static void filterByCategory(Scanner scanner, ExpenseManager manager) {
        System.out.println("\n--- FILTER BY CATEGORY ---");
        System.out.print("Enter category name: ");
        String category = scanner.nextLine().trim();

        if (category.isEmpty()) {
            System.out.println("[ERROR] Category cannot be empty.");
            return;
        }

        manager.filterByCategory(category);
    }
}
