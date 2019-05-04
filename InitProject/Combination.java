/**
 * Combination - A service class that handles one combination number for the CombinationLock class.
 *
 * @author Robert Aroutiounian
 * @version 08/14/2015
 */
public class Combination
{
    private int secretValue;     // the desired dial value for the combination number
    private int secretTurn;      // the desired turn value for the combination number

    private int currentValue;    // indicates the current dial value for the combination number
    private int currentTurn;     // indicates if the dial was turned to left or right for the combination number

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    /**
     * Secondary constructor
     *
     * @param secretValue
     * @param secretTurn
     */
    public Combination(int secretValue, int secretTurn)
    {
        this.secretValue = secretValue;
        this.secretTurn = secretTurn;
    }

    /**
     * Responsible for turning the dial, calls either turnRight or turnLeft method
     *
     * @param dial      the current dial value
     * @param noOfTicks the number of ticks to turn the dial
     * @param turn      the direction to turn the dial
     * @return the updated currentValue of the dial
     */
    public int turn(int dial, int noOfTicks, int turn)
    {
        this.currentValue = dial;
        this.currentTurn = turn;
        if (this.currentTurn == RIGHT)
        {
            turnRight(noOfTicks);
        }
        if (this.currentTurn == LEFT)
        {
            turnLeft(noOfTicks);
        }
        return this.currentValue;  // THIS IS A STUB
    }

    /**
     * Increases the currentValue of the dial by the noOfTicks. Rolls over if needed.
     */
    private void turnRight(int noOfTicks)
    {
        for (int i = 0; i < noOfTicks; i++)
        {
            this.currentValue += 1;

            if (this.currentValue == 40)
            {
                this.currentValue = 0;
            }
        }
        this.currentTurn = RIGHT;
    }

    /**
     * Decreases the currentValue of the counter by the noOfTicks. Rolls over if needed.
     */
    private void turnLeft(int noOfTicks)
    {
        for (int i = 0; i < noOfTicks; i++)
        {
            this.currentValue -= 1;

            if (this.currentValue == 40)
            {
                this.currentValue = 0;
            }
            this.currentTurn = LEFT;
        }
    }

    /**
     * sets the   currentValue to 0 and currentTurn to LEFT
     */
    public void reset()
    {
        this.currentValue = 0;
        this.currentTurn = LEFT;
    }

    /**
     * @return true if the combination number is in the desired state
     */
    public boolean correct()
    {
        if (this.currentValue == secretValue)
        {
            return true;
        }
        else
        {
            return false; // THIS IS A STUB
        }
    }

    /**
     * @param other the Combination object to test against for equality
     * @return true if both objects are in the same state
     */
    public boolean equals(Combination other)
    {
        return this.secretValue == other.secretValue &&
                this.secretTurn == other.secretTurn;
    }

    /**
     * @return String representation of the secretValue and secretTurn
     */
    public String getSecretValueAndTurn()
    {
        return "The secret value is: " + this.secretValue + "."
                + "\nThe secret turn is: " + this.secretTurn + ".";   // THIS IS A STUB
    }

    /**
     * @return String representation of the currentValue and currentTurn
     */
    public String getCurrentValueAndTurn()
    {
        return "The current value is: " + this.currentValue + "."
                + "\nThe current turn is: " + this.currentTurn + ".";  // THIS IS A STUB
    }
}