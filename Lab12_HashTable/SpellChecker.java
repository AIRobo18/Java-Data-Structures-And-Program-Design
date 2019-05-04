import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * An application that checks the given file for spelling errors based on the given dictionary
 *
 * @author Anna Bieszczad
 * @version 11/10/2015
 *
 * @modified Robert Aroutiounian
 *
 */

public class SpellChecker
{
    private DictionaryInterface<String, String> myDictionary;
    private DictionaryInterface<String, Integer> misspelledWords;

    public SpellChecker()
    {
        this.myDictionary = new HashedDictionary<String, String>();
        this.misspelledWords = new HashedDictionary<String, Integer>();
    }

    /**
     * load the words into the dictionary
     *
     * @param theFileName   the name of the file holding the words to put in
     *                      the dictionary.
     */
    public void loadDictionary(String theFileName) throws IOException
    {
        System.out.println("***THE MAIN DICTIONARY***");
        File file1 = new File(theFileName);
        Scanner scan = new Scanner(file1);
        while (scan.hasNext())
        {
            String spelling = scan.next();
            this.myDictionary.add(spelling, spelling);
        }
        scan.close();
        this.myDictionary.displayHashTable();
        System.out.println("***END OF THE MAIN DICTIONARY***");
        System.out.println();
    }


    /**
     * get the words to check, check them, then put Word objets into currentLine
     * When a single line has been read display the currentLine to the user
     *
     * @param theFileName   the name of the file holding the text to check
     */
    public void checkText(String theFileName) throws IOException
    {
        System.out.println("***THE SpellChecker RESULTS***");
        System.out.println();
        File file = new File(theFileName);
        Scanner scan = new Scanner(file);
        while (scan.hasNext())
        {
            LineToDisplay display = new LineToDisplay();
            String line = scan.nextLine();
            StringTokenizer st = new StringTokenizer(line, " !,.?:;\")(-", true);
            while (st.hasMoreTokens())
            {
                String token = st.nextToken();
                boolean check = checkToken(token);
                Word word = new Word(token, check);
                if (!check)
                {
                    word = new Word("^" + token + "^", check);
                }
                display.addWord(word);
                if (!check)
                {
                    token = token.toLowerCase();
                    Integer count = this.misspelledWords.getValue(token);
                    if (count == null)
                    {
                        this.misspelledWords.add(token, 1);
                    }
                    else
                    {
                        count++;
                        this.misspelledWords.add(token, count);
                    }
                }
            }
            display.display();
        }
        System.out.println("***THE END***");
        System.out.println();
    }

    /**
     * check the spelling of a single word
     *
     * @param word          a single word to check
     * @return true if the word is correctly spelled or if the word is a non-alpha .
     */
    public boolean checkToken(String word)
    {
        boolean spelled = false;
        String pat = "[a-zA-Z']+";
        if (this.myDictionary.getValue(word) != null || !word.matches(pat))
        {
            spelled = true;
        }
        return spelled;
    }

    public void handleMisspelledWords()
    {
        System.out.println("***HANDLING THE MISSPELLED WORDS***");
        System.out.println();
        System.out.println("Misspelled Dictionary Before has " + this.misspelledWords.getSize() +  " entries:");
        this.misspelledWords.displayHashTable();

        System.out.println("***Looking for words to be added to the main dictionary***");
        Iterator<String> iteratorString = this.misspelledWords.getKeyIterator();
        Iterator<Integer> iteratorInt = this.misspelledWords.getValueIterator();
        while (iteratorString.hasNext() && iteratorInt.hasNext())
        {
            String string = iteratorString.next();
            Integer integer = iteratorInt.next();
            if (integer > 1)
            {
                System.out.println(string + " " + integer + " ---> moving to the main dictionary");
                this.myDictionary.add(string, string);
                this.misspelledWords.remove(string);
            }
            else
            {
                System.out.println(string + " " + integer + " ---> leaving");
            }
        }

        System.out.println();
        System.out.println("Misspelled Dictionary After has " + this.misspelledWords.getSize() + " entries:");
        this.misspelledWords.displayHashTable();

        System.out.println("Number of probes in main Dictionary " + this.myDictionary.getNumberOfProbes());
        System.out.println("Number of probes in main MisspelledDictionary " + this.misspelledWords.getNumberOfProbes());
    }

    public static void main(String args[])
    {
        SpellChecker spellChecker = new SpellChecker();
        try
        {
//            spellChecker.loadDictionary("sampleDictionarySmall.txt");
            spellChecker.loadDictionary("sampleDictionary.txt");
            spellChecker.checkText("checkMe.txt");
            spellChecker.handleMisspelledWords();

        }
        catch (IOException e)
        {
            System.out.println("There was an error in reading or opening the file: ");
            System.out.println(e.getMessage());
        }
    }
} // end class SpellChecker