import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame implements ActionListener {

    JTextField celsiusInput;
    JTextField fahrenheitInput;
    JButton celsiusToFahrenheitButton;
    JButton fahrenheitToCelsiusButton;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3, 10, 20));

        celsiusInput = new JTextField();
        fahrenheitInput = new JTextField();

        Font font = new Font("Arial", Font.PLAIN, 18);
        celsiusInput.setFont(font);
        fahrenheitInput.setFont(font);

        celsiusToFahrenheitButton = new JButton("Convert to Fahrenheit");
        fahrenheitToCelsiusButton = new JButton("Convert to Celsius");
        celsiusInput.setFont(font);
        fahrenheitInput.setFont(font);

        celsiusToFahrenheitButton.addActionListener(this);
        fahrenheitToCelsiusButton.addActionListener(this);

        JLabel cel=new JLabel("Celsius: ");
        cel.setFont(font);

        JLabel fah=new JLabel("Fahrenheit: ");
        fah.setFont(font);

        panel.add(cel);
        panel.add(celsiusInput);
        panel.add(celsiusToFahrenheitButton);
        panel.add(fah);
        panel.add(fahrenheitInput);
        panel.add(fahrenheitToCelsiusButton);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == celsiusToFahrenheitButton) {
            convertCelsiusToFahrenheit();
        } else if (e.getSource() == fahrenheitToCelsiusButton) {
            convertFahrenheitToCelsius();
        }
    }

    private void convertCelsiusToFahrenheit() {
        try {
            double celsius = Double.parseDouble(celsiusInput.getText());
            double fahrenheit = (celsius * 9 / 5) + 32;
            fahrenheitInput.setText(String.format("%.2f", fahrenheit));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for Celsius. Please enter a number.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void convertFahrenheitToCelsius() {
        try {
            double fahrenheit = Double.parseDouble(fahrenheitInput.getText());
            double celsius = (fahrenheit - 32) * 5 / 9;
            celsiusInput.setText(String.format("%.2f", celsius));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for Fahrenheit. Please enter a number.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        TemperatureConverter converter = new TemperatureConverter();
        converter.setVisible(true);
    }
}
