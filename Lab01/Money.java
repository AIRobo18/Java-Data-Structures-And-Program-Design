import java.util.Random;

/**
 * Created by robert.aroutiounian3 on 8/27/15.
 */
public abstract class Money
{
    private int denomination;
    private boolean heads;

    public Money(int newDenomination)
    {
        Random ran = new Random();
        this.denomination = ran.nextInt(newDenomination + 1);
        this.heads = false;
    }

    // gets the denomination of the currency
    // @return the denomination of the currency
    public int getDenomination()
    {
        return this.denomination;
    }

    // gets the value of the denomination and it varies if it is a coin or bill
    public abstract double getValue();

    // flips the currency and sees if it lands heads or tails
    public void toss()
    {
        Random flip = new Random();
        this.heads = flip.nextBoolean();
    }

    // sees whether currency landed on heads or tails
    // @return the boolean to see if it is heads or not
    public boolean isHeads()
    {
        if (this.heads)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // prints out a statement of whether
    public abstract String toString();
}
