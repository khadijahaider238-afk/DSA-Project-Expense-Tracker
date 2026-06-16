Expense Tracker - Java DSA Project
A console-based Expense Tracker built in Java that demonstrates core Data Structures and Algorithms concepts.

Data Structures Used
Structure	Where Used
Linked List	Stores all expenses as connected nodes
Stack	Undo last expense (LIFO)
HashMap	Category-wise expense totals
Array	Temporary storage for sorting/searching
Algorithms Implemented
Algorithm	Where Used
Merge Sort	Sort expenses by amount
Binary Search	Search expense by ID
Recursion	Filter expenses by category

Features
Add new expenses (title, amount, category, date)
View all expenses in table format
Search expense by ID (Binary Search)
Sort expenses by amount (Merge Sort)
Filter expenses by category (Recursion)
Undo last entry (Stack)
Category summary using HashMap

How to Run
Requirements
Java JDK 21
Steps
Install Java JDK 21 from https://www.oracle.com/java/technologies/downloads/
Open project in IntelliJ IDEA or VS Code
Navigate to the `src/` folder
Open `Main.java`
Compile and run:
bash
cd src
javac Main.java Expense.java ExpenseManager.java Stack.java Utility.java
java Main


Project Structure

ExpenseTracker/
├── src/
│   ├── Main.java           # Entry point, menu system
│   ├── Expense.java        # Expense model (LinkedList Node)
│   ├── ExpenseManager.java # Core logic (LinkedList + HashMap)
│   ├── Stack.java          # Custom Stack for undo feature
│   └── Utility.java        # Merge Sort + Binary Search
└── README.md
Author
Name: Khadija Haider
Student ID: L1F24BSSE0354
Section: D-2
Course: DSA
Java Version: JDK 21
