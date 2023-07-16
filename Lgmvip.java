import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyJTextFieldLimit extends PlainDocument {
    private int limit;

    MyJTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
        if (str == null)
            return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}

public class Lgmvip extends JFrame {
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JTextField amountTextField;
    private JLabel resultLabel;

    // Conversion rates
    private static final double USD_TO_EUR = 0.85;
    private static final double USD_TO_GBP = 0.75;
    private static final double USD_TO_JPY = 110.89;
    private static final double USD_TO_AUD = 1.35;
    private static final double USD_TO_INR = 74.5;
    private static final double EUR_TO_USD = 1.18;
    private static final double GBP_TO_USD = 1.34;
    private static final double JPY_TO_USD = 0.009;
    private static final double AUD_TO_USD = 0.74;
    private static final double INR_TO_USD = 0.013;

    public Lgmvip() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        // Currency options
        String[] currencies = {"USD", "EUR", "GBP", "JPY", "AUD", "INR"};

        // Components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(fromLabel.getFont().deriveFont(Font.PLAIN, 20)); // Increase font size
        fromCurrencyComboBox = new JComboBox<>(currencies);
        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(toLabel.getFont().deriveFont(Font.PLAIN, 20)); // Increase font size
        toCurrencyComboBox = new JComboBox<>(currencies);
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(amountLabel.getFont().deriveFont(Font.PLAIN, 20)); // Increase font size
        amountTextField = new JTextField();
        amountTextField.setDocument(new MyJTextFieldLimit(10)); // Limit the input length to 10 characters
        amountTextField.setFont(amountTextField.getFont().deriveFont(Font.PLAIN, 30)); // Increase font size
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel();
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 42)); // Increase font size

        // Convert button ActionListener
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        inputPanel.add(fromLabel);
        inputPanel.add(fromCurrencyComboBox);
        inputPanel.add(toLabel);
        inputPanel.add(toCurrencyComboBox);
        inputPanel.add(amountLabel);
        inputPanel.add(amountTextField);
        inputPanel.add(convertButton);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        resultPanel.add(resultLabel);

        add(inputPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window after setting it visible
    }

    private void convertCurrency() {
        String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
        String toCurrency = (String) toCurrencyComboBox.getSelectedItem();
        double amount;
        try {
            amount = Double.parseDouble(amountTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double convertedAmount;
        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            convertedAmount = amount * USD_TO_EUR;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("GBP")) {
            convertedAmount = amount * USD_TO_GBP;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("JPY")) {
            convertedAmount = amount * USD_TO_JPY;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("AUD")) {
            convertedAmount = amount * USD_TO_AUD;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("INR")) {
            convertedAmount = amount * USD_TO_INR;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            convertedAmount = amount * EUR_TO_USD;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("USD")) {
            convertedAmount = amount * GBP_TO_USD;
        } else if (fromCurrency.equals("JPY") && toCurrency.equals("USD")) {
            convertedAmount = amount * JPY_TO_USD;
        } else if (fromCurrency.equals("AUD") && toCurrency.equals("USD")) {
            convertedAmount = amount * AUD_TO_USD;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("USD")) {
            convertedAmount = amount * INR_TO_USD;
        } else {
            JOptionPane.showMessageDialog(this, "Unsupported conversion", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Lgmvip();
            }
        });
    }
}

