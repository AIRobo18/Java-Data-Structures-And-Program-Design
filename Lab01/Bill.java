import sun.jvm.hotspot.debugger.posix.elf.ELFException;

/**
 * Created by robert.aroutiounian3 on 8/27/15.
 */
public class Bill extends Money
{
    private static final int[] DENOMINATION_VALUE = {1, 2, 5, 10, 20, 50, 100};
    private static final String[] DENOMINATION_NAME = {"WASHINGTON", "JEFFERSON", "LINCOLN", "HAMILTON", "JACKSON",
                                                        "GRANT", "FRANKLIN"};
    private static final int NUMBER_OF_DENOMINATIONS = 7;

    public Bill()
    {
        super(NUMBER_OF_DENOMINATIONS);
    }

    // returns the value of the corresponding denomination
    public double getValue()
    {
        int denomination = getDenomination();

        double value = (double)(this.DENOMINATION_VALUE[denomination]);

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
            return name + "landed HEADS";
        }
        else
        {
            return name + " landed TAILS";
        }
    }
}
