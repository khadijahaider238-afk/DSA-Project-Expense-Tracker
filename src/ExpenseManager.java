import java.util.HashMap;
public class ExpenseManager {

    private Expense head;
    private int size;
    private int nextId;

    private HashMap<String, Double> categoryMap;

    private Stack undoStack;

    public ExpenseManager() {
        head        = null;
        size        = 0;
        nextId      = 1;
        categoryMap = new HashMap<>();
        undoStack   = new Stack(20); // Can undo up to 20 actions
    }

    public void addExpense(String title, double amount, String category, String date) {
        Expense newExpense = new Expense(nextId++, title, amount, category, date);
        if (head == null) {
            head = newExpense;
        } else {
            Expense current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newExpense;
        }
        size++;

        categoryMap.put(category,
                categoryMap.getOrDefault(category, 0.0) + amount);

        undoStack.push(newExpense);

        System.out.println("\n[SUCCESS] Expense added: " + newExpense.getTitle()
                + " | Rs. " + newExpense.getAmount());
    }
    public void viewAllExpenses() {
        if (head == null) {
            System.out.println("\n[INFO] No expenses found.");
            return;
        }

        System.out.println("\n========== ALL EXPENSES ==========");
        Utility.printTableHeader();

        Expense current = head;
        double total = 0;

        while (current != null) {
            System.out.println(current.toString());
            total += current.getAmount();
            current = current.next;
        }

        Utility.printTableFooter();
        System.out.printf("  TOTAL: Rs. %.2f  |  Count: %d%n", total, size);
    }
    public void searchById(int id) {
        if (head == null) {
            System.out.println("\n[INFO] No expenses to search.");
            return;
        }

        Expense[] arr = toArray();

        int index = Utility.binarySearch(arr, id);

        if (index == -1) {
            System.out.println("\n[NOT FOUND] No expense with ID: " + id);
        } else {
            System.out.println("\n[FOUND] Expense details:");
            Utility.printTableHeader();
            System.out.println(arr[index].toString());
            Utility.printTableFooter();
        }
    }

    public void sortByAmount() {
        if (head == null) {
            System.out.println("\n[INFO] No expenses to sort.");
            return;
        }

        Expense[] arr = toArray();

        Utility.mergeSort(arr, 0, arr.length - 1);

        System.out.println("\n========== SORTED BY AMOUNT (Low to High) ==========");
        Utility.printTableHeader();
        for (Expense e : arr) {
            System.out.println(e.toString());
        }
        Utility.printTableFooter();
    }

    public void filterByCategory(String category) {
        System.out.println("\n========== EXPENSES: " + category.toUpperCase() + " ==========");
        Utility.printTableHeader();

        double total = filterRecursive(head, category);

        Utility.printTableFooter();
        System.out.printf("  Category Total: Rs. %.2f%n", total);
    }

    private double filterRecursive(Expense current, String category) {
        if (current == null) return 0.0;

        double total = 0;

        if (current.getCategory().equalsIgnoreCase(category)) {
            System.out.println(current.toString());
            total += current.getAmount();
        }

        return total + filterRecursive(current.next, category);
    }

    public void undoLastExpense() {
        if (undoStack.isEmpty()) {
            System.out.println("\n[INFO] Nothing to undo.");
            return;
        }

        Expense toRemove = undoStack.pop();
        System.out.println("\n[UNDO] Removing: " + toRemove.getTitle());

        if (head == null) return;

        if (head.getId() == toRemove.getId()) {
            head = head.next;
        } else {
            Expense current = head;
            while (current.next != null && current.next.getId() != toRemove.getId()) {
                current = current.next;
            }
            if (current.next != null) {
                current.next = current.next.next;
            }
        }

        String cat = toRemove.getCategory();
        double newTotal = categoryMap.getOrDefault(cat, 0.0) - toRemove.getAmount();
        if (newTotal <= 0) {
            categoryMap.remove(cat);
        } else {
            categoryMap.put(cat, newTotal);
        }

        size--;
        nextId--;
        System.out.println("[SUCCESS] Expense removed: " + toRemove.getTitle());
    }

    public void showCategorySummary() {
        if (categoryMap.isEmpty()) {
            System.out.println("\n[INFO] No expenses recorded.");
            return;
        }

        System.out.println("\n========== CATEGORY SUMMARY (HashMap) ==========");
        System.out.println("| Category         | Total Amount     |");

        double grandTotal = 0;
        for (String cat : categoryMap.keySet()) {
            double total = categoryMap.get(cat);
            grandTotal += total;
            System.out.printf("| %-16s | Rs. %-12.2f |%n", cat, total);
        }
        System.out.printf("| %-16s | Rs. %-12.2f |%n", "GRAND TOTAL", grandTotal);

    }

    private Expense[] toArray() {
        Expense[] arr = new Expense[size];
        Expense current = head;
        int i = 0;
        while (current != null) {
            arr[i++] = current;
            current = current.next;
        }
        return arr;
    }

    public int getSize() {
        return size;
    }
}
