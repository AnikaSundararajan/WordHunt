import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 * Final page of game
 * Displays list of words guessed by user.
 * Displays highest and current scores.
 * Allows user to play again.
 */
public class WordList implements ActionListener 
{
    private final int WIDTH = 300;
    private final int LEFT = 75;
    
    private Player wLPlayer;
    private JFrame frame = new JFrame();
    private ImageIcon bgImage;
    private JLabel myLabel;
    private Font buttonFont = new Font("Gilroy", Font.BOLD, 36);
    private Font textFont = new Font("Gilroy", Font.BOLD, 20);
    private Color myColor = new Color(5, 68, 71);
    private JButton newGameButton = new JButton("play again");
    private JTextField highestScore = new JTextField("");
    private JTextField currentScore = new JTextField("");

    private JTextArea words = new JTextArea("");
    private JTextArea wordScores = new JTextArea("");
    private String wordsText = "";
    private String wordScoresText = "";

    /**
     * Constructor
     * Creates GUI for printing list of words
     * Creates GUI for displaying final scores
     * Creates GUI for the 'play again' button
     * 
     * @param p the Player to access for point values
     */
    public WordList(Player p)
    {
        wLPlayer = p;
        bgImage = new ImageIcon(this.getClass().getResource("/wordListImage.png"));
        myLabel = new JLabel(bgImage);
        myLabel.setSize(456, 596);

        // To play a new game
        newGameButton.setBounds(LEFT, 200, WIDTH, 100);
        newGameButton.setFont(buttonFont);
        newGameButton.setBackground(Color.WHITE);
        newGameButton.setForeground(myColor);
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(this);

        // Displays highest score and current game score
        highestScore.setBounds(LEFT, 50, WIDTH, 50);
        highestScore.setHorizontalAlignment(JLabel.CENTER);
        highestScore.setFont(textFont);
        highestScore.setBackground(myColor);
        highestScore.setForeground(Color.WHITE);
        highestScore.setEditable(false);
        highestScore.setText("HIGHEST SCORE: " + p.getHighScore());

        currentScore.setBounds(LEFT, 100, WIDTH, 50);
        currentScore.setHorizontalAlignment(JLabel.CENTER);
        currentScore.setFont(textFont);
        currentScore.setBackground(myColor);
        currentScore.setForeground(Color.WHITE);
        currentScore.setEditable(false);
        currentScore.setText("CURRENT SCORE: " + p.getCurrentScore());
        
        // Displays list of words and amount of points per word figured out
        words.setBounds(100, 300, 150, 215);
        words.setFont(textFont);
        words.setBackground(myColor);
        words.setForeground(Color.WHITE);
        words.setEditable(false);
        
        wordScores.setBounds(200, 300, 150, 215);
        wordScores.setFont(textFont);
        wordScores.setBackground(myColor);
        wordScores.setForeground(Color.WHITE);
        wordScores.setEditable(false);

        for (int i = 0; i < p.getMyWords().size(); i++)
        {
            wordsText += "    " + p.getMyWords().get(i).toUpperCase() + "\n";
            wordScoresText += "           " + (p.getMyWords().get(i).length() * 100) + "\n";

            if (i == 7)
            {
                wordsText += "  +" + (p.getMyWords().size() - 8) + " WORDS";
                i = p.getMyWords().size();
            }
        }
        words.setText(wordsText);
        wordScores.setText(wordScoresText);
        
        // Misc adding to frame commands
        frame.add(newGameButton);
        frame.add(highestScore);
        frame.add(currentScore);
        frame.add(words);
        frame.add(wordScores);
        
        frame.add(myLabel);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(456, 596);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    /**
     * Switches back to the starting page when button clicked
     * 
     * @param e event caused by the button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton)
        {
            frame.dispose();
            wLPlayer.resetCurrentScore();
            StartPage sPage = new StartPage(wLPlayer);
        }
        
    }
}

