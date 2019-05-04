/**
 * Created by robert.aroutiounian3 on 8/27/15.
 */
public class Coin extends Money
{
    private final int[] DENOMINATION_VALUE = {1, 5, 10, 25, 50};
    private final String[] DENOMINATION_NAME = {"PENNY", "NICKEL", "DIME", "QUARTER", "HALF_DOLLAR"};
    private static final int NUMBER_OF_DENOMINATIONS = 5;

    public Coin()
    {
        super(NUMBER_OF_DENOMINATIONS);
    }

    // returns the value of the corresponding denomination
    public double getValue()
    {
        int denomination = getDenomination();

        double value = (((double)(this.DENOMINATION_VALUE[denomination]))/100);

        return value;
    }

    // prints out the name of the corresponding denomination and if it landed heads or  tails
    public String toString()
    {
        toss();

        int denomination = getDenomination();

        String name = this.DENOMINATION_NAME[denomination];

        if (isHeads())
        {
            return name + " landed HEADS";
        }
        else
        {
            return name + " landed TAILS";
        }
    }
}
