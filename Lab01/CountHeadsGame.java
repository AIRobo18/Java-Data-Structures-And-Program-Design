import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by robert.aroutiounian3 on 8/27/15.
 */
public class CountHeadsGame
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int amount;
        int input;

        boolean check;
        do
        {
            System.out.println("How many coins/bills can fit in this piggy bank?");
            amount = scan.nextInt();

            System.out.println("How many coins/bills should be put in the piggy bank?");
            input = scan.nextInt();

            if (input > amount)
            {
                check = true;
            }
            else
            {
                check = false;
            }
        } while (check);

        System.out.println("******* USER'S TURN *******");

        PiggyBank user = new PiggyBank(amount, input);

        for (int i = 0; i < input; i++)
        {
            System.out.println("Added $" + user.remove().getValue() + " to your piggy bank.");
        }

        System.out.println("There are " + input + " bills/coins in your piggy bank: ");

        ArrayList<Money> list;
        // I was thinking of making the PiggyBank into an array and have
        // a for loop print each element in the array and state whether
        // it landed heads or tails


    }
}