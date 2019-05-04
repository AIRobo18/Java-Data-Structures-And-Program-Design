/**
 * A class that keeps score for the mind reading game.
 *
 * @author Robert Aroutiounian
 * @version 11/10/2015
 */
public class ScoreBoard
{
    private int correctCount;
    private int incorrectCount;

    public ScoreBoard()
    {
        this.correctCount = 0;
        this.incorrectCount = 0;
    } // end default constructor

    public void incrementCorrect()
    {
        this.correctCount++;
    } // end incrementCorrect

    public void incrementIncorrect()
    {
        this.incorrectCount++;
    } // end incrementIncorrect

    public int getCorrectCount()
    {
        return this.correctCount; // THIS IS A STUB
    } // end getCorrectCount

    public int getIncorrectCount()
    {
        return this.incorrectCount; // THIS IS A STUB
    } // end getIncorrectCount
}  // end ScoreBoard
