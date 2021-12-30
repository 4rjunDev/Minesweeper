import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
public class Cell extends JButton
{
    private int value;
    private int row;
    private int column;
    boolean flagged;
    boolean question;
    int num;

    public Cell()
    {
        row = 9;
        column = 9;
        value = 0;
    }
    
    public Cell(int r, int c)
    {
        row = r;
        column = c;
        value = 0;
        flagged = false;
        question = false;
    }

    public void setValue(int v)
    {
        this.value = v;
    }
    
    public void setFlag(boolean f)
    {
        this.flagged = f;
    }
    
    public void setQuestion(boolean f)
    {
        this.question = f;
    }
    
    public int getValue()
    {
        return this.value;
    }
    
    public int getRow()
    {
        return this.row;
    }
    
    public int getColumn()
    {
        return this.column;
    }
    
    public boolean getFlag()
    {
        return this.flagged;
    }
    
    public boolean getQuestion()
    {
        return this.question;
    }
    
    public static void main (String[] args)
    {
    }
}
