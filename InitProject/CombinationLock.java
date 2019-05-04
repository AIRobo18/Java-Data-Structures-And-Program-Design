/**
 * CombinationLock - A service class that implements the CombinationLock functionality.
 *
 * @author Robert Aroutiounian
 * @version 08/14/2015
 */
public class CombinationLock
{
    private int dial;
    private Combination combination1;
    private Combination combination2;
    private Combination combination3;
    public static final int MAX_VALUE = 39;

    /**
     * The secondary constructor for objects of class CombinationLock.
     * Throws an exception if the value is greater than the maximum allowed
     */
    public CombinationLock(int secret1, int secret2, int secret3) throws CombinationLockInitializationException
    {
        if (secret1 > MAX_VALUE)
        {
            throw new CombinationLockInitializationException("Value cannot exceed " + MAX_VALUE);
        }
        else
        {
            this.combination1 = new Combination(secret1, secret1);
        }

        if (secret2 > MAX_VALUE)
        {
            throw new CombinationLockInitializationException("Value cannot exceed " + MAX_VALUE);
        }
        else
        {
            this.combination2 = new Combination(secret2, secret2);
        }

        if (secret3 > MAX_VALUE)
        {
            throw new CombinationLockInitializationException("Value cannot exceed " + MAX_VALUE);
        }
        else
        {
            this.combination3 = new Combination(secret3, secret3);
        }
    }

    /**
     * Calls turn method to turn the dial for each number
     *
     * @param noOfTicks1 number of ticks for the first combination number
     * @param turn1      direction to turn the dial for the first combination number
     * @param noOfTicks2 number of ticks for the second combination number
     * @param turn2      direction to turn the dial for the second combination number
     * @param noOfTicks3 number of ticks for the third combination number
     * @param turn3      direction to turn the dial for the third combination number
     */
    public void turnTheDial(int noOfTicks1, int turn1, int noOfTicks2, int turn2, int noOfTicks3, int turn3)
    {
        this.combination1.turn(this.dial, noOfTicks1, turn1);
        this.combination2.turn(this.dial, noOfTicks2, turn2);
        this.combination3.turn(this.dial, noOfTicks3, turn3);
    }

    /**
     * Checks if the Combination lock is open
     *
     * @return true if the correct() for each number returns true
     *         false otherwise
     */
    public boolean open()
    {
        return this.combination1.correct() &&
                this.combination2.correct() &&
                this.combination3.correct();
    }

    /**
     * Determines if two CombinationLock objects are in the same state
     *
     * @param other the CombinationLock object to test against for equality
     * @return true if both objects are in the same state
     */
    public boolean equals(CombinationLock other)
    {
        return this.combination1.equals(other.combination1) &&
                this.combination2.equals(other.combination2) &&
                this.combination3.equals(other.combination3);
    }

    /**
     * resets dial to 0 and each combination number to its default state
     */
    public void reset()
    {
        this.dial = 0;
        this.combination1.reset();
        this.combination2.reset();
        this.combination3.reset();
    }

    /**
     * @return String containing the current values of the CombinationLock object
     *         including the secret values.
     */
    public String toString()
    {
        return "The currnet values of the combination locks are: " + this.combination1.getCurrentValueAndTurn() + " "
                + this.combination2.getCurrentValueAndTurn() + " "
                + this.combination3.getCurrentValueAndTurn()
                + "\nThe secret values are: " + this.combination1.getSecretValueAndTurn() + " "
                + this.combination2 + " "
                + this.combination3.getSecretValueAndTurn();
    }
}