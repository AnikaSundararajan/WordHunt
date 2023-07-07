import java.util.*;

/**
 * Checks the word to confirm it's presence in 
 * the english dictionary and get the accurate
 * amount of points the player who found it 
 * deserves.
 */
public class Word 
{
    private String word;
    private ArrayList<String> allWordsFile;
    private ArrayList<String> usedWords;

    /**
     * Constructor
     * @param w given word
     */
    public Word(String w, ArrayList<String> wordFile, ArrayList<String> used)
    {
        word = w.toLowerCase();
        allWordsFile = wordFile;
        usedWords = used;
    }

    /**
     * Adds the word to the list of used words
     */
    public void addToList()
    {
        usedWords.add(word);
    }

    /**
     * Checks that the word is in the English dictionary
     * @return if valid, true. if invalid, false.
     */
    public boolean isEnglishWord()
    {
        if (word.length() > 2)
        {
            if (allWordsFile.contains(word))
                return true;
        }
        return false;
    }

    /**
     * Checks that the word has not been used already
     * @return if valid, true. if invalid, false.
     */
    public boolean notBeenUsed()
    {
        for (String used : usedWords)
        {
            if (word.compareToIgnoreCase(used) == 0)
                return false;
        }
        return true;
    }

    /**
     * Calculates and adds the accurate amount
     * of points to Player p 
     * @param p Player to add the points to
     * @return amount of points added, if not english word: -1, if word already used: 0
     */
    public int addPointsTo(Player p)
    {
        int points = 0;
        if (isEnglishWord())
        {
            if (notBeenUsed())
            {
                points = word.length() * 100;
                p.addPoints(points);
                addToList();
                return points;
            }
            return 0;
        }
        return -1;
    }

    /**
     * Gets the word
     * @return String of the word
     */
    public String getWord()
    {
        return word;
    }

}
