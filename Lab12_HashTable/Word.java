/**
 * This class holds a token from a text with a flag indicating if the word
 * is believed to be spelled correctly.
 *
 * @author Anna Bieszczad
 * @version 11/10/2015
 *
 * @modified Robert Aroutiounian
 */

public class Word
{
    private String word;
    private boolean spelling;

    /**
     * Constructor for objects of class Word
     */
    public Word(String word, boolean spelling)
    {
        this.word = word;
        this.spelling = spelling;
    }

    /**
     * accessor for the word held by the class
     * @return the word
     */
    public String getWord()
    {
        return this.word;
    }

    /**
     * is the word spelled correctly
     * @return the flag held by the class indicating if the word is spelled correctly
     */

    public boolean isSpelledCorrectly()
    {
        return this.spelling;
    }
}