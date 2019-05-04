import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Robert Aroutiounian.
 */
public class CheckBinaryRadixSort extends SortArray
{
    public static void main(String[] args)
    {
        int arraySize = 0;
        int numOfTrials = 0;
        int maxValue = 0;
        boolean invalidInput;
        do
        {
            try
            {
                invalidInput = false;
                Scanner input = new Scanner(System.in);

                System.out.println("What would you like your array size to be, something greater than or equal to 1.");
                arraySize = input.nextInt();

                System.out.println("What would you like the number of trials to be, something greater than or equal to 1.");
                numOfTrials = input.nextInt();

                System.out.println("What would you like your largest value to be. something greater than or equal to 1.");
                maxValue = input.nextInt();
            } catch (InputMismatchException ime)
            {
                System.out.println("Could not convert input to an integer");
                invalidInput = true;
            } catch (Exception e)
            {
                System.out.println("There was an error with System.in");
                System.out.println(e.getMessage());
                invalidInput = true;
            }
        } while (invalidInput);

        System.out.println();

        Integer[] array = new Integer[arraySize];
        Integer[] copy = new Integer[array.length];
        for (int n = 1; n <= numOfTrials; n++)
        {
            System.out.println("TRIALS #" + n);
            Random ran = new Random();

            for (int i = 0; i < array.length; i++)
            {
                array[i] = ran.nextInt((maxValue) + 1);
            }

            for (int i = 0; i < array.length; i++)
            {
                copy[i] = array[i];
            }

            System.out.println("The original array is: ");
            System.out.println(Arrays.toString(copy));

            binaryRadixSort(copy, maxValue);
            System.out.println("The original array sorted with countingSort is: ");
            System.out.println(Arrays.toString(copy));

            Arrays.sort(array);
            if (Arrays.equals(array, copy))
            {
                System.out.println("    Passes");
            }
            else
            {
                System.out.println("    Fails");
            }
            System.out.println();
        }
    }
}