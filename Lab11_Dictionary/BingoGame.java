/**
 *
 * @author Robert Aroutiounian
 * @version 11/01/2015
 */
 
import java.util.*;
import java.util.function.BooleanSupplier;

public class BingoGame 
{
	private final int NUM_OF_CHIPS = 75;
	private int numOfPlayers;
	private ArrayList<BingoChip> bingoDrum;
	private BingoCard[] playersCards;

	public BingoGame(int numOfPlayers) 
	{
        setNumOfPlayers(numOfPlayers);
        createBingoDrum();
        createPlayersCards();
	}
	
	private void createBingoDrum()
	{
		this.bingoDrum = new ArrayList<>();
		for (int i = 1; i < this.NUM_OF_CHIPS; i ++)
		{
			BingoChip chip = new BingoChip(i);
			this.bingoDrum.add(chip);
		}
	}
	
	private void createPlayersCards()
	{
		this.playersCards = new BingoCard[this.numOfPlayers];
		for (int i = 0; i < this.numOfPlayers; i++)
		{
            System.out.println("---> Creating bingo card for Player " + (i + 1));

            BingoCard card = new BingoCard();
			this.playersCards[i] = card;
		}
	}
	
	private void setNumOfPlayers(int numOfPlayers)
	{
		this.numOfPlayers = numOfPlayers;
	}

	public int getNumberOfChips()
	{
		return (this.NUM_OF_CHIPS - this.bingoDrum.size());
	}

	public BingoChip pullChip()
	{
		Random ran = new Random();

		int num = ran.nextInt(this.bingoDrum.size());

		BingoChip chip;
        chip = this.bingoDrum.remove(num);

        System.out.println(chip.toString());
        return chip;
	}
	
	public void play()
	{
		Boolean winner = false;

		while (!winner)
		{
            BingoChip pulledChip = pullChip();
			for (int i = 0; i < this.numOfPlayers && !winner; i++)
			{
				BingoCard card = this.playersCards[i];
				card.checkNumber(pulledChip);
                System.out.println("Player's " + (i + 1) + " card:");
                card.printCard();
                if (card.isWinner())
				{
                    System.out.println("!!! Player " + (i + 1) + " says BINGO !!!");
					winner = true;
				}
			}
		}
		System.out.println(getNumberOfChips() + " chips were called.");
	}
}