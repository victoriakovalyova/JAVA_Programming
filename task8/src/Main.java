import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название файла: ");
        String path = scanner.nextLine();
        new Form1(path);
    }
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
    public static boolean RightTop(int[][] field, int row, int column) {
        boolean cycle = true;
        int pos, rowstep, columnstep, rowc = 0, columnc = column - 1;

        rowstep = row;
        columnstep = column;

        pos = field[rowc + 1][columnc] - field[rowc][columnc];

        for(int i = 0; i < rowstep - 1; i++){
            rowc++;
            int cur = field[rowc][columnc];
            int past = field[rowc - 1][columnc];
            if(cur - past != pos){
                cycle = false;
                break;
            }
        }

        columnstep--;
        rowstep--;

        while((rowstep > 0)&&(columnstep > 0)){
            int cur, past;

            if(!cycle){ break; }


            for(int i = 0; i < columnstep; i++){
                columnc--;
                cur = field[rowc][columnc];
                past = field[rowc][columnc + 1];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            if(!cycle){ break; }

            for(int i = 0; i < rowstep; i++){
                rowc--;
                cur = field[rowc][columnc];
                past = field[rowc + 1][columnc];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            columnstep--;
            rowstep--;

            if((columnstep < 1) || (rowstep < 1)){break;}

            if(!cycle){ break; }

            for(int i = 0; i < columnstep; i++){
                columnc++;
                cur = field[rowc][columnc];
                past = field[rowc][columnc - 1];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            if(!cycle){ break; }

            for(int i = 0; i < rowstep; i++){
                rowc++;
                cur = field[rowc][columnc];
                past = field[rowc - 1][columnc];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            columnstep--;
            rowstep--;
        }
        System.out.println();
        return cycle;
    }
    public static boolean LeftTop(int[][] field, int row, int column) {
        boolean cycle = true;
        int pos, rowstep, columnstep, rowc = 0, columnc = 0;

        rowstep = row;
        columnstep = column;

        pos = field[rowc][columnc + 1] - field[rowc][columnc];

        for(int i = 0; i < columnstep - 1; i++){
            columnc++;
            int cur = field[rowc][columnc];
            int past = field[rowc][columnc - 1];

            if(cur - past != pos){
                cycle = false;
                break;
            }
        }

        columnstep--;
        rowstep--;


        while((rowstep > 0)&&(columnstep > 0)){
            int cur, past;

            if(!cycle){ break; }

            for(int i = 0; i < rowstep; i++){
                rowc++;
                cur = field[rowc][columnc];
                past = field[rowc - 1][columnc];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            if(!cycle){ break; }

            for(int i = 0; i < columnstep; i++){
                columnc--;
                cur = field[rowc][columnc];
                past = field[rowc][columnc + 1];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            columnstep--;
            rowstep--;

            if((columnstep < 1) || (rowstep < 1)){break;}

            if(!cycle){ break; }

            for(int i = 0; i < rowstep; i++){
                rowc--;
                cur = field[rowc][columnc];
                past = field[rowc + 1][columnc];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            if(!cycle){ break; }

            for(int i = 0; i < columnstep; i++){
                columnc++;
                cur = field[rowc][columnc];
                past = field[rowc][columnc - 1];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }
            columnstep--;
            rowstep--;
        }
        System.out.println();
        return cycle;
    }
    public static boolean RightDown(int[][] field, int row, int column) {
        boolean cycle = true;
        int pos, rowstep, columnstep, rowc = row - 1, columnc = column - 1;

        rowstep = row;
        columnstep = column;

        pos = field[rowc][columnc - 1] - field[rowc][columnc];

        for(int i = 0; i < columnstep - 1; i++){
            columnc--;
            int cur = field[rowc][columnc];
            int past = field[rowc][columnc + 1];

            if(cur - past != pos){
                cycle = false;
                break;
            }
        }

        columnstep--;
        rowstep--;


        while((rowstep > 0)&&(columnstep > 0)){
            int cur, past;

            if(!cycle){ break; }

            for(int i = 0; i < rowstep; i++){
                rowc--;
                cur = field[rowc][columnc];
                past = field[rowc + 1][columnc];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            if(!cycle){ break; }

            for(int i = 0; i < columnstep; i++){
                columnc++;
                cur = field[rowc][columnc];
                past = field[rowc][columnc - 1];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            columnstep--;
            rowstep--;

            if((columnstep < 1) || (rowstep < 1)){break;}

            if(!cycle){ break; }

            for(int i = 0; i < rowstep; i++){
                rowc++;
                cur = field[rowc][columnc];
                past = field[rowc - 1][columnc];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            if(!cycle){ break; }

            for(int i = 0; i < columnstep; i++){
                columnc--;
                cur = field[rowc][columnc];
                past = field[rowc][columnc + 1];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }
            columnstep--;
            rowstep--;
        }
        System.out.println();
        return cycle;
    }
    public static boolean LeftDown(int[][] field, int row, int column) {
        boolean cycle = true;
        int pos, rowstep, columnstep, rowc = row - 1, columnc = 0;

        rowstep = row;
        columnstep = column;

        pos = field[rowc - 1][columnc] - field[rowc][columnc];

        for(int i = 0; i < rowstep - 1; i++){
            rowc--;
            int cur = field[rowc][columnc];
            int past = field[rowc + 1][columnc];
            if(cur - past != pos){
                cycle = false;
                break;
            }
        }

        columnstep--;
        rowstep--;

        while((rowstep > 0)&&(columnstep > 0)){
            int cur, past;

            if(!cycle){ break; }

            for(int i = 0; i < columnstep; i++){
                columnc++;
                cur = field[rowc][columnc];
                past = field[rowc][columnc - 1];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            if(!cycle){ break; }

            for(int i = 0; i < rowstep; i++){
                rowc++;
                cur = field[rowc][columnc];
                past = field[rowc - 1][columnc];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            columnstep--;
            rowstep--;

            if((columnstep < 1) || (rowstep < 1)){break;}

            if(!cycle){ break; }

            for(int i = 0; i < columnstep; i++){
                columnc--;
                cur = field[rowc][columnc];
                past = field[rowc][columnc + 1];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            if(!cycle){ break; }

            for(int i = 0; i < rowstep; i++){
                rowc--;
                cur = field[rowc][columnc];
                past = field[rowc + 1][columnc];
                if(cur - past != pos){
                    cycle = false;
                    break;
                }
            }

            columnstep--;
            rowstep--;
        }
        System.out.println();
        return cycle;
    }
    static class Form1 extends JFrame {
        JTable table;
        JFrame frame;
        JButton button;
        DefaultTableModel model;
        JPanel panel;
        Form1(String path){
            frame = new JFrame();
            panel = new JPanel(new GridLayout(2,1));
            model = new DefaultTableModel(2,1);
            table = new JTable(model);
            button = new JButton("Solution");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int N = Integer.parseInt(table.getValueAt(0, 0).toString());
                    int M = Integer.parseInt(table.getValueAt(1, 0).toString());
                    new Form(path,N, M);
                }
            });

            frame.add(panel);
            panel.add(table);
            panel.add(button);
            frame.setSize(500,500);
            frame.setLocation(new Point(600,300));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }

    static class Form extends JFrame {
        JTable table, table2;
        JFrame frame;
        JPanel panel, panel2, panel3, panel4;
        JButton button2, button3, button4, button5;
        DefaultTableModel model, model2;

        Form(String path,int N, int M){
            frame = new JFrame();
            frame.setLocation(new Point(600,300));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            panel = new JPanel(new GridLayout(3,1)); //основная панель
            panel2 = new JPanel(new GridLayout(1,1)); // панель таблицы
            panel3 = new JPanel(new GridLayout(1,1)); // панель текстбокса
            panel4 = new JPanel(new GridLayout(2,2)); // панель кнопок

            model = new DefaultTableModel(N,M);
            model2 = new DefaultTableModel(1,1);

            table = new JTable(model);
            table2 = new JTable(model2);


            button2 = new JButton("LeftTop");
            button3 = new JButton("RightTop");
            button4 = new JButton("LeftDown");
            button5 = new JButton("RightDown");

            frame.setSize(500,400);
            frame.add(panel);

            panel.add(panel2);
            panel.add(panel3);
            panel.add(panel4);

            panel2.add(table);

            panel3.add(table2);

            panel4.add(button2);
            panel4.add(button3);
            panel4.add(button4);
            panel4.add(button5);


            int[][] field = readfile("input.txt", N, M);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    table.setValueAt(field[i][j], i, j);
                }
            }

            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            try {
                                if((table.getValueAt(i, j) != null) && (table.getValueAt(i, j).toString() != "")) {
                                    field[i][j] = Integer.parseInt(table.getValueAt(i, j).toString());
                                }
                            } catch (NumberFormatException ex){
                            }
                        }
                    }
                    boolean answer = LeftTop(field, N, M);

                    if(answer){
                        table2.setValueAt("Последовательность найдена", 0, 0);
                        writefile("Последовательность найдена");
                    } else{
                        table2.setValueAt("Последовательности нет", 0, 0);
                        writefile("Последовательности нет");

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
                                    field[i][j] = Integer.parseInt(table.getValueAt(i, j).toString());
                                }
                            } catch (NumberFormatException ex){
                                ex.printStackTrace();
                            }
                        }
                    }
                    boolean answer = RightTop(field, N , M);

                    if(answer){
                        table2.setValueAt("Последовательность найдена", 0, 0);
                        writefile("Последовательность найдена");
                    } else{
                        table2.setValueAt("Последовательности нет", 0, 0);
                        writefile("Последовательности нет");

                    }
                }
            });

            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            try {
                                if((table.getValueAt(i, j) != null) && (table.getValueAt(i, j).toString() != "")) {
                                    field[i][j] = Integer.parseInt(table.getValueAt(i, j).toString());
                                }
                            } catch (NumberFormatException ex){
                                ex.printStackTrace();
                            }
                        }
                    }
                    boolean answer = LeftDown(field, N , M);

                    if(answer){
                        table2.setValueAt("Последовательность найдена", 0, 0);
                        writefile("Последовательность найдена");
                    } else{
                        table2.setValueAt("Последовательности нет", 0, 0);
                        writefile("Последовательности нет");

                    }
                }
            });

            button5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            try {
                                if((table.getValueAt(i, j) != null) && (table.getValueAt(i, j).toString() != "")) {
                                    field[i][j] = Integer.parseInt(table.getValueAt(i, j).toString());
                                }
                            } catch (NumberFormatException ex){
                                ex.printStackTrace();
                            }
                        }
                    }
                    boolean answer = RightDown(field, N , M);

                    if(answer){
                        table2.setValueAt("Последовательность найдена", 0, 0);
                        writefile("Последовательность найдена");
                    } else{
                        table2.setValueAt("Последовательности нет", 0, 0);
                        writefile("Последовательности нет");
                    }
                }
            });


        }
    }
}