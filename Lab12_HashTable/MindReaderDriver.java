import java.util.Scanner;

/**
 * A program that implements the mind-reader game.
 *
 * @author Anna Bieszczad
 * @version 11/10/2015
 *
 * @modified Robert Aroutiounian
 */
public class MindReaderDriver
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        MindReader mindReader = new MindReader();
        String userChoice;
        String userResponse;

        do
        {
            do
            {
                System.out.println("\nThink of either A or B, and I will guess your choice. Type QUIT to exit. ");

                userChoice = keyboard.nextLine().toUpperCase();
            } while (!userChoice.matches(("A|B|QUIT")));

            if (!userChoice.equalsIgnoreCase("QUIT"))
            {
                System.out.print("I guess that you chose " + mindReader.getNextGuess() + "; am I right? (Y/N) > ");
                userResponse = keyboard.nextLine().toUpperCase();
                mindReader.updateScoreBoard(userResponse);
                mindReader.displayScoreBoard();
                mindReader.updateUserChoices(userChoice.charAt(0)); // update the dictionary
            }
        } while (!userChoice.equalsIgnoreCase("QUIT"));
        System.out.println("Bye!");
    }  // end main
}  // end MindReaderDriver