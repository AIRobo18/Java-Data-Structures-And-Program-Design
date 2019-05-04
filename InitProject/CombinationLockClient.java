import java.util.*;

/**
 * CombinationLockClient - A class that tests the CombinationLock class.
 *
 * @author Anna Bieszczad
 * @version 08/14/2015
 */
public class CombinationLockClient
{
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        Scanner token;
        String input;
        boolean tryAgain;
        CombinationLock lock = null;
        do
        {
            tryAgain = false;
            try
            {
                do
                {
                    // prompt for 3 secret combination values
                    System.out.println("Enter 3 positive integers separated by spaces representing the combination lock (i.e. 8 13 14)");
                    input = scan.nextLine();
                } while (!input.matches("\\d+ \\d+ \\d+"));

                token = new Scanner(input);
                // create CombinationLock object.
                // The constructor checks the input against the MAX_VALUE, so the possible exception must be handled
                lock = new CombinationLock(token.nextInt(), token.nextInt(), token.nextInt());
            } catch (CombinationLockInitializationException clie)
            {
                System.out.println(clie.getMessage());
                tryAgain = true;
            }
        } while (tryAgain);
        // display the current state of the lock
        System.out.println(lock);
        do
        {
            tryAgain = false;
            do
            {
                // prompt for the number of tick to turn the dial in attempt to open it
                System.out.println("Enter tick values to open the lock (i.e. 9 right 1 left 23 right)");
                input = scan.nextLine().toLowerCase();
            } while (!input.matches("[1-9][0-9]* (left|right) [1-9][0-9]* (left|right) [1-9][0-9]* (left|right)"));
            token = new Scanner(input);
            lock.turnTheDial(token.nextInt(), (token.next().equals("left") ? Combination.LEFT : Combination.RIGHT),
                    token.nextInt(), (token.next().equals("left") ? Combination.LEFT : Combination.RIGHT),
                    token.nextInt(), (token.next().equals("left") ? Combination.LEFT : Combination.RIGHT));
            System.out.println(lock);
            if (lock.open())
                System.out.println("\nThe lock has been open!");
            else
            {
                // the given combination was incorrect. We will reset the dial to 0 and ask the user to try again
                System.out.println("\nWrong sequence, resetting the dial to 0. Try again!\n");
                tryAgain = true;
                lock.reset();
            }
        } while (tryAgain);

        // test equals method
        System.out.println("\n>>>Testing equals method<<<");
        CombinationLock lock1 = new CombinationLock(1, 2, 3);
        System.out.println("lock1 is: " + lock1);
        CombinationLock lock2 = new CombinationLock(1, 2, 3);
        System.out.println("lock2 is: " + lock2);
        if (lock1.equals(lock2))
            System.out.println("lock1 and lock2 are the same - CORRECT");
        else
            System.out.println("lock1 and lock2 are not the same - INCORRECT");
        System.out.println("\nTurning the dial of lock1:");
        lock1.turnTheDial(1, Combination.RIGHT, 1, Combination.LEFT, 3, Combination.RIGHT);
        System.out.println("lock1 is now: " + lock1);
        System.out.println("Turning the dial of lock2:");
        lock2.turnTheDial(1, Combination.RIGHT, 39, Combination.LEFT, 1, Combination.RIGHT);
        System.out.println("lock2 is now: " + lock2);
        if (lock1.equals(lock2))
            System.out.println("lock1 and lock2 are the same - INCORRECT");
        else

            System.out.println("lock1 and lock2 are not the same - CORRECT");
    }
}