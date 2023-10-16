import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame implements ActionListener {
    private ExpenseTracker expenseTracker;
    private JTextField descriptionField, amountField, categoryField;
    private List<Expense> expenses;

    public Main() {
        expenseTracker = new ExpenseTracker();

        // Create labels
        JLabel descriptionLabel = new JLabel("Description");
        JLabel amountLabel = new JLabel("Amount");
        JLabel categoryLabel = new JLabel("Category");

        // Create text fields
        descriptionField = new JTextField();
        amountField = new JTextField();
        categoryField = new JTextField();

        // Create buttons
        JButton addButton = new JButton("Add Expense");
        JButton viewButton = new JButton("View Expenses");
        JButton summaryButton = new JButton("Expense Summary");

        // Set layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 10, 20));

        // Add components to the frame
        panel.add(descriptionLabel);
        panel.add(amountLabel);
        panel.add(categoryLabel);
        panel.add(descriptionField);
        panel.add(amountField);
        panel.add(categoryField);
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(summaryButton);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panel);

        // Add action listeners
        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        summaryButton.addActionListener(this);

        // Set frame properties
        setTitle("Expense Tracker");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        for (Expense expense : expenses) {
            if (!categories.contains(expense.getCategory())) {
                categories.add(expense.getCategory());
            }
        }
        return categories;
    }
    public double getTotalExpensesForCategory(String category) {
        double total = 0;
        for (Expense expense : expenses) {
            if (category == null || expense.getCategory().equalsIgnoreCase(category)) {
                total += expense.getAmount();
            }
        }
        return total;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Expense")) {
            String description = descriptionField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String category = categoryField.getText();

            Expense expense = new Expense(description, amount, category);
            expenseTracker.addExpense(expense);

            
            descriptionField.setText("");
            amountField.setText("");
            categoryField.setText("");

        } else if (e.getActionCommand().equals("View Expenses")) {
            expenses = expenseTracker.viewExpenses();

            JFrame viewFrame = new JFrame("View Expenses");
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            StringBuilder message = new StringBuilder("Expenses:\n");
            for (Expense expense : expenses) {
                message.append(expense.toString()).append("\n");
            }

            textArea.setText(message.toString());
            viewFrame.add(new JScrollPane(textArea));

            viewFrame.setSize(300, 200);
            viewFrame.setVisible(true);

        } else if (e.getActionCommand().equals("Expense Summary")) {
           
            String[] categoryOptions = new String[expenseTracker.getAllCategories().size() + 1];
            categoryOptions[0] = "All Categories";
            for (int i = 0; i < expenseTracker.getAllCategories().size(); i++) {
                categoryOptions[i + 1] = expenseTracker.getAllCategories().get(i);
            }

          
            String selectedCategory = (String) JOptionPane.showInputDialog(
                    this,
                    "Select a category",
                    "View Expenses",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    categoryOptions,
                    categoryOptions[0]);

            StringBuilder message = new StringBuilder("Expenses:\n");

            if (selectedCategory != null) {
                if (selectedCategory.equals("All Categories")) {
                    for (Expense expense : expenses) {
                        message.append(expense.toString()).append("\n");
                    }
                } else {
                    for (Expense expense : expenses) {
                        if (expense.getCategory().equalsIgnoreCase(selectedCategory)) {
                            message.append(expense.toString()).append("\n");
                        }
                    }
                }

                
                JFrame viewFrame = new JFrame("View Expenses");
                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                textArea.setText(message.toString());
                viewFrame.add(new JScrollPane(textArea));
                viewFrame.setSize(500, 500);
                viewFrame.setVisible(true);
            }
        }else if (e.getActionCommand().equals("Expense Summary")) {
            double total = expenseTracker.getTotalExpensesForCategory(null);
            JOptionPane.showMessageDialog(this, "Total expenses for all categories: " + total, "Expense Summary", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
