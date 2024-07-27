/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class OrderedSequenceChecker {
    //Чтение из файла
    public static int[][] readArrayFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int rows = scanner.nextInt();
            int cols = scanner.nextInt();

            int[][] array = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    array[i][j] = scanner.nextInt();
                }
            }

            scanner.close();
            return array;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    // С левого нижнего угла
    public static boolean isOrderedSequence_down_left(int[][] array) {
        int n = array.length;
        int expectedValue = 1;
        int test = 0;

        for (int i = 0; i < n; i++) {

            if (test % 2 == 0) {

                for (int j = array.length-1; j >= 0; j--) {


                    if (array[j][i] != expectedValue) {
                        return false;
                    }
                    expectedValue++;
                }
                test++;

            } else {

                for (int j = 0; j < array.length; j++) {


                    if (array[j][i] != expectedValue) {
                        return false;
                    }
                    expectedValue++;
                }
                test++;
            }
        }

        return true;
    }
    // С левого верхнего угла
    public static boolean isOrderedSequence_up_left(int[][] array) {
        int n = array.length;
        int expectedValue = 1;
        int test = 0;

        for (int i = 0; i < n; i++) {

            if (test % 2 != 0) {

                for (int j = array.length-1; j >= 0; j--) {


                    if (array[j][i] != expectedValue) {
                        return false;
                    }
                    expectedValue++;
                }
                test++;

            } else {

                for (int j = 0; j < array.length; j++) {


                    if (array[j][i] != expectedValue) {
                        return false;
                    }
                    expectedValue++;
                }
                test++;
            }
        }

        return true;
    }
    // С правого нижнего угла
    public static boolean isOrderedSequence_down_right(int[][] array) {
        int n = array.length;
        int expectedValue = 1;
        int test = 0;

        for (int i = n-1; i >= 0; i--) {

            if (test % 2 == 0) {

                for (int j = array.length-1; j >= 0; j--) {


                    if (array[j][i] != expectedValue) {
                        return false;
                    }
                    expectedValue++;
                }
                test++;

            } else {

                for (int j = 0; j < array.length; j++) {


                    if (array[j][i] != expectedValue) {
                        return false;
                    }
                    expectedValue++;
                }
                test++;
            }
        }

        return true;
    }
    // С правого верхнего угла
    public static boolean isOrderedSequence_up_right(int[][] array) {
        int n = array.length;
        int expectedValue = 1;
        int test = 0;

        for (int i = n-1; i >= 0; i--) {

            if (test % 2 != 0) {

                for (int j = array.length-1; j >= 0; j--) {


                    if (array[j][i] != expectedValue) {
                        return false;
                    }
                    expectedValue++;
                }
                test++;

            } else {

                for (int j = 0; j < array.length; j++) {


                    if (array[j][i] != expectedValue) {
                        return false;
                    }
                    expectedValue++;
                }
                test++;
            }


        }

        return true;
    }

    public static void main(String[] args) {

        int[][] array = readArrayFromFile("input.txt");
        if (array != null) {
            // Выводим считанный массив на экран
            for (int[] row : array) {
                for (int num : row) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Ошибка при чтении массива из файла.");
        }

        */
/*int[][] array = {

                {3, 4, 9},
                {2, 5, 8},
                {1, 6, 7},

        };*//*


        assert array != null;
        if (isOrderedSequence_down_left(array)) {
            System.out.println("Массив образует упорядоченную последовательность.");
        } else {
            System.out.println("Массив НЕ образует упорядоченную последовательность.");
        }
    }
}*/


import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.StringBuffer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends JFrame {

    private JTextField fileNameField;
    private JTextArea arrayTextArea;
    private JTextArea result;

    public Main() {
        setTitle("Задание 19");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel NameText = new JLabel("Введите текст");
        arrayTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane1 = new JScrollPane(arrayTextArea);

        JButton loadArrayButton = new JButton("Выбрать аббревиатуры");
        loadArrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String TextArea = arrayTextArea.getText();
                List <String> array = GetAbb(TextArea);
                for (String word: array){
                    result.setText(word);
                }

            }
        });
        JLabel resultText = new JLabel("Результат программы");
        result = new JTextArea(10, 30);
        JScrollPane scrollPane2 = new JScrollPane(result);

        panel.add(NameText);
        panel.add(arrayTextArea);
        panel.add(loadArrayButton);
        panel.add(resultText);
        panel.add(result);
        panel.add(scrollPane1);
        panel.add(scrollPane2);

        add(panel);

        setVisible(true);

    }
    public static List<String> GetAbb(String text){
        List <String> result = new ArrayList<>();
        Matcher m = Pattern.compile("[A-ZА-ЯЁ]{2,6}").matcher(text);
        while (m.find()) {
            result.add(m.group());
        }
        return result;
    }

    public static void main(String[] args) {
        new Main();
    }
}



















/*
        JButton loadArrayButton = new JButton("Загрузить массив из файла");
        loadArrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = fileNameField.getText();
                int[][] array = readArrayFromFile(fileName);
                if (array != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int[] row : array) {
                        for (int num : row) {
                            sb.append(num).append(" ");
                        }
                        sb.append("\n");
                    }
                    arrayTextArea.setText(sb.toString());
                } else {
                    arrayTextArea.setText("Ошибка чтения файла");
                }
            }
        });

        arrayTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(arrayTextArea);

        JButton checkButton = new JButton("Найти порядок в массиве");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] array = readArrayFromFile(fileNameField.getText());
                int bag = 1;
                if (array != null) {
                    if (Chek1(array)){
                        JOptionPane.showMessageDialog(Main.this, "Массив образует упорядоченную последовательность.");
                        bag++;
                    } else {
                        JOptionPane.showMessageDialog(Main.this, "Массив НЕ образует упорядоченную последовность.");
                        bag++;
                    }
                    bag++;
                } else {
                    JOptionPane.showMessageDialog(Main.this, "Ошибка при чтении массива из файла.");
                }
            }
        });

        panel.add(fileNameLabel);
        panel.add(fileNameField);
        panel.add(loadArrayButton);
        panel.add(scrollPane);
        panel.add(checkButton);
        add(panel);

        setVisible(true);
    }

    public static int[][] readArrayFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int rows = scanner.nextInt();
            int cols = scanner.nextInt();

            int[][] array = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    array[i][j] = scanner.nextInt();
                }
            }

            scanner.close();
            return array;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean Chek1(int [][] array){
        int old = 0; //array[array[0].length][0];
        for (int x = 0; x < array.length; x ++){
            for (int y = array[0].length - 1; y >= 0; y--){
                int num = array[y][x];
                if (num <= old) return false;
                old = num;

            }
            x++;
            if (x == array.length) return true;
            for (int y = 0; y < array[0].length; y++){
                int num = array[y][x];
                if (num < old) return false;
                old = num;
            }

        }
        return  true;
    }
    public static boolean Chek2(int [][] array){
        int old = (array[0][array.length-1]) + 1;
        for (int x = 0; x < array.length; x ++){
            for (int y = array[0].length - 1; y >= 0; y--){
                int num = array[y][x];
                if (num >= old) return false;
                old = num;

            }
            x++;
            if (x == array.length) return true;
            for (int y = 0; y < array[0].length; y++){
                int num = array[y][x];
                if (num > old) return false;
                old = num;
            }

        }
        return  true;
    }

    public static void main(String[] args) {
        new Main();
    }
}*/

/*
setTitle("Задание 19");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel fileNameLabel = new JLabel("Введите имя файла:");
        fileNameField = new JTextField(20);

        JButton loadArrayButton = new JButton("Загрузить массив из файла");
        loadArrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = fileNameField.getText();
                int[][] array = readArrayFromFile(fileName);
                if (array != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int[] row : array) {
                        for (int num : row) {
                            sb.append(num).append(" ");
                        }
                        sb.append("\n");
                    }
                    arrayTextArea.setText(sb.toString());
                } else {
                    arrayTextArea.setText("Ошибка чтения файла");
                }
            }
        });

        arrayTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(arrayTextArea);

        JButton checkButton = new JButton("Найти порядок в массиве");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] array = readArrayFromFile(fileNameField.getText());
                int bag = 1;
                if (array != null) {
                    if (Chek1(array)){
                        JOptionPane.showMessageDialog(Main.this, "Массив образует упорядоченную последовательность.");
                        bag++;
                    } else {
                        JOptionPane.showMessageDialog(Main.this, "Массив НЕ образует упорядоченную последовность.");
                        bag++;
                    }
                    bag++;
                } else {
                    JOptionPane.showMessageDialog(Main.this, "Ошибка при чтении массива из файла.");
                }
            }
        });

        panel.add(fileNameLabel);
        panel.add(fileNameField);
        panel.add(loadArrayButton);
        panel.add(scrollPane);
        panel.add(checkButton);
        add(panel);

        setVisible(true);
    }

    public static int[][] readArrayFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int rows = scanner.nextInt();
            int cols = scanner.nextInt();

            int[][] array = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    array[i][j] = scanner.nextInt();
                }
            }

            scanner.close();
            return array;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean Chek1(int [][] array){
        int old = 0; //array[array[0].length][0];
        for (int x = 0; x < array.length; x ++){
            for (int y = array[0].length - 1; y >= 0; y--){
                int num = array[y][x];
                if (num <= old) return false;
                old = num;

            }
            x++;
            if (x == array.length) return true;
            for (int y = 0; y < array[0].length; y++){
                int num = array[y][x];
                if (num < old) return false;
                old = num;
            }

        }
        return  true;
    }
    public static boolean Chek2(int [][] array){
        int old = (array[0][array.length-1]) + 1;
        for (int x = 0; x < array.length; x ++){
            for (int y = array[0].length - 1; y >= 0; y--){
                int num = array[y][x];
                if (num >= old) return false;
                old = num;

            }
            x++;
            if (x == array.length) return true;
            for (int y = 0; y < array[0].length; y++){
                int num = array[y][x];
                if (num > old) return false;
                old = num;
            }

        }
        return  true;
    }
* */

