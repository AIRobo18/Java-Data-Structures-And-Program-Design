import java.util.*;

/**
 * This class represents chains of linked nodes that
 * can be sorted by a Shell sort.
 *
 * @author Charles Hoot
 * @author Frank M. Carrano
 *         Modified by Anna Bieszczad
 * @author Robert Aroutiounian
 * @version 09/26/2015
 */
public class ChainSort<T extends Comparable<? super T>>
{
    private Node<T> firstNode; // reference to first node
    private int length; // number of entries in list

    public ChainSort()
    {
        clear();
    } // end default constructor

    public final void clear()
    {
        this.firstNode = null;
        this.length = 0;
    } // end clear

    public final int getLength()
    {
        return this.length;
    } // end isFull

    public void display()
    {
        Node currentNode = this.firstNode;
        while (currentNode != null)
        {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    } // end display

    public boolean isEmpty()
    {
        boolean result;

        if (this.length == 0)
        {
            assert this.firstNode == null;
            result = true;
        } else
        {
            assert this.firstNode != null;
            result = false;
        }

        return result;
    } // end isEmpty

    public void addToBeginning(T newEntry)
    {
        Node<T> newNode = new Node<>(newEntry);
        newNode.next = this.firstNode;
        this.firstNode = newNode;
    } // end addToBeginning

    public void shellSort(int first, int last)
    {
//        System.out.println("IN shellSort METHOD - IMPLEMENT ME");
        // for each space
        //     create subchains
        //     call incrementalInsertionSort

        int n = ((last - first) + 1)/2;

//        while (n > 0)
        for (n = n; n > 0; n = n/2)
        {
//            int counter = 0;
//            int counter2 = 0;
            Node<T> previousNode = this.firstNode;
            Node<T> currentNode = this.firstNode;

//            while (counter < n)
            for (int i = first; i < first + n; i++)
            {
                currentNode = currentNode.next;
//                counter++;
            }
            currentNode.previous = previousNode;

//            while (counter2 < n)
            for (int i = first + n; i < last; i++)
            {
                currentNode = currentNode.next;
                previousNode = previousNode.next;
                currentNode.previous = previousNode;
//                counter2++;
            }
            System.out.println("---> Before partial sort with space " + n + " :");
            incrementalInsertionSort(first, last, n);
            System.out.println("---> After partial sort done with space " + n + " :");
            display();
            System.out.println();
//            n = n/2;
        }

    } // end shellSort

    /**
     * Task: Sorts equally spaced elements of a linked chain into
     * ascending order. Subchains created with a use of previous.
     *
     * @param first the integer index of the first element to consider;
     * @param last  the integer index of the last element to consider;
     * @param space the difference between the indices of the
     *              elements to sort
     */
    private void incrementalInsertionSort(int first, int last, int space)
    {
//        System.out.println("IN incrementalInsertionSort METHOD - IMPLEMENT ME");

        Node<T> previousNode = this.firstNode;
        Node<T> currentNode = this.firstNode;
        int counter = 0;
        T temp;
        Node<T> temp2;
        while (counter < space)
        {
            currentNode = currentNode.next;
            counter++;
        }
        currentNode.previous = previousNode;

        int unsorted;
        for (unsorted = first + space; unsorted <= last; unsorted++)
        {
            System.out.println("---> Comparing current: " + currentNode.data + " with previous: " + previousNode.data);
            if (currentNode.data.compareTo(previousNode.data) < 0)
            {
                System.out.println("--> " + currentNode.data + " is smaller than " + previousNode.data);
                temp = currentNode.data;
                currentNode.data = previousNode.data;
                if (previousNode.previous != null)
                {
//                    System.out.println("Previous is not null");
                    temp2 = previousNode;
//                    System.out.println("temp is " + temp + ", temp2 is " + temp2.previous.data);
//                    if (temp.compareTo(temp2.previous.data) < 0)
//                    {
//                        System.out.println("Yes");
//                    }
//                    else
//                    {
//                        System.out.println("No");
//                    }
                    while (temp2.previous != null && temp.compareTo(temp2.previous.data) < 0)
                    {
//                        System.out.println("Inside while loop");
                        System.out.println("---> Comparing " + temp + " with " + temp2.previous.data);
                        System.out.println("--> " + temp + " is smaller than " + temp2.previous.data);
                        temp2.data = temp2.previous.data;
                        temp2 = temp2.previous;
                    }
                    temp2.data = temp;
                }
                else
                {
                    previousNode.data = temp;
                }
                display();
            }

            previousNode = previousNode.next;
            currentNode = currentNode.next;
        }
    } // end incrementalInsertionSort


    private class Node<S>
    {
        private S data;
        private Node<S> next;
        private Node<S> previous; // ADDED for linking backwards for shell sort

        private Node(S dataPortion)
        {
            this.data = dataPortion;
            this.next = null;
            this.previous = null;
        }
    } // end Node

    // ************ TEST DRIVER *****************

    public static void main(String args[])
    {
        System.out.println("What size chain should be used?");
        int chainSize = getInt("   It should be an integer value greater than or equal to 1.");

        System.out.println("What seed value should be used?");
        int seed = getInt("   It should be an integer value greater than or equal to 1.");
        Random generator = new Random(seed);
        ChainSort<Integer> myChain = new ChainSort<>();

        for (int i = 0; i < chainSize; i++)
            myChain.addToBeginning(generator.nextInt(100));

        System.out.print("\nOriginal Chain Content: ");
        myChain.display();
        myChain.shellSort(1, chainSize);
        System.out.print("\nSorted Chain Content: ");
        myChain.display();
    }


    /**
     * Get an integer value
     *
     * @param rangePrompt String representing a message used to ask the user for input
     * @return an integer
     */
    private static int getInt(String rangePrompt)
    {
        Scanner input;
        int result = 10;        //default value is 10
        try
        {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();

        } catch (NumberFormatException e)
        {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        } catch (Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;
    }
} // end ChainSort