import java.util.*;

/**
 * Creates Letters to be used for WordHunt's grid
 * Check's for validity of grid
 */

public class Board {
    
    private Letter[] board = new Letter[16];
    private final char[] consonant = 
        {'B','C','D','F','G','H','J','K','L','M','N',
        'O','P','Q','R','S','T','V','W','X','Y','Z'};
    private final char[] vowels = {'A','E','I','O','U','Y'};
    private Stack<Letter> stack = new Stack<Letter>();

    /**
     * Constructor of board
     * Creates a random letter for each part of the grid
     */
    public Board()
    {
        board[0] = new Letter(vowels[(int) (Math.random()*6)],0,0);
        board[1] = new Letter(vowels[(int) (Math.random()*6)], 0, 1);
        board[2] = new Letter (consonant[(int)(Math.random()*20)], 0, 2);
        board[3] = new Letter (vowels[(int) (Math.random()*6)], 0, 3);
        // Row 2
        board[4]= new Letter (vowels[(int) (Math.random()*6)], 1, 0);
        board[5] = new Letter (consonant[(int)(Math.random()*20)], 1, 1);
        board[6] = new Letter (consonant[(int)(Math.random()*20)], 1, 2);
        board[7] = new Letter (consonant[(int)(Math.random()*20)], 1, 3);
        // Row 3
        board[8] = new Letter (consonant[(int)(Math.random()*20)], 2, 0);
        board[9] = new Letter (consonant[(int)(Math.random()*20)], 2, 1);
        board[10] = new Letter (consonant[(int)(Math.random()*20)], 2, 2);
        board[11] = new Letter (vowels[(int)(Math.random()*6)], 2, 3);
        // Row 4
        board[12] = new Letter (vowels[(int)(Math.random()*6)],3, 0);
        board[13] = new Letter (consonant[(int)(Math.random()*20)], 3, 1);
        board[14] = new Letter (vowels[(int)(Math.random()*6)],3,2);
        board[15] = new Letter (vowels[(int)(Math.random()*6)],3,3);
    }

    /**
     * Returns the randomized letters
     * @return array of Letter objects
     */
    public Letter[] getBoard()
    {
        return board;
    }

    /**
     * Pushes a letter to stack holding all letters
     * @param letter Letter object to push
     */
    public void addToStack(Letter letter)
    {
        stack.push(letter);
    }

    /**
     * Empties the stack
     */
    public void clearStack()
    {
        stack.clear();
    }

    /**
     * Gets the stack of letters
     * @return the stack
     */
    public Stack<Letter> getStack()
    {
        return stack;
    }

    /**
     * Check if an action is valid on the board
     * Must be diagonal, left, right, up, down
     * @param l new letter
     * @return true if diagonal/left/right/up/down, false if not
     */
    public boolean actionValid(Letter l)
    {
        if (stack.isEmpty())
            return true;
        Letter prev = stack.pop();

        if(prev.equals(l))
            return false;
        if (!(stack.isEmpty()))
        {
        for (Letter a: stack)
        {
            if (prev.getRow() == a.getRow() &&
            prev.getCol() == a.getCol())
                return false;
        }
        }
        if((l.getRow() == prev.getRow()-1) && (l.getCol() == prev.getCol()-1)){
            return true;
        }
        if((l.getRow() == prev.getRow()-1) && (l.getCol() == prev.getCol())){
            return true;
        }
        if((l.getRow() == prev.getRow()-1) && (l.getCol() == prev.getCol()+1)){
            return true;
        }
        if((l.getRow() == prev.getRow()) && (l.getCol() == prev.getCol()-1)){
            return true;
        }
        if((l.getRow() == prev.getRow()) && (l.getCol() == prev.getCol()+1)){
            return true;
        }
        if((l.getRow() == prev.getRow()+1) && (l.getCol() == prev.getCol()-1)){
            return true;
        }
        if((l.getRow() == prev.getRow()+1) && (l.getCol() == prev.getCol())){
            return true;
        }
        if((l.getRow() == prev.getRow()+1) && (l.getCol() == prev.getCol()+1)){
            return true;
        }
        //(r-1, c-1) (r-1, c) (r-1, c + 1)
        //(r, c-1) (r, c + 1)
        //(r+1, c-1) (r+1, c) (r+1, c+1)

    return false;

    }
}