import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Random;
import javax.swing.ImageIcon;
public class MinesweeperArray extends JFrame implements MouseListener
{
    int clicked = 0;
    private final int SIZE = 9;
    int flagsRemaining = SIZE*SIZE/6;
    int BOMBS = SIZE*SIZE/6;
    int time = 0;
    int rotateFlag = 0;
    boolean isBomb;

    JLabel Time;
    JLabel Remaining;
    JButton Restart;
    private Cell[][] grid = new Cell [SIZE][SIZE];
    GridBagConstraints c = new GridBagConstraints ();

    private Timer gameTimer;

    public MinesweeperArray()
    {
        // Set up the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500, 300, 750, 500);

        Time = new JLabel ("Time: " + time + "    ");
        Time.setFont(new Font("Comic Sans", Font.PLAIN, 18));
        Remaining = new JLabel ("    Flags Left: " + flagsRemaining);
        Remaining.setFont(new Font("Comic Sans", Font.PLAIN, 18));
        Restart = new JButton();
        Restart.setIcon(new ImageIcon("Smileyface.png"));
        Restart.setPreferredSize(new Dimension(50, 50));
        Restart.addMouseListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JPanel north = new JPanel (new GridBagLayout());
        north.setMinimumSize(new Dimension(300, 50));
        north.setMaximumSize(new Dimension(300, 50));
        north.setPreferredSize(new Dimension(300, 50));
        north.add(Time);
        north.add(Restart);
        north.add(Remaining);
        panel.add(north);

        JPanel cells = new JPanel();
        cells.setLayout(new GridLayout(SIZE, SIZE));
        panel.add(cells);

        gameTimer = new Timer(1000, new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    Time.setText(String.valueOf("Time: " + time++ + "    "));
                }
            });

        for (int r = 0 ; r < SIZE ; r++)
        {
            for (int c = 0 ; c < SIZE ; c++)
            {
                grid [r][c] = new Cell (r, c);
                grid [r][c].addMouseListener(this);
                cells.add(grid [r][c]);
            }
        }

        //show the contents
        this.setContentPane(panel);
        this.setVisible (true);

        newGame();
    }

    public int count(int row, int column)
    {
        int count = 0;
        // check all cells around
        try {
            if (grid[row+1][column].getValue() == 1) 
            {
                // check cell below
                count++;
            }
        } 
        catch (Exception e) 
        {
            //Do nothing
        }
        try {
            if (grid[row-1][column].getValue() == 1) 
            {
                // check cell above
                count++;
            }
        } 
        catch (Exception e) 
        {

        }
        try {
            if (grid[row][column+1].getValue() == 1) 
            {
                //check cell to the right
                count++;
            }
        } 
        catch (Exception e) 
        {

        }
        try {
            if (grid[row][column-1].getValue() == 1) 
            {
                //check cell to the left
                count++;
            }
        } 
        catch (Exception e) 
        {

        }
        try {
            if (grid[row+1][column+1].getValue() == 1) 
            {
                // check right bottom diagonal
                count++;
            }
        } 
        catch (Exception e) 
        {

        }
        try {
            if (grid[row-1][column+1].getValue() == 1) 
            {
                // check top right diagonal
                count++;
            }
        } 
        catch (Exception e) 
        {

        }
        try {
            if (grid[row+1][column-1].getValue() == 1) 
            {
                // check bottom left diagonal
                count++;
            }
        } 
        catch (Exception e) 
        {

        }
        try {
            if (grid[row-1][column-1].getValue() == 1) 
            {
                // check top left diagonal
                count++;
            }
        } 
        catch (Exception e) 
        {

        }
        return count;
    }

    public boolean reveal(int row, int column)
    {
        int count = count(row, column);
        clicked++;
        if (grid[row][column].getValue() == 1) 
        {
            //grid[row][column].setText("(" + grid[row][column].getRow() + "," + grid[row][column].getColumn() + ")");
            gameTimer.stop();
            grid[row][column].setIcon(new ImageIcon("bomb.png"));
            // Options dialog with buttons:
            int choice;
            Object[] options = {"OK", "Restart"};
            choice = JOptionPane.showOptionDialog (null, "Game Over", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options [0]);

            if (choice == 0)
            {
                this.dispose();
            }
            else
            {
                Restart();
            }
            return true;
        } 

        else if (count == 0) 
        {
            //reveal surrounding cells
            grid[row][column].setEnabled(false);
            try {
                if (grid[row+1][column].isEnabled() && !grid[row+1][column].getFlag() && !grid[row+1][column].getQuestion()) {
                    reveal(row+1, column);
                }
                
            } catch (Exception e)
            {
            }

            try {
                if (grid[row-1][column].isEnabled() && !grid[row-1][column].getFlag() && !grid[row-1][column].getQuestion()) {
                    reveal(row-1, column);
                }
            } catch (Exception e)
            {
            }

            try {
                if (grid[row][column+1].isEnabled() && !grid[row][column+1].getFlag() && !grid[row][column+1].getQuestion()) {
                    reveal(row, column+1);
                }
            } catch (Exception e)
            {
            }

            try {
                if (grid[row][column-1].isEnabled() && !grid[row][column-1].getFlag() && !grid[row][column-1].getQuestion()) {
                    reveal(row, column-1);
                }
            } catch (Exception e)
            {
            }

            try {
                if (grid[row+1][column+1].isEnabled() && !grid[row+1][column+1].getFlag() && !grid[row+1][column+1].getQuestion()) {
                    reveal(row+1, column+1);
                }
            } catch (Exception e)
            {
            }

            try {
                if (grid[row+1][column-1].isEnabled() && !grid[row+1][column-1].getFlag() && !grid[row+1][column-1].getQuestion()) {
                    reveal(row+1, column-1);
                }
            } catch (Exception e)
            {
            }

            try {
                if (grid[row-1][column+1].isEnabled() && !grid[row-1][column+1].getFlag() && !grid[row-1][column+1].getQuestion()) {
                    reveal(row-1, column+1);
                }
            } catch (Exception e)
            {
            }

            try {
                if (grid[row-1][column-1].isEnabled() && !grid[row-1][column-1].getFlag() && !grid[row-1][column-1].getQuestion()) {
                    reveal(row-1, column-1);
                }
            } catch (Exception e)
            {
            }

            //reveal(row-1, column);
            //reveal(row, column+1);
            //reveal(row, column-1);
        }
        grid[row][column].setText(String.valueOf(count));
        grid[row][column].setEnabled(false);
        System.out.println("reveal");
        return false;
    }

    public void mousePressed (MouseEvent e)
    {
        gameTimer.start();
        if (e.getSource() == Restart) {
            Restart();
            return;
        }

        Cell c = (Cell) (e.getSource());
        System.out.println("    flagsRemaining " + flagsRemaining);

        if (e.getButton() == 1)
        {
            if (c.isEnabled() && !c.getQuestion() && !c.getFlag())
            {
                // Left mouse button pressed - call reveal() method
                isBomb = reveal(c.getRow(), c.getColumn());
                if (isBomb) {
                    return;
                }
                System.out.println("mouseClicked: " + clicked);
                c.setEnabled(false);
                if (clicked >= (SIZE*SIZE - BOMBS))
                {
                    gameTimer.stop();
                    gameWon();
                }
            }
        } 
        else if (e.getButton() == 3)
        {
            //Right mouse button - cycle the button's text between "" (blank), "B" (bomb) and "?" (flag)
            if (c.isEnabled())
            {
                if (c.getQuestion())
                {
                    c.setIcon(null);
                    c.setQuestion(!c.getQuestion());
                    rotateFlag = 0;
                }
                else if (!c.getFlag()) 
                {
                    // if flag not set, add flag
                    c.setIcon(new ImageIcon("flag.png"));
                    c.setFlag(!c.getFlag());
                    flagsRemaining--;
                    Remaining.setText("Flags Left: " + flagsRemaining);
                } 
                else if (!c.getQuestion())
                {
                    c.setIcon(new ImageIcon("questionmark.png"));
                    c.setQuestion(!c.getQuestion());
                    c.setFlag(!c.getFlag());
                    flagsRemaining++;
                    Remaining.setText("Flags Left: " + flagsRemaining);
                }
            }
        }
    }

    public void mouseReleased (MouseEvent e) 
    {
    }

    public void mouseClicked (MouseEvent e) 
    {
    }

    public void mouseEntered (MouseEvent e) 
    {
    }

    public void mouseExited (MouseEvent e) 
    {
    }

    public void newGame()
    {
        for (int r = 0 ; r < SIZE ; r++)
        {
            for (int c = 0 ; c < SIZE ; c++)
            {
                grid[r][c].setText("");
                grid[r][c].setIcon(null);
                grid[r][c].setValue(0);
                grid[r][c].setEnabled(true);
                grid[r][c].setFlag(false);
                grid[r][c].setQuestion(false);
            }
        }

        // generate random r,c pairs
        // number of bombs SIZE*SIZE/6
        Random random = new Random(); 
        for(int i = 0; i < SIZE*SIZE/6; i++){
            int randomRow = random.nextInt(SIZE);
            int randomCol = random.nextInt(SIZE);
            grid[randomRow][randomCol].setValue(1);
        }
    }

    public void Restart()
    {
        newGame();
        gameTimer.stop();
        clicked = 0;
        time = 0;
        Time.setText(String.valueOf("Time: " + time + "    "));
        flagsRemaining = SIZE*SIZE/6;
        Remaining.setText("Flags Left: " + flagsRemaining);
        return;
    }

    public void gameWon()
    {
        int choice;
        Time.setText(String.valueOf("Time: " + time + "    "));
        Object[] options = {"OK", "Restart"};
        choice = JOptionPane.showOptionDialog (null, "You Won! It took you " + time + " seconds!", "Winner!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options [0]);
        if (choice == 0)
        {
            this.dispose();
        }   

        else
        {
            Restart();
        }
    }

    public static void main (String[] args)
    {
        new MinesweeperArray();
    }
}
