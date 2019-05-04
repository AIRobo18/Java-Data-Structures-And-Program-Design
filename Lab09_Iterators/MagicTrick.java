import sun.tools.tree.ThisExpression;

import java.util.*;

/**
 * MagicTrick is a program that will guess a number that user is thinking of.
 *
 * @author Anna Bieszczad
 * @modifiedBy Robert Aroutiounian
 * @version 10/18/2015
 */
public class MagicTrick
{
    public final int NUM_OF_SEQUENCES = 5;
    public final int NUMBERS_PER_SEQUENCE = 16;
    private ArrayList<Integer>[] sequences;

    public MagicTrick()
    {
        this.sequences = new ArrayList[this.NUM_OF_SEQUENCES];

        int m, n, r = 0;

        for (int i = 0; i < this.NUM_OF_SEQUENCES; i++)
        {
            ArrayList<Integer> sequence = new ArrayList<>();

            m = 0;
            Integer number = (int)Math.pow(2, r);
            n = number + 1;
            sequence.add(number);

            for (int j = 0; j < this.NUMBERS_PER_SEQUENCE - 1; j++)
            {
                m++;

                if (m == n - 1)
                {
                    number = number + n;
                    m = 0;
                }
                else
                {
                    number = number + 1;
                }

                sequence.add(number);
            }

            this.sequences[i] = sequence;
            r++;
        }

    }

    public void displaySequences()
    {
        int number;
        for (int i = 0; i < this.sequences.length; i++)
        {
            number = i + 1;
            System.out.println("Sequence " + number + " " + this.sequences[i]);
        }
    }

    public void guessNumber(String[] answer)
    {
        int index;
        int sum = 0;
        for (int i = 0; i < answer.length; i++)
        {
            index = Integer.parseInt(answer[i]) - 1;
            sum += this.sequences[index].get(0);
        }
        System.out.println("Your number is " + sum);
    }

    public static void main(String[] args)
    {
        MagicTrick magic = new MagicTrick();
        System.out.println("Think of a number between 1 and 31\n");
        magic.displaySequences();

        System.out.println("\nList all the sequences that your number is in (ie. 1 3)");
        Scanner scan = new Scanner(System.in);
        magic.guessNumber(scan.nextLine().split(" "));
    }
}
