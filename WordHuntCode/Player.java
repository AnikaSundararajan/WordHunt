import java.util.*;

/**
 * Keeps track of each Player's points and idenitifies them by name.
 */
public class Player 
{
    private ArrayList<String> arrMyWords;
    private int highScore;
    private int currentScore;

    /**
     * Constructor
     */
    public Player()
    {
        highScore = 0;
        currentScore = 0;
        arrMyWords = new ArrayList<String>();
    }

    /**
     * Adds p points to Player's points
     * @param p amount of points to add
     */
    public void addPoints(int points)
    {
        currentScore = currentScore + points;
    }

    /**
     * Returns current amount of points Player has
     * @return int of points
     */
    public int getCurrentScore()
    {
        return currentScore;
    }

    /**
     * Resets the current score
     */
    public void resetCurrentScore()
    {
        currentScore = 0;
    }

    /**
     * Gets highest score
     * @return high score
     */
    public int getHighScore()
    {
        if (highScore < currentScore)
        {
            highScore = currentScore;
            return currentScore;
        }
        else
            return highScore;
    }

    /**
     * Sets valid words to print
     * @param arr of words
     */
    public void setMyWords(ArrayList<String> arr)
    {
        arrMyWords = arr;
    }

    /**
     * Gets valid words
     * @return array
     */
    public ArrayList<String> getMyWords()
    {
        return arrMyWords;
    }
}