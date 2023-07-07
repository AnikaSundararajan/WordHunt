import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Opening page of the game.
 * Instructions on how to play and start game button.
 */
public class StartPage implements ActionListener
{
    private static Player myPlayer = new Player();
    private JFrame frame = new JFrame();
    private ImageIcon bgImage;
    private JLabel myLabel;
    private Color myColor = new Color(5, 68, 71);
    private JButton myButton = new JButton("start game");
    private Font myFont = new Font("Gilroy", Font.BOLD, 36);
    private WordHunt wH;
    
    /**
     * Constructor
     * Creates the GUI of instructions and button
     * 
     * @param p the Player playing
     */
    public StartPage(Player p)
    {
        bgImage = new ImageIcon(this.getClass().getResource("/startPageImage.png"));
        myLabel = new JLabel(bgImage);
        myLabel.setSize(560, 730);

        myButton.setBounds(140, 475, 300, 100);
        
        myButton.setFont(myFont);
        myButton.setBackground(Color.WHITE);
        myButton.setForeground(myColor);
        myButton.setFocusable(false);
        myButton.addActionListener(this);

        frame.add(myButton);
        frame.add(myLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(560, 730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    /**
     * Gives instructions on what to do when button clicked
     * Times the game for 60 seconds
     * Then moves onto the screen with list of words
     * 
     * @param e event caused by the button clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myButton)
        {
            frame.dispose();
            wH = new WordHunt(myPlayer);

            Timer gamet = new Timer();

            TimerTask task = new TimerTask() 
            {
                int counter = 60;
                @Override
                public void run() 
                {
                    if (counter > 0)
                    {
                        counter--;
                        wH.timerField.setText("" + counter);
                    }
                    else 
                    {
                        myPlayer.setMyWords(wH.usedWords);
                        // System.out.println(wH.player.getMyWords());
                        // System.out.println(wH.player.getPoints());
                        wH.frame.dispose();
                        WordList wList = new WordList(myPlayer);
                        gamet.cancel();
                    }
                }
            };

            gamet.scheduleAtFixedRate(task, 10, 1000);
        }
        
    }

    /**
     * Main method
     * Runs the class
     */
    public static void main (String args[])
    {
        StartPage sp = new StartPage(myPlayer);
    }
}
