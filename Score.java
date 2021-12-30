import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Random;
import javax.swing.ImageIcon;
import java.io.*;
import java.util.*;

public class Score extends JDialog
{
    JPanel panel = new JPanel();
    JLabel scoreLabel = new JLabel();
    int[] highScores = new int[10];
    String[] listItems = {"0","0","0","0","0","0","0","0","0","0"};
    JList<String> scoreList = new JList<String>(listItems);
    String[] names = new String[10];
    String[][] tableData = {{"0", ""},{"0", ""},{"0", ""},{"0", ""},{"0", ""},{"0", ""},{"0", ""},{"0", ""},{"0", ""},{"0", ""}};
    String[] columnNames = {"Score", "Name"};
    JTable scoreTable = new JTable(tableData, columnNames);

    public Score(int score, Frame owner)
    {
        // Set up the frame
        super(owner, "HighScores:", true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setBounds(500, 300, 750, 500);

        scoreList.setFixedCellWidth(100);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer)scoreList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        panel.add(scoreLabel);
        panel.add(scoreList);

        //set table col and width
        // DefaultTableCellRenderer centerRenderer=new DefaultTableCellRenderer();
        // centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        // scoreTable.setDefaultRenderer(Object.class, centerRenderer);
        // scoreTable.getColumnModel().getColumn(1).setPreferredWidth(150);

        //show the contents
        this.setContentPane(panel);
        loadScores();
        addScore(score, null);
        this.setVisible (true);
    }

    // public Score(int score)
    // {
    // this();
    // addScore(score, null);
    // }

    public void addScore(int newScore, String name)
    {
        scoreLabel.setText("You Won! It took you " + newScore + " seconds!");
        // addScore(newScore);
        loadScores();

        for (int i=0; i<10; i++)
        {
            if (newScore > highScores[i])
            {
                for (int j=9; j>i; j--)
                {
                    highScores[j] = highScores[j-1];
                    names[j] = names[j-1];
                }
                highScores[i] = newScore;
                // add dialog to ask for name
                name = JOptionPane.showInputDialog (null, "High Score! Please enter your name", "High Score!", JOptionPane.INFORMATION_MESSAGE);
                names[i] = name;
                break;
            }
        }

        for (int i=0; i<10; i++)
        {
            // listItems[i] = Integer.toString(highScores[i]);
            listItems[i]=highScores[i]+" "+names[i];
            // tableData[i][0]=Integer.toString(highScores[i]);
            // tableData[i][1]=names[i];
        }
        saveScores(listItems);
        repaint();
    }

    public void saveScores(String[] scores)
    {
        {
            try {
                PrintWriter out= new PrintWriter("test.txt");
                for (int i=0; i<scores.length; i++) {
                    out.println(scores[i]);
                }
                
                // for element in high score list, save all scores to file
                // for (int i=0; i<num; i++)
                // {
                // x = Math.random()*100;
                // out.println(x);
                // }
                out.close();
            } catch (IOException e) {
                System.out.println("Cannot open file.");
            }
        }
    }

    public void loadScores()
    {
        int num=0;
        double x,total=0;
        try {
            BufferedReader in = new BufferedReader(new FileReader("test.txt"));
            String line; // Read a line
            // Scanner in2 = new Scanner(new FileReader("test.txt"));
            // num = in2.nextInt();
            // num = Integer.parseInt(line); // Convert it to integer
            for (int i=0; i<10; i++)
            {
                line = in.readLine(); // Read a line
                listItems[i] = line;
                System.out.println("line: " + line);
                line = line.substring(0, 2);
                highScores[i] = Integer.parseInt(line.trim());
                // listItems[i] = line;
                // x = Double.parseDouble(line); // Convert it to double
                // total += x;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Cannot open file.");
        }
        repaint();
    }
}