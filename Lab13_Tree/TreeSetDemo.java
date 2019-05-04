import java.util.Arrays;
import java.util.TreeSet;

/**
 * @version 11/12/2015.
 */
public class TreeSetDemo
{
    public static void main(String[] args)
    {
        String phrase = "the quick brown fox jumps over the lazy dog";
        //String phrase = "the quick fox jumps over the lazy brown dog";
        String[] phraseArray = phrase.split(" ");

        TreeSet<String> words = new TreeSet<>();
        words.addAll(Arrays.asList(phraseArray));

        System.out.println("words in the TreeSet: " + words);
    }
}
