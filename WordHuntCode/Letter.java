/**
 * Letter and position of each button of the board
 */


public class Letter {
	private char letterChar;
	private int row;
	private int col;
	
	/**
	 * Letter constructor
	 * @param l letter of alphabet
	 * @param r row on board
	 * @param c column on board
	 */
	public Letter(char l, int r, int c)
	{
		row = r;
		col = c;
		letterChar = l;
	}

	/**
	 * Set letter to given letter on alphabet
	 * @param c
	 */
	public void setLetter(char c){
		letterChar = c;
	}

	/**
	 * Set position of letter
	 * @param r row
	 * @param c column
	 */
	public void setPos(int r, int c){
		row = r;
		col = c;
	}

	/**
	 * Get letter of alphabet
	 * @return letter of alphabet
	 */
	public char getLetter() {
		return letterChar;
	}
	
	/**
	 * Get row of letter
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Get column of letter
	 * @return column
	 */
	public int getCol() {
		return col;
	}
}