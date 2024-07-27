import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends JFrame {
    private JTextArea textInputArea;
    private JTextArea rootsInputArea;
    private JTextField fileNameField;
    private JTable resultsTable;
    private JButton correctButton, loadButton, saveButton;
    private DefaultTableModel tableModel;

    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout(5, 5));
        textInputArea = new JTextArea("");
        rootsInputArea = new JTextArea("");
        fileNameField = new JTextField("");

        JPanel northPanel = new JPanel(new GridLayout(2, 1));
        northPanel.add(new JScrollPane(textInputArea));
        northPanel.add(fileNameField);
        add(northPanel, BorderLayout.NORTH);

        JPanel westPanel = new JPanel(new BorderLayout());
        westPanel.add(new JLabel("Словарек"), BorderLayout.NORTH);
        westPanel.add(new JScrollPane(rootsInputArea), BorderLayout.CENTER);
        add(westPanel, BorderLayout.WEST);

        String[] columnNames = {""};
        tableModel = new DefaultTableModel(columnNames, 0);
        resultsTable = new JTable(tableModel);
        add(new JScrollPane(resultsTable), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        correctButton = new JButton("Расчет");
        loadButton = new JButton("Экспорт из файла");
        saveButton = new JButton("Импорт в файл");
        buttonsPanel.add(correctButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(saveButton);
        add(buttonsPanel, BorderLayout.SOUTH);

        correctButton.addActionListener(this::correctText);
        loadButton.addActionListener(e -> loadFromFile());
        saveButton.addActionListener(e -> saveToFile());

        setVisible(true);
    }

    private void correctText(ActionEvent e) {
        List<String> roots = new ArrayList<>();
        String rootsText = rootsInputArea.getText();
        Scanner scanner = new Scanner(rootsText);
        while (scanner.hasNextLine()) {
            roots.add(scanner.nextLine().trim());
        }
        scanner.close();

        String correctedText = Results(textInputArea.getText(), roots);
        tableModel.setRowCount(0);
        tableModel.addRow(new Object[]{correctedText});
    }

    private void loadFromFile() {
        File file = new File(fileNameField.getText().trim());
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                StringBuilder fileContent = new StringBuilder();
                if (scanner.hasNextLine()) {
                    textInputArea.setText(scanner.nextLine());
                }
                while (scanner.hasNextLine()) {
                    fileContent.append(scanner.nextLine()).append("\n");
                }
                rootsInputArea.setText(fileContent.toString());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Файл не найден.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ошибка.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveToFile() {
        File file = new File("2.txt");
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(textInputArea.getText());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Ошибка", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static String Results(String text, List<String> roots) {
        StringBuilder correctedText = new StringBuilder();
        List<String> textWords = splitWords(text);
        for (String word : textWords) {
            String correctedWord = promezh(word, roots);
            if (word.length() != correctedWord.length()) {
                correctedWord = correctedWord.substring(0, word.length());
            }
            correctedText.append(correctedWord).append(" ");
        }
        return correctedText.toString().trim();
    }
        public static List<String> splitWords(String text) {
            List<String> words = new ArrayList<>();
            String[] wordArray = text.split("\\s+");
            for (String word : wordArray) {
                word = word.replaceAll("[^а-яА-ЯёЁa-zA-Z]", "");
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
            return words;
        }
        public static boolean isOneCharacterDifference(String str1, String str2) {
            if (Math.abs(str1.length() - str2.length()) != 1) {
                return false;
            }

            int minLength = Math.min(str1.length(), str2.length());
            int diffCount = 0;
            for (int i = 0; i < minLength; i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    diffCount++;
                    if (diffCount > 1) {
                        return false;
                    }
                }
            }

            return diffCount == 1 || Math.abs(str1.length() - str2.length()) == 1;
        }
        public static String promezh(String s, List<String> roots) {
            for (int i = s.length(); i > 0; i--) {
                String str1 = s.substring(0, i);
                boolean replaced = false;
                for (String root : roots) {
                    if (isOneCharacterDifference(str1, root)) {
                        if (i - 1 == root.length()) {
                            s = root + s.substring(i - 1);
                        } else {
                            s = root + s.substring(i);
                        }
                        replaced = true;
                        break;
                    }
                }
                if (replaced) {
                    break;
                }
            }
            return s;
        }
        public static void main(String[] args) {
            SwingUtilities.invokeLater(Main::new);
        }
    }
