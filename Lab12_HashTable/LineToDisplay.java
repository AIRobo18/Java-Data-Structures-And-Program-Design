import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class that is used to display the lines of text that are corrected.
 *
 * @author Anna Bieszczad
 * @version 11/10/2015
 *
 * @modified Robert Aroutiounian
 */


public class LineToDisplay
{
    private ArrayList<Word> line;

    /**
     * Constructor for objects of class LineToDisplay
     */
    public LineToDisplay()
    {
        this.line = new ArrayList<>();
    }

    /**
     * Add a new word to the current line
     *
     * @param word  a Word object to be added
     */
    public void addWord(Word word)
    {
        this.line.add(word);
    }


    /**
     * displays the corrected input line
     */
    public void display()
    {
        Iterator<Word> iterator = this.line.iterator();

        while (iterator.hasNext())
        {
            System.out.print(iterator.next().getWord());
        }
        System.out.println();
    }
}