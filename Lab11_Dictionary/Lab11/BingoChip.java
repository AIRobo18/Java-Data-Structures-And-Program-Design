/**
 *
 * @author Robert Aroutiounian
 * @version 11/01/2015
 */

public class BingoChip 
{
	private char letter;
	private int number;

	public BingoChip(int number)
	{
		setNumber( number );		
		setLetter();		
	}

	private void setNumber(int number)
	{
		this.number = number;
	}

	private void setLetter()
	{
        String bingo = BingoCard.BINGO_KEYS;
		int index = (number - 1)/BingoCard.MAX_VALUES_PER_LETTER;
		this.letter = bingo.charAt(index);
	}

	public int getNumber()
	{
		return this.number;
	}

	public char getLetter()
	{
		return this.letter;
	}

	public String toString()
	{
		return "---> Calling: " + getLetter() + " " + getNumber();
	}
}