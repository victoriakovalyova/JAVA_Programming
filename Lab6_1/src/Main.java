import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class Main {
    static class Candy implements Comparable<Candy> {
        String name;
        double pricePerKg;
        public Candy(String name, double pricePerKg) {
            this.name = name;
            this.pricePerKg = pricePerKg;
        }
        @Override
        public int compareTo(Candy other) {
            return Double.compare(other.pricePerKg, this.pricePerKg);
        }

        @Override
        public String toString() {
            return name;
        }
    }
    public static void writeFile(String text, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(text);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Object> readFile(String filename) {
        List<Object> data = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            double money = Double.parseDouble(scanner.nextLine());
            data.add(money);
            List<Candy> candies = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(" ");
                String candyName = parts[0];
                double pricePerKg = Double.parseDouble(parts[1]);
                candies.add(new Candy(candyName, pricePerKg));
            }
            data.add(candies);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static List<Candy> chooseCandies(double money, List<Candy> candies) {
        int k=0;
        Collections.sort(candies, Collections.reverseOrder());
        List<Candy> possibleChoices = new ArrayList<>();
        double remainingMoney = money;
        for (Candy candy : candies) {
            if (remainingMoney >= candy.pricePerKg) {
                possibleChoices.add(candy);
                remainingMoney -= candy.pricePerKg;
            }
        }
        if (!possibleChoices.isEmpty() && remainingMoney > 0) {
            for (int i = possibleChoices.size() - 1; i >= 0; i--) {
                Candy cheaperCandy = possibleChoices.get(i);
                for (Candy expensiveCandy : candies) {
                    k++;
                    if (!possibleChoices.contains(expensiveCandy) && remainingMoney + cheaperCandy.pricePerKg >= expensiveCandy.pricePerKg) {
                        remainingMoney += cheaperCandy.pricePerKg - expensiveCandy.pricePerKg;
                        possibleChoices.set(i, expensiveCandy);
                        break;
                    }
                }
            }
        }
        return possibleChoices;
    }




    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab6");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JButton button = new JButton("Расчет");
        JButton button1 = new JButton("Экспорт из файла");
        JButton button2 = new JButton("Импорт в файл");
        JTextArea textArea = new JTextArea(15, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JTextArea inputArea = new JTextArea(15, 30);
        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        JTextField textField = new JTextField(20);
        JTextField textField2 = new JTextField(20);
        JTextField textField3 = new JTextField(20);
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Integer> weights1 = new ArrayList<>();
                String input = textField.getText();
                List<Object> data = readFile(input);
                double money = (double) data.get(0);
                List<Candy> candies = (List<Candy>) data.get(1);
                List<Candy> chosenCandies = chooseCandies(money, candies);
                double remainingMoney = money;
                textArea.setText("");
                textArea.append("Выбранные конфеты:\n");
                for (Candy candy : chosenCandies) {
                    remainingMoney -= candy.pricePerKg;
                    textArea.append(candy.toString() + "\n");
                }
                textArea.append("Остаток денег: " + remainingMoney + "\n");
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text1 = textArea.getText();
                writeFile(text1,"results.txt");
            }
        });


        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Читаем данные из JTextArea
                List<Object> data = readFromTextArea(inputArea);
                double money = (double) data.get(0);
                List<Candy> candies = (List<Candy>) data.get(1);
                List<Candy> chosenCandies = chooseCandies(money, candies);
                StringBuilder result = new StringBuilder();
                result.append("Выбранные конфеты:\n");
                double remainingMoney = money;
                for (Candy candy : chosenCandies) {
                    remainingMoney -= candy.pricePerKg;
                    result.append(candy.toString()).append("\n");
                }
                result.append("Остаток денег: ").append(remainingMoney).append("\n");
                textArea.setText(result.toString());
            }
        });
        panel.add(inputScrollPane);
        panel.add(scrollPane);
        frame.add(panel);
        panel.add(textField);
        panel.add(button1);
        panel.add(button);
        panel.add(button2);
        frame.setVisible(true);
    }
    public static List<Object> readFromTextArea(JTextArea textArea) {
        List<Object> data = new ArrayList<>();
        String[] lines = textArea.getText().split("\n");
        double money = Double.parseDouble(lines[0]);
        data.add(money);
        List<Candy> candies = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(" ");
            String candyName = parts[0];
            double pricePerKg = Double.parseDouble(parts[1]);
            candies.add(new Candy(candyName, pricePerKg));
        }
        data.add(candies);
        return data;
    }
}