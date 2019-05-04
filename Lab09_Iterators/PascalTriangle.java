import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * A class that represents Pascal Triangles.
 *
 * @author Robert Aroutounian
 * @version 10/18/2015
 */
public class PascalTriangle
{
    private List<ArrayList<Integer>> triangle;
    private static final int DEFAULT_NUMBER_OF_ROWS = 10;
    private static final int INVALID_INPUT = -1;

    public PascalTriangle()
    {
//        System.out.println("default constructor - implement me");
        this(DEFAULT_NUMBER_OF_ROWS);
    } // end default constructor

    /**
     * Precondition: numberOfRows > 1
     * <p/>
     * The constructor creates the this.triangle object - list of list filled
     * with Integers that are calculated to produce proper Pascal Triangle property.
     * Utilizes createNextRow private method
     */
    public PascalTriangle(int numberOfRows)
    {
//        System.out.println("secondary constructor - implement me");
        this.triangle = new ArrayList<>(numberOfRows);
        for (int i = 0; i < numberOfRows; i++)
        {
            this.triangle.add(createNextRow(i));
        }
    } // end constructor

    /**
     * Returns the list that represents the row whose index is rowIndex.
     */

    private ArrayList<Integer> createNextRow(int rowIndex)
    {
        ArrayList<Integer> nextRow = new ArrayList<>();

        int number;
        if (rowIndex == 0)
        {
            nextRow.add(rowIndex, 1);
        }
        else if (rowIndex == 1)
        {
            nextRow.add(1);
            nextRow.add(1);
        }
        else
        {
            nextRow.add(1);
            for (int i = 1; i < rowIndex; i++)
            {
                number = (triangle.get(rowIndex - 1).get(i - 1)) + (triangle.get(rowIndex - 1).get(i));
                nextRow.add(number);
            }
            nextRow.add(1);
        }

        return nextRow;
    } // end createNextRow

    /**
     * prints the content of the this.triangle object
     * MUST USE FOR-EACH LOOPS
     */
    public void display()
    {
//        System.out.println("display method - implement me");

        for (ArrayList<Integer> item : this.triangle)
        {
            for (Integer element : item)
            {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        // MUST USE FOR-EACH LOOPS
    }

    /**
     * calculates and prints the sums of integers in each row of this.triangle
     * MUST USE ITERATORS
     */
    public void printHorizontalSums()
    {
//        System.out.println("printHorizontalSums method - implement me");

        for (int i = 0; i < this.triangle.size(); i++)
        {
            Iterator<Integer> iterator = this.triangle.get(i).iterator();
            int sum = 0;

            while (iterator.hasNext())
            {
                sum += iterator.next();
            }
            System.out.println(sum);
        }
        // MUST USE ITERATORS
    }

    /**
     * @param totalObjects            - total number of objects
     * @param numberOfObjectsToChoose
     * @return number representing how many combinations of objects are possible
     *         or -1 if the input is invalid
     */
    public int howManyWays(int totalObjects, int numberOfObjectsToChoose)
    {
//        System.out.println("howManyWays method - implement me");

        if (numberOfObjectsToChoose > totalObjects)
        {
            return INVALID_INPUT;
        }
        else
        {
            int ways = this.triangle.get(totalObjects).get(numberOfObjectsToChoose);
            return ways;
        }
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int triangleSize = 0;
        do
        {
            try
            {
                System.out.println("Please enter the triangle size that is greater than 1");
                triangleSize = scanner.nextInt();
            } catch (Exception e)
            {
                System.err.println("Invalid input");
            }
        } while (triangleSize <= 1);

        PascalTriangle triangle = new PascalTriangle(triangleSize);
        triangle.display();
        System.out.println("\nHorizontal sums are:");
        triangle.printHorizontalSums();
        System.out.println("\n***COMBINATION problem***\nHow many balls in total? ");
        int totalBalls = scanner.nextInt();
        System.out.println("How many balls to choose?");
        int numberOfBallsToChoose = scanner.nextInt();
        System.out.println("You have " + totalBalls + " balls. How many different ways could you choose just "
                + numberOfBallsToChoose + " of them?");
        int result = triangle.howManyWays(totalBalls, numberOfBallsToChoose);

        if (result != PascalTriangle.INVALID_INPUT)
            System.out.println("The answer is: " + result);
        else
            System.out.println("The answer cannot be given with the triangle of size " + triangleSize);
    } // end main
} // end PascalTriangle