import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;


public class Main {

    public static void writefile(String answer){
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int[][] readfile(String path, int row, int column){

        int[][] matrix = null;
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            int numRows = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                numRows++;
            }
            scanner.close();

            matrix = new int[numRows][];
            scanner = new Scanner(file);
            int rowNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] numbers = line.split(" ");
                matrix[rowNum] = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    matrix[rowNum][i] = Integer.parseInt(numbers[i]);
                }
                rowNum++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return matrix;
    }
    public static int solution(int[][] field) {
        int winner = 0, x, y, count;
        boolean OneWin = false, TwoWin = false;


        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {

                x = j;
                y = i;
                count = 0;
                while ((x < field[i].length) && (field[y][x] == field[i][j])) {
                    count++;
                    x++;
                }
                if (count > 4) {
                    if (field[i][j] == 2) {
                        OneWin = true;
                    }
                    if (field[i][j] == 1) {
                        TwoWin = true;
                    }
                }
                x = j;
                y = i;
                count = 0;
                while ((x > 0) && (field[y][x] == field[i][j])) {
                    count++;
                    x--;
                }
                if (count > 4) {
                    if (field[i][j] == 2) {
                        OneWin = true;
                    }
                    if (field[i][j] == 1) {
                        TwoWin = true;
                    }
                }

                x = j;
                y = i;
                count = 0;
                while ((y < field.length) && (field[y][x] == field[i][j])) {
                    count++;
                    y++;
                }
                if (count > 4) {
                    if (field[i][j] == 2) {
                        OneWin = true;
                    }
                    if (field[i][j] == 1) {
                        TwoWin = true;
                    }
                }

                //UP
                x = j;
                y = i;
                count = 0;
                while ((y > 0) && (field[y][x] == field[i][j])) {
                    count++;
                    y--;
                }
                if (count > 4) {
                    if (field[i][j] == 2) {
                        OneWin = true;
                    }
                    if (field[i][j] == 1) {
                        TwoWin = true;
                    }
                }
                x = j;
                y = i;
                count = 0;
                while ((x < field[i].length) && (y < field.length) && (field[y][x] == field[i][j])) {
                    count++;
                    x++;
                    y++;
                }
                if (count > 4) {
                    if (field[i][j] == 2) {
                        OneWin = true;
                    }
                    if (field[i][j] == 1) {
                        TwoWin = true;
                    }
                }
                x = j;
                y = i;
                count = 0;
                while ((x > 0) && (y > 0) && (field[y][x] == field[i][j])) {
                    count++;
                    x--;
                    y--;
                }
                if (count > 4) {
                    if (field[i][j] == 2) {
                        OneWin = true;
                    }
                    if (field[i][j] == 1) {
                        TwoWin = true;
                    }
                }

                x = j;
                y = i;
                count = 0;
                while ((x < field[i].length) && (y > 0) && (field[y][x] == field[i][j])) {
                    count++;
                    x++;
                    y--;
                }
                if (count > 4) {
                    if (field[i][j] == 2) {
                        OneWin = true;
                    }
                    if (field[i][j] == 1) {
                        TwoWin = true;
                    }
                }
                x = j;
                y = i;
                count = 0;
                while ((x > 0) && (y < field.length) && (field[y][x] == field[i][j])) {
                    count++;
                    x--;
                    y++;
                }
                if (count > 4) {
                    if (field[i][j] == 2) {
                        OneWin = true;
                    }
                    if (field[i][j] == 1) {
                        TwoWin = true;
                    }
                }

            }
        }

        if (OneWin && !TwoWin) winner = 1;
        if (!OneWin && TwoWin) winner = -1;

        return winner;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название файла: ");
        String path = scanner.nextLine();
        new Form2(path);
    }

    static class Form2 extends JFrame {
        JTable table;
        JFrame frame;
        JButton button;
        JButton button2;
        DefaultTableModel model;
        JPanel panel;

        Form2(String path){
            frame = new JFrame();
            panel = new JPanel(new GridLayout(3,1));
            model = new DefaultTableModel(2,1);
            table = new JTable(model);
            button = new JButton("SolutionFromFile");
            button2 = new JButton("Solution");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int N = Integer.parseInt(table.getValueAt(0, 0).toString());
                    int M = Integer.parseInt(table.getValueAt(1, 0).toString());
                    new Form(path, N, M);
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int N = Integer.parseInt(table.getValueAt(0, 0).toString());
                    int M = Integer.parseInt(table.getValueAt(1, 0).toString());
                    new Form(path, N, M,1);
                }
            });

            frame.add(panel);
            panel.add(table);
            panel.add(button);
            panel.add(button2);

            frame.setSize(500,500);
            frame.setLocation(new Point(670,300));

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }

    static class Form extends JFrame {
        JTable table;
        JTable table2;
        JFrame frame;
        JPanel panel;
        JPanel panel2;
        JPanel panel3;
        JButton button;
        JButton button3;
        DefaultTableModel model;
        DefaultTableModel model2;
        Form(String path, int N, int M){
            int [][] field = readfile(path, N, M);
            frame = new JFrame();
            panel = new JPanel(new GridLayout(2,1));
            panel2 = new JPanel(new GridLayout(1,1));
            panel3 = new JPanel(new GridLayout(3,1));

            model = new DefaultTableModel(N,M);
            model2 = new DefaultTableModel(1,1);

            table = new JTable(model);
            table2 = new JTable(model2);

            button = new JButton("Считать данные");
            button3 = new JButton("Вывести результат игры");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < M; j++) {
                            if (field[i][j] != 0) {
                                table.setValueAt(field[i][j], i, j);
                            }
                        }
                    }
                }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            try {
                                if((table.getValueAt(i, j) != null) && (table.getValueAt(i, j).toString() != "")) {

                                    if ((Integer.parseInt(table.getValueAt(i, j).toString()) == 1) || (Integer.parseInt(table.getValueAt(i, j).toString()) == 2)) {
                                        field[i][j] = Integer.parseInt(table.getValueAt(i, j).toString());
                                    }
                                } else {
                                    field[i][j] = 0;
                                }
                            } catch (NumberFormatException ex){
                                ex.printStackTrace();
                            }

                            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                                for (int k = 0; k < table.getRowCount(); k++) {
                                    for (int value : field[k]) {
                                        if(value == 8){
                                            writer.write(" ");
                                        } else {
                                            writer.write(String.valueOf(value));
                                        }
                                        writer.write(" ");
                                    }
                                    writer.newLine();
                                }
                            } catch (IOException p) {
                                p.printStackTrace();
                            }

                        }
                    }

                    int answer = solution(field);

                    if (answer == 1) {
                        table2.setValueAt("Победили 2", 0, 0);
                    }
                    if (answer == -1) {
                        table2.setValueAt("Победили 1", 0, 0);
                    }
                    if (answer == 0) {
                        table2.setValueAt("Победителя нет", 0, 0);
                    }
                    String answer2 = table2.getValueAt(0,0).toString();
                    writefile(answer2);

                }
            });

            frame.add(panel);
            panel.add(panel2);
            panel.add(panel3);

            panel2.add(table);
            panel3.add(table2);
            panel3.add(button3);
            panel3.add(button);

            frame.setSize(400,500);
            frame.setLocation(new Point(670,300));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
        Form(String path, int N, int M,int i){

            frame = new JFrame();
            panel = new JPanel(new GridLayout(2,1));
            panel2 = new JPanel(new GridLayout(1,1));
            panel3 = new JPanel(new GridLayout(3,1));

            model = new DefaultTableModel(N,M);
            model2 = new DefaultTableModel(1,1);

            table = new JTable(model);
            table2 = new JTable(model2);

            button3 = new JButton("Смена хода");

            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rowCount = table.getRowCount();
                    int columnCount = table.getColumnCount();
                    int [][] field = new int[rowCount][columnCount];

                    int flag=0;
                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            if((table.getValueAt(i, j) == null) || (table.getValueAt(i, j).toString() == "")) {
                                flag = 1;
                            }
                        }
                    }

                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            try {
                                if((table.getValueAt(i, j) != null) && (table.getValueAt(i, j).toString() != "")) {

                                    if ((Integer.parseInt(table.getValueAt(i, j).toString()) == 1) || (Integer.parseInt(table.getValueAt(i, j).toString()) == 2)) {
                                        field[i][j] = Integer.parseInt(table.getValueAt(i, j).toString());
                                    }
                                } else {
                                    field[i][j] = 0;
                                }
                            } catch (NumberFormatException ex){
                                ex.printStackTrace();
                            }

                            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                                for (int k = 0; k < table.getRowCount(); k++) {
                                    for (int value : field[k]) {
                                        if(value == 8){
                                            writer.write(" ");
                                        } else {
                                            writer.write(String.valueOf(value));
                                        }
                                        writer.write(" ");
                                    }
                                    writer.newLine();
                                }
                            } catch (IOException p) {
                                p.printStackTrace();
                            }

                        }
                    }


                    int answer = solution(field);
                    if (answer==0 && flag==1){
                        return;
                    }

                    if (answer == 1) {
                        table2.setValueAt("Победили 2", 0, 0);
                    }
                    if (answer == -1) {
                        table2.setValueAt("Победили 1", 0, 0);
                    }
                    if (answer == 0 && flag==0) {
                        table2.setValueAt("Победителя нет", 0, 0);
                    }
                    String answer2 = table2.getValueAt(0,0).toString();
                    writefile(answer2);

                }
            });

            frame.add(panel);
            panel.add(panel2);
            panel.add(panel3);

            panel2.add(table);
            panel3.add(table2);
            panel3.add(button3);


            frame.setSize(400,500);
            frame.setLocation(new Point(670,300));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }
}