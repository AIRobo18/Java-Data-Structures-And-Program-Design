import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.text.*;
import java.util.*;

/**
 * A class that implements math operations utilizing a stack.
 *
 * @author Robert Aroutiounian
 * @version 09/14/2015
 */
public class MathWithStack
{
    public void decimalToBinary()
    {
        System.out.println("DECIMAL TO BINARY CONVERTER");
        Scanner keyboard = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        try
        {
            do
            {
                System.out.println("\nPlease enter a positive integer, or type \"stop\"");
                int decimalNumber = keyboard.nextInt();

                System.out.print(decimalNumber + " in binary is --> ");

                while (decimalNumber != 0)
                {
                    stack.push(decimalNumber % 2);
                    decimalNumber = decimalNumber / 2;
                }

                while (!stack.isEmpty())
                {
                    System.out.print(stack.pop());
                }

                System.out.println();

            } while (true);
        }
        catch (InputMismatchException ime)
        {
            System.out.println("Done with conversion.\n");
        }
    }

    public void ancientMultiplier()
    {
        // http://en.wikipedia.org/wiki/Ancient_Egyptian_multiplication

        System.out.println("ANCIENT MULTIPLIER");
        Scanner keyboard = new Scanner(System.in);
        Stack<Integer> nStack = new Stack<>();
        Stack<Integer> numberStack = new Stack<>();
        try
        {
            do
            {
                int smallestOperand;
                int largestOperand;
                int n = 1;
                int number = 0;

                System.out.println("\nPlease enter operand1, or type \"stop\"");
                int operand1 = keyboard.nextInt();

                System.out.println("Please enter operand2");
                int operand2 = keyboard.nextInt();

                if (operand1 > operand2)
                {
                    smallestOperand = operand2;
                    largestOperand = operand1;
                    System.out.println("The smallest operand is: " + smallestOperand + ";"
                                        + " and the larger operand is: " + largestOperand);
                }
                else
                {
                    smallestOperand = operand1;
                    largestOperand = operand2;
                    System.out.println("The smallest operand is: " + smallestOperand + ";"
                                        + " and the larger operand is: " + largestOperand);
                }


                System.out.println("--> Creating the mapping table:");
                number = largestOperand;
                int i = 1;
                while (n <= smallestOperand)
                {
                    nStack.push(n);

                    if (n > i)
                    {
                        number = number + number;
                    }
                    numberStack.push(number);
                    System.out.println(nStack.peek() + " --> " + numberStack.peek());
                    n = n + n;
                }

                System.out.println();
                System.out.println("--> Calculating the result");
                System.out.print(smallestOperand + " * " + largestOperand + ":");
                int result = 0;
                while (!nStack.isEmpty())
                {
//                    System.out.println("Running while");
                    if (smallestOperand >= nStack.peek())
                    {
//                        System.out.println("Running if");
                        smallestOperand = smallestOperand - nStack.pop();
                        result += numberStack.peek();
                        System.out.print(" + " + numberStack.pop());
                    }
                    else
                    {
//                        System.out.println("Running else");
                        nStack.pop();
                        numberStack.pop();
                    }
                }
                System.out.print(" = " + result);

                System.out.println();
            } while (true);
        }
        catch (InputMismatchException ime)
        {
            System.out.println("Done with conversion.\n");
        }

    }


    public static void main(String[] args)
    {
        MathWithStack mathWithStack = new MathWithStack();
        mathWithStack.decimalToBinary();
        mathWithStack.ancientMultiplier();
    }
}