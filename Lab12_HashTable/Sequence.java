/**
 * A class that represents the search key for the mind reader problem
 * We retain the last four user choices as the current key.
 *
 * @author Robert Aroutiounian
 * @version 11/10/2015
 */
public class Sequence
{
    private String lastFourChoices;

    public Sequence()
    {
        this.lastFourChoices = "1234"; // initialize to any 4-character string
    } // end default constructor

    public void updateLastFourChoices(char nextChoice)
    {
        String sub = this.lastFourChoices.substring(1, this.lastFourChoices.length());
        this.lastFourChoices = sub + nextChoice;
    } // end updateLastFourChoices

    public String getLastFourChoices()
    {
        return this.lastFourChoices;  // THIS IS A STUB
    } // end getLastFourChoices

    public int hashCode()
    {
        int hash = 0;
        int n = this.lastFourChoices.length();

        for (int i = 0; i < n; i++)
        {
            hash = 17 * hash + this.lastFourChoices.charAt(i);
        }
        return 0; // THIS IS A STUB
    } // end hashCode

    public String toString()
    {
        return this.lastFourChoices; // THIS IS A STUB
    }
}
