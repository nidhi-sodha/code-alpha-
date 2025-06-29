import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StockTradingApp {

    private static Stock[] stocks = {
        new Stock("AAPL", 180.25),
        new Stock("GOOGL", 2750.30),
        new Stock("TSLA", 720.50)
    };

    public static void main(String[] args) {
        // Show login window first
        showLoginWindow();
    }

    private static void showLoginWindow() {
        JFrame loginFrame = new JFrame("Welcome to Stock Trading App");
        loginFrame.setSize(300, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Enter Name:");
        JTextField nameField = new JTextField();
        JLabel idLabel = new JLabel("Enter ID:");
        JTextField idField = new JTextField();
        JButton startButton = new JButton("Start Trading");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(new JLabel()); // empty cell
        panel.add(startButton);

        loginFrame.add(panel);
        loginFrame.setVisible(true);

        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();

            if (name.isEmpty() || id.isEmpty()) {
                JOptionPane.showMessageDialog(loginFrame, "Please enter both name and ID.");
                return;
            }

            // Proceed to trading interface
            loginFrame.dispose(); // close login window
            showTradingWindow(name, id);
        });
    }

    private static void showTradingWindow(String userName, String userId) {
        User user = new User(userName, userId);

        JFrame frame = new JFrame("Stock Trading - " + userName);
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea outputArea = new JTextArea(12, 40);
        outputArea.setEditable(false);

        JComboBox<Stock> stockList = new JComboBox<>(stocks);
        JTextField quantityField = new JTextField(5);
        JButton buyButton = new JButton("Buy");
        JButton portfolioButton = new JButton("View Portfolio");

        buyButton.addActionListener(e -> {
            Stock selectedStock = (Stock) stockList.getSelectedItem();
            try {
                int qty = Integer.parseInt(quantityField.getText());
                if (qty > 0) {
                    user.buyStock(selectedStock.getSymbol(), qty);
                    outputArea.append("Bought " + qty + " shares of " + selectedStock.getSymbol() + "\n");
                } else {
                    outputArea.append("Quantity must be greater than 0.\n");
                }
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid quantity.\n");
            }
        });

        portfolioButton.addActionListener(e -> {
    Object[][] data = user.getPortfolioData();
    String[] columnNames = {"Stock Symbol", "Quantity"};

    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);

    JFrame tableFrame = new JFrame(user.getName() + "'s Portfolio");
    tableFrame.setSize(300, 200);
    tableFrame.add(scrollPane);
    tableFrame.setVisible(true);
});


        JPanel panel = new JPanel();
        panel.add(new JLabel("Stock:"));
        panel.add(stockList);
        panel.add(new JLabel("Qty:"));
        panel.add(quantityField);
        panel.add(buyButton);
        panel.add(portfolioButton);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(outputArea));
        frame.setVisible(true);
    }
}
