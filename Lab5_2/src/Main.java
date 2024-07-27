import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Main {
    public static List<Integer> Convert(String text1) {
        List<Integer> convert = new ArrayList<>();
        String[] parts = text1.split("\\s+");
        for (String part : parts) {
            try {
                int number = Integer.parseInt(part.trim());
                convert.add(number);
            } catch (NumberFormatException e) {
            }
        }
        return convert;
    }

    public static void writeFile(String text, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(List<Integer> weights, String filePath) {
        boolean FirstL = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line.trim());
                weights.add(number);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Buble(List<List<Integer>> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).get(1) < list.get(j + 1).get(1)) {
                    Collections.swap(list, j, j + 1);
                    if (list.get(j).size() > 1 && list.get(j + 1).size() > 1) {
                        Collections.swap(list.get(j), 0, 0);
                        Collections.swap(list.get(j + 1), 0, 0);
                    }
                }
            }
        }
    }

    public static void PrintConsole(List<List<Integer>> list, JTextArea textArea) {
        textArea.setText("");
        for (List<Integer> innerList : list) {
            int weight = innerList.get(1);
            for (int j = 0; j < weight; j++) {
               textArea.append(innerList.get(0) + " ");
            }
        }
    }

    public static List<List<Integer>> createNewList(List<Integer> list) {
        List<List<Integer>> newList = new ArrayList<>();
        Map<Integer, Integer> number = new HashMap<>();
        for (int num : list) {
            number.put(num, number.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : number.entrySet()) {
            List<Integer> innerList = new ArrayList<>();
            innerList.add(entry.getKey());
            innerList.add(entry.getValue());
            newList.add(innerList);
        }
        Buble(newList);
        return newList;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        JFrame frame = new JFrame("Lab5");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JButton button = new JButton("Расчет");
        JButton button1 = new JButton("Расчет из файла");
        JButton button2 = new JButton("Запись в файл");
        JTextArea textArea = new JTextArea(15, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JTextField textField = new JTextField(20);
        JTextField textField3 = new JTextField(20);
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Integer> weights = Convert(textField3.getText());
                List<List<Integer>> result = createNewList(weights);
                PrintConsole(result, textArea);
            }
        });

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                list.clear();
                String input = textField.getText();
                readFile(list, input);
                List<List<Integer>> result = createNewList(list);
                PrintConsole(result, textArea);
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text1 = textArea.getText();
                writeFile(text1,"results.txt");
            }
        });
        panel.add(button);
        panel.add(button2);
        panel.add(textField3);
        panel.add(scrollPane);
        frame.add(panel);
        panel.add(textField);
        panel.add(button1);
        frame.setVisible(true);
    }
}
