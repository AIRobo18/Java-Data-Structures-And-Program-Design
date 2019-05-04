/**
 *
 * @author Robert Aroutiounian
 * @version 11/01/2015
 */
 
import java.util.*;
public class BingoCard 
{
	private TreeSet<Character> bingoChars;
	private HashMap<Character, TreeSet<Integer>> card;
    public final static String BINGO_KEYS = "BINGO";
    public final static int MAX_VALUES_PER_LETTER = 15;
    public final static int NUMBERS_PER_LETTER = 5;

	public BingoCard()
	{
		this.bingoChars = new TreeSet<>();
		this.card = new HashMap<>();

		Random ran = new Random();
		int num;
		for (int i = 0; i < this.BINGO_KEYS.length(); i++)
		{
            Character bingoChar = this.BINGO_KEYS.charAt(i);
			TreeSet<Integer> intSet = new TreeSet<>();
			while (intSet.size() < this.NUMBERS_PER_LETTER)
			{
				num = (ran.nextInt(this.MAX_VALUES_PER_LETTER) + 1) + (this.MAX_VALUES_PER_LETTER * i);
				intSet.add(num);
			}
			this.card.put(bingoChar, intSet);
		}

		this.printCard();
	}

	public void checkNumber(BingoChip chip)
	{
        char chipChar = chip.getLetter();
        int chipInt = chip.getNumber();

        TreeSet<Integer> intSet = this.card.get(chipChar);
        if (intSet.contains(chipInt))
        {
            intSet.add(0);
            this.bingoChars.add(chipChar);
            this.card.put(chipChar, intSet);
        }
	}
	
	public boolean isWinner()
	{
//        boolean winner = true;
//        TreeSet<Integer> intSet;
//        char letter;
//
//        for (int i = 0; i < this.BINGO_KEYS.length(); i++)
//        {
//            letter = this.BINGO_KEYS.charAt(i);
//            intSet = this.card.get(letter);
//            if (!intSet.contains(0))
//            {
//                System.out.println();
//                winner = false;
//                break;
//            }
//        }

        boolean winner = false;

        if (this.BINGO_KEYS.length() - this.bingoChars.size() == 0)
        {
            if (this.bingoChars.first() == this.BINGO_KEYS.charAt(0) &&
                    this.bingoChars.last() == this.BINGO_KEYS.charAt(this.BINGO_KEYS.length() - 1))
            {
                winner = true;
            }
        }
		return winner;
	}
	
	public void printCard()
	{
        char letter;
        for (int i = 0; i < this.BINGO_KEYS.length(); i++)
        {
            letter = this.BINGO_KEYS.charAt(i);
            System.out.print(letter + " ");

            TreeSet< Integer> integerSet = this.card.get(letter);
            for (Integer integers : integerSet)
            {
                System.out.print(integers + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}