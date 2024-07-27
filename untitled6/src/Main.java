
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.function.Function;

class Tree6Num<Node>
{
    public static void main(String[] args) {
        // Запуск GUI должен производиться через Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new t6(); // Создание и отображение GUI
            }
        });
    }

    private static final Map<Character, Character> addBranch = Map.of(
            '─', '┴',
            '┬', '┼',
            '┌', '├',
            '┐', '┤'
    );
    private final Function<Node, List<Node>> ChildTree;
    private final Function<Node, String> ValueNode;
    public Tree6Num(
            Function<Node, List<Node>> getChildren,
            Function<Node, String> getVal
    ) {
        this.ChildTree = getChildren;
        this.ValueNode = getVal;
    }

    public String onDisplay(Node Tree)
    { return StringTo(Tree); }

    public String StringTo(Node Tree)
    { return StringTo(Tree, 0); }

    public String StringTo(Node Tree, int depth)
    {
        String[][] res = StrTree(Tree, depth);
        var str = new StringBuilder();
        for (var line : res)
        {
            for (var x : line)
                str.append(IsTree(x) ? TextColr(x) : x);
            str.append("\n");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    private String[][] getVal(Node Tree)
    {
        var stVal = this.ValueNode.apply(Tree);

        if (!stVal.contains("\n"))
            return new String[][]{ new String[]{stVal} };

        var lstVal = stVal.split("\n");
        var longest = 0;
        for (var item : lstVal) {
            longest = Math.max(item.length(), longest);
        }
        var res = new String[lstVal.length][];
        for (int i = 0; i < lstVal.length; i++) {
            res[i] = new String[]{lstVal[i] + " ".repeat(longest - lstVal[i].length())};
        }
        return res;
    }

    private LinkedList<Node> DeleteNulls(List<Node> list) {
        var res = new LinkedList<Node>();
        for (var node : list)
        {
            if (node == null)
                continue;
            res.addLast(node);
        }
        return res;
    }

    private String[][] StrTree(Node node, int depth) {
        var val = getVal(node);
        var children = this.ChildTree.apply(node);
        children = DeleteNulls(children);
        if (children.isEmpty())
        {
            String[][] res;
            res = new String[][]{new String[]{"[" + val[0][0] + "]"}};
            return res;
        }
        var PrintToDisplayOnScreen = new ArrayList<ArrayList<String>>();
        PrintToDisplayOnScreen.add(new ArrayList<>());
        var spacing_count = 0;
        var probel = "";

        for (var child : children)
        {
            var childPrint = StrTree(child, depth + 1);
            for (int l = 0; l < childPrint.length; l++)
            {
                var line = childPrint[l];
                if (l + 1 >= PrintToDisplayOnScreen.size())
                    PrintToDisplayOnScreen.add(new ArrayList<>());
                if (l == 0)
                {
                    var lineLen = CountJoin(List.of(line));
                    var middleOfChild = lineLen - (int) Math.ceil(line[line.length - 1].length() / 2d);
                    var toPrint0Len = CountJoin(PrintToDisplayOnScreen.get(0));
                    PrintToDisplayOnScreen.get(0).add(" ".repeat(spacing_count - toPrint0Len + middleOfChild) + "┬");
                }
                var toPrintNxtLen = CountJoin(PrintToDisplayOnScreen.get(l + 1));
                PrintToDisplayOnScreen.get(l + 1).add(" ".repeat(spacing_count - toPrintNxtLen));
                PrintToDisplayOnScreen.get(l + 1).addAll(List.of(line));
            }
            spacing_count = 0;
            for (var item : PrintToDisplayOnScreen)
            {
                var itemLen = CountJoin(item);
                spacing_count = Math.max(itemLen, spacing_count);
            }
            spacing_count++;
        }
        int pipePos;
        if (PrintToDisplayOnScreen.get(0).size() != 1)
        {
            var LinewNewin = String.join("", PrintToDisplayOnScreen.get(0));
            var predSpace = LinewNewin.length() - (LinewNewin = LinewNewin.trim()).length();
            int CountOfTrimmed = LinewNewin.length();
            LinewNewin = " ".repeat(predSpace) +
                    "┌" + LinewNewin.substring(1, LinewNewin.length() - 1).replace(' ', '─') + "┐";
            var middle = LinewNewin.length() - (int) Math.ceil(CountOfTrimmed / 2d);
            pipePos = middle;

            var CharNew = addBranch.get(LinewNewin.charAt(middle));
            LinewNewin = LinewNewin.substring(0, middle) + CharNew + LinewNewin.substring(middle + 1);
            var al = new ArrayList<String>();
            al.add(LinewNewin);
            PrintToDisplayOnScreen.set(0, al);
        } else {
            PrintToDisplayOnScreen.get(0).set(0, PrintToDisplayOnScreen.get(0).get(0).substring(0, PrintToDisplayOnScreen.get(0).get(0).length() - 1) + '│');
            pipePos = PrintToDisplayOnScreen.get(0).get(0).length() - 1;
        }
        if (val[0][0].length() < pipePos * 2)
            probel = " ".repeat(pipePos - (int) Math.ceil(val[0][0].length() / 2d));


        val = new String[][]{new String[]{probel, "[" + val[0][0] + "]"}};


        var HowToArr = new String[val.length + PrintToDisplayOnScreen.size()][];
        int row = 0;
        for (var item : val) {
            HowToArr[row] = new String[item.length];
            System.arraycopy(item, 0, HowToArr[row], 0, item.length);
            row++;
        }
        for (var item : PrintToDisplayOnScreen)
        {
            HowToArr[row] = new String[item.size()];
            int i = 0;
            for (var x : item)
            {
                HowToArr[row][i] = x;
                i++;
            }
            row++;
        }
        return HowToArr;
    }

    private static boolean IsTree(String x)
    {
        if (x == null || x.isEmpty())
            return false;

        char xxChart = x.charAt(0);
        if (xxChart == '[' || xxChart == '|' || (xxChart == '│' && x.trim().length() > 1))
            return true;

        if (x.length() < 2)
            return false;

        var middle = "─".repeat(x.length() - 2);
        return x.equals("┌" + middle + "┐") || x.equals("└" + middle + "┘");
    }

    private String TextColr(String txt)
    {
        var spaces = " ".repeat(txt.length() - (txt = txt.trim()).length());

        txt = " " + txt.substring(1, txt.length() - 1) + " ";
        return spaces + txt;
    }

    private int CountJoin(Collection<String> lst) {
        return String.join("", lst).length();
    }
}
class Solution
{
    //Создание дерева
    public static TreeCreate TreaCreate(String input)
    {
        int inputIndex = input.indexOf(',');
        if (inputIndex == -1)
            return new TreeCreate(input.replace("(", "").replace(")", ""));

        TreeCreate node = new TreeCreate(input.substring(0, inputIndex).trim().replace("(", "").replace(")", ""));//Главный узел
        int Len = 0;
        int StartPosition = inputIndex + 1;
        for (int i = inputIndex + 1; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if (c == '(') Len++;
            else if (c == ')') Len--;

            if ((c == ',' && Len == 0) || i == input.length() - 1)
            {
                node.children.add(TreaCreate(input.substring(StartPosition, i).trim()));
                StartPosition = i + 1;
            }
        }
        return node;
    }

    //Вывод дерева
    public static String PictureTree(TreeCreate node)
    {
        Tree6Num<TreeCreate> cursor = new Tree6Num<>(TreeCreate::GetChild, TreeCreate::ValueGet);
        return cursor.onDisplay(node);
    }
}

class TreeCreate
{
    public String value;
    public ArrayList<TreeCreate> children;

    public TreeCreate(String value)
    {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public ArrayList<TreeCreate> GetChild()
    {
        return children;
    }
    public String ValueGet()
    {
        return value;
    }
}

class t6
{
    JFrame frame = new JFrame();
    JPanel panel = new JPanel(new GridLayout(2,1));
    JPanel panel2 = new JPanel(new GridLayout(1,2));
    JTextArea tb = new JTextArea();
    JTextArea tb2 = new JTextArea();
    JButton button = new JButton("Solution");
    public t6()
    {

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tb2.setText("");
                String treeString = tb.getText();
                TreeCreate tree = Solution.TreaCreate(treeString);
                tb2.setText(Solution.PictureTree(tree));
            }
        });
        tb2.setFont(new Font("Lucida Console", Font.PLAIN, 14));
        panel.add(panel2);
        panel2.add(tb);
        panel2.add(button);
        panel.add(tb2);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize( 1000, 1000);
        frame.setVisible(true);
    }
}
