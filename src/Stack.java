public class Stack {

    private Expense[] stackArray;
    private int top;
    private int capacity;

    public Stack(int capacity) {
        this.capacity = capacity;
        stackArray = new Expense[capacity];
        top = -1;
    }

    public boolean push(Expense expense) {
        if (isFull()) {
            System.out.println("[Stack] Stack is full. Cannot undo more than " + capacity + " actions.");
            return false;
        }
        stackArray[++top] = expense;
        return true;
    }

    public Expense pop() {
        if (isEmpty()) {
            return null;
        }
        return stackArray[top--];
    }

    public Expense peek() {
        if (isEmpty()) return null;
        return stackArray[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {

        return top == capacity - 1;
    }

    public int size() {

        return top + 1;
    }
}
