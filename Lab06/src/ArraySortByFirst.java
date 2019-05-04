/**
 * This program will sort an n by n array by the first value in each row.
 * Selection sort algorithm is modified to do the sorting.
 * For example:
 * <p/>
 * If the original array is:
 * 1  2  3  4  5
 * 3  4  5  1  2
 * 5  2  3  4  1
 * 2  3  1  4  5
 * 4  2  3  1  5
 * <p/>
 * The array after sorting is:
 * 1  2  3  4  5
 * 2  3  1  4  5
 * 3  4  5  1  2
 * 4  2  3  1  5
 * 5  2  3  4  1
 *
 * @author Robert Aroutiounian
 * @version 09/26/2015
 */
public class ArraySortByFirst
{
    /**
     * Task: Sorts an array of integers by the first value of each row. After
     * sorting, the first column of the array is in ascending order.
     *
     * @param data an n by n array of integers
     */
    public static void sortByFirst(int data[][])
    {
//        System.out.println("IN sortByFirst - IMPLEMENT ME - I am similar to selection sort");

        int indexOfNextSmallest;
        for (int r = 0; r < data.length - 1; r++)
        {
            indexOfNextSmallest = getIndexOfSmallest(data, r, data.length - 1);
            swap(data, r, indexOfNextSmallest);
        }
    } // end selectionSort

    /**
     * Task: Finds the index of the smallest value in a portion of an array.
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0 and < a.length that is the index of
     *              the first array element to consider
     * @param last  an integer >= first and < a.length that is the index
     *              of the last array element to consider
     * @return the index of the smallest value among
     *         a[first], a[first + 1], . . . , a[last]
     */
    private static int getIndexOfSmallest(int[][] a, int first, int last)
    {
        int min = a[first][0];
        int indexOfMin = first;

//        System.out.println("IN getIndexOfSmallest - IMPLEMENT ME");

        for (int r = indexOfMin; r <= last; r++)
        {
            if (min > a[r][0])
            {
                indexOfMin = r;
                min = a[indexOfMin][0];
            }
        }

        return indexOfMin;
    } // end getIndexOfSmallest

    /**
     * Task: Swaps the rows  a[i] and a[j].
     *
     * @param a an n by n array of integers
     * @param i an integer >= 0 and < a.length
     * @param j an integer >= 0 and < a.length
     */
    private static void swap(int[][] a, int i, int j)
    {
//        System.out.println("IN swap - IMPLEMENT ME");

        int[] temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    } // end swap

    public static void display(int data[][])
    {
//        System.out.println("IN display - IMPLEMENT ME");

        for (int r = 0; r < data.length; r ++)
        {
            for (int c = 0; c < data[r].length; c++)
            {
                System.out.print(data[r][c] + " ");
            }
            System.out.println();
        }
    } // end display

    public static void main(String args[])
    {
        int array[][] = {{1, 2, 3, 4, 5},
                {3, 4, 5, 1, 2},
                {5, 2, 3, 4, 1},
                {2, 3, 1, 4, 5},
                {4, 2, 3, 1, 5}};

        System.out.println("The original array is ");
        display(array);
        System.out.println();

        sortByFirst(array);
        System.out.println("The array after sorting is ");
        display(array);
        System.out.println();
    } // end main
} // end ArraySortByFirst
