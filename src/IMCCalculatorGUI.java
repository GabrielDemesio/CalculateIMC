import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class IMCCalculatorGUI extends JFrame {
    private JTextField weightTextField;
    private JTextField heightTextField;
    private JLabel resultLabel;

    public IMCCalculatorGUI() {
        // Configurações básicas da janela
        setTitle("Cálculo de IMC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        // Painel para os campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 10, 10)); //
        JLabel weightLabel = new JLabel("Weight (kg):");
        weightTextField = new JTextField();
        JLabel heightLabel = new JLabel("Height (m):");
        heightTextField = new JTextField();
        inputPanel.add(weightLabel);
        inputPanel.add(weightTextField);
        inputPanel.add(new JLabel()); // Espaço em branco
        inputPanel.add(heightLabel);
        inputPanel.add(heightTextField);
        inputPanel.add(new JLabel()); // Espaço em branco

        // Botão para calcular o IMC
        JButton calculateButton = new JButton("calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double weight = Double.parseDouble(weightTextField.getText());
                    double height = Double.parseDouble(heightTextField.getText());

                    IMCCalculator calculator = new IMCCalculator();
                    double imc = calculator.calculateIMC(weight, height);

                    DecimalFormat df = new DecimalFormat("0.00");
                    String resultado = "Seu IMC é: " + df.format(imc) + " - " + calculator.getIMCStatus(imc);
                    resultLabel.setText(resultado);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Digite valores válidos para Peso e Altura!");
                }
            }
        });

        // Painel para o resultado
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultLabel = new JLabel();
        resultPanel.add(resultLabel);

        // Adicionar componentes à janela
        add(inputPanel, BorderLayout.NORTH);
        add(calculateButton, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        pack(); // Ajusta o tamanho da janela para os componentes
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IMCCalculatorGUI().setVisible(true);
            }
        });
    }
}

class IMCCalculator {
    public double calculateIMC(double weight, double height) {
        return weight / (height * height);
    }

    public String getIMCStatus(double imc) {
        if (imc < 18.5) {
            return "Magreza";
        } else if (imc >= 18.5 && imc <= 24.9) {
            return "Normal";
        } else if (imc >= 25.0 && imc <= 29.9) {
            return "Sobrepeso";
        } else if (imc >= 30.0 && imc <= 39.9) {
            return "Obesidade";
        } else {
            return "Obesidade Grave";
        }
    }
}
