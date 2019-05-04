/**
 * A class that tracks the user choice as a function of the last four choices.
 *
 * @author Robert Aroutiounian
 * @version 11/10/2015
 */
public class UserChoiceCounts
{
    private int countA;
    private int countB;

    public UserChoiceCounts()
    {
        this.countA = 0;
        this.countB = 0;
    } // end default constructor

    public UserChoiceCounts(char choice)
    {
        if (choice == 'A')
        {
            this.countA = 1;
            this.countB = 0;
        }
        else
        {
            this.countA = 0;
            this.countB = 1;
        }
    } // end secondary constructor

    public int getCountA()
    {
        return this.countA; // THIS IS A STUB
    } // end getCountA

    public int getCountB()
    {
        return this.countB; // THIS IS A STUB
    } // end getCountB

    public void incrementCount(char choice)
    {
        if (choice == 'A')
        {
            this.countA++;
        }
        else
        {
            this.countB++;
        }
    } // end incrementCount

    public String toString()
    {
        return "A = " + this.countA + ", B = " + this.countB;
    }
} // end UserChoiceCounts
