import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Gameboard used to guess words
 * Gameboard made with buttons of letters
 * Point value and accuracy of word displayed 
 */
public class WordHunt implements ActionListener{
    
    /**
     * Player implemented in WordHunt
     */
    public Player wHPlayer;
    /**
     * Array of all the words used by the player
     * To avoid repeats of words
     */
    public ArrayList<String> usedWords = new ArrayList<String>();
    
    private Board myBoard = new Board();
    private Letter[] letters = myBoard.getBoard();
    private Word guess;
    private ArrayList<String> allWordsFile = new ArrayList<String>();
    
    {
        try 
        {
            Scanner s = new Scanner(new File("allWords.txt"));
            while (s.hasNext())
            {
                allWordsFile.add(s.next().toLowerCase());
            }
            s.close();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Frame in which game is played
     */
    public JFrame frame;
    /**
     * Displays countdown timer on screen
     */
    public JTextField timerField;
    
    private JButton[] letterButtons = new JButton[16];
    private JButton[] functionButtons = new JButton[2];
    private JButton letter1, letter2, letter3, letter4;
    private JButton letter5, letter6, letter7, letter8;
    private JButton letter9, letter10, letter11, letter12;
    private JButton letter13, letter14, letter15, letter16;
    private JButton resetButton, enterButton;
    private JTextField textField, pointsField;
    
    private JPanel panel;
    private Font myFont = new Font("Gilroy", Font.BOLD, 30);
    private Color myColor = new Color(5, 68, 71);

    /**
     * Constructor
     * Creates GUI for game board's layout and buttons
     * Creates GUI for word accuracy and displaying points
     * @param player
     */
    public WordHunt(Player player) 
    {
        wHPlayer = player;
        frame = new JFrame("Word Hunt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setBackground(myColor);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);

        timerField = new JTextField();
        timerField.setBounds(300, 10, 50, 25);
        timerField.setFont(new Font("Gilroy", Font.PLAIN, 15));
        timerField.setBackground(myColor);
        timerField.setHorizontalAlignment(JLabel.CENTER);
        timerField.setForeground(Color.WHITE);
        timerField.setEditable(false);

        pointsField = new JTextField();
        pointsField.setBounds(50, 75, 300, 50);
        pointsField.setFont(myFont);
        pointsField.setHorizontalAlignment(JLabel.CENTER);
        pointsField.setBackground(myColor);
        pointsField.setForeground(Color.WHITE);
        pointsField.setEditable(false);

        letter1 = new JButton("" + letters[0].getLetter());
        letter2 = new JButton("" + letters[1].getLetter());
        letter3 = new JButton("" + letters[2].getLetter());
        letter4 = new JButton("" + letters[3].getLetter());
        letter5 = new JButton("" + letters[4].getLetter());
        letter6 = new JButton("" + letters[5].getLetter());
        letter7 = new JButton("" + letters[6].getLetter());
        letter8 = new JButton("" + letters[7].getLetter());
        letter9 = new JButton("" + letters[8].getLetter());
        letter10 = new JButton("" + letters[9].getLetter());
        letter11 = new JButton("" + letters[10].getLetter());
        letter12 = new JButton("" + letters[11].getLetter());
        letter13 = new JButton("" + letters[12].getLetter());
        letter14 = new JButton("" + letters[13].getLetter());
        letter15 = new JButton("" + letters[14].getLetter());
        letter16 = new JButton("" + letters[15].getLetter());

        letterButtons[0] = letter1;
        letterButtons[1] = letter2;
        letterButtons[2] = letter3;
        letterButtons[3] = letter4;
        letterButtons[4] = letter5;
        letterButtons[5] = letter6;
        letterButtons[6] = letter7;
        letterButtons[7] = letter8;
        letterButtons[8] = letter9;
        letterButtons[9] = letter10;
        letterButtons[10] = letter11;
        letterButtons[11] = letter12;
        letterButtons[12] = letter13;
        letterButtons[13] = letter14;
        letterButtons[14] = letter15;
        letterButtons[15] = letter16;

        for(int i = 0; i < 16; i++)
        {
            letterButtons[i].addActionListener(this);
            letterButtons[i].setFont(myFont);
            letterButtons[i].setFocusable(false);
        }

        resetButton = new JButton("RESET");
        enterButton = new JButton("ENTER");
        functionButtons[0] = resetButton;
        functionButtons[1] = enterButton;

        for(int i = 0; i < 2; i++)
        {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setOpaque(true);
            functionButtons[i].setBackground(myColor);
            functionButtons[i].setForeground(myColor);
            functionButtons[i].setFocusable(false);
        }

        resetButton.setBounds(50, 450, 145, 50);
        enterButton.setBounds(205, 450, 145, 50);

        panel = new JPanel();
        panel.setBounds(50, 125, 300, 300);
        panel.setLayout(new GridLayout(4, 4));
        panel.setBackground(myColor);
        
        for (int i = 0; i < 16; i++)
        {
            panel.add(letterButtons[i]);
        }
        
        frame.add(panel);
        frame.add(resetButton);
        frame.add(enterButton);
        frame.add(pointsField);
        frame.add(textField);
        frame.add(timerField);
        frame.setVisible(true);
    }

    /**
     * Determines whether word is accurate or not
     * Changes color of button based on being clicked
     * Changes text in textfields
     * 
     * @param e the event to be checked
     */
    @Override
    public void actionPerformed (ActionEvent e)
    {
        for (int i = 0; i < 16; i++)
        {
            if (e.getSource() == letterButtons[i])
            {
                if (myBoard.getStack().contains(letters[i]))
                {
                    // System.out.println("contains");
                    pointsField.setText("INVALID ACTION");
                    textField.setText("");
                    myBoard.clearStack();
                    for (int k = 0; k < 16; k++)
                        letterButtons[k].setForeground(myColor);
                }
                else if (myBoard.actionValid(letters[i]))
                {
                    pointsField.setText("");
                    textField.setText(textField.getText().concat(letterButtons[i].getText()));
                    myBoard.addToStack(letters[i]);
                    letterButtons[i].setForeground(Color.LIGHT_GRAY);
                }
                else 
                {
                    pointsField.setText("INVALID ACTION");
                    textField.setText("");
                    myBoard.clearStack();
                    for (int k = 0; k < 16; k++)
                        letterButtons[k].setForeground(myColor);
                }
            }
        }

        if (e.getSource() == resetButton)
        {
            textField.setText("");
            myBoard.clearStack();
            for (int i = 0; i < 16; i++)
                letterButtons[i].setForeground(myColor);
        }

        if (e.getSource() == enterButton)
        {
            guess = new Word(textField.getText(), allWordsFile, usedWords);
            textField.setText("");
            myBoard.clearStack();
            for (int i = 0; i < 16; i++)
                letterButtons[i].setForeground(myColor);

            int numPoints = guess.addPointsTo(wHPlayer);
            if (numPoints > 0)
                pointsField.setText("+" + numPoints);
            else if (numPoints == 0)
                pointsField.setText("ALREADY USED");
            else 
                pointsField.setText("NOT A WORD");
                
        }
    }

}
