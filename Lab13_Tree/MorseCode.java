import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.*;

/**
 * A class that implements text encoding/decoding with Morse Code
 *
 * @author Robert Aroutiounian
 * @version 11/12/2015
 */

public class MorseCode
{
    private BinaryNode<Character> root;

    public MorseCode()
    {
        this.root = new BinaryNode<>(' ');
    }

    private void buildMorseCodeTree()
    {
        int index;
        BinaryNode<Character> current;
        BinaryNode<Character> letterTree;
        String code = null;
        try
        {
            Scanner file = new Scanner(new File("MorseCode.txt"));
            System.out.println("The Morse Code:");
            System.out.println("===============");

            while (file.hasNext()) // test for the end of the file
            {
                code = file.nextLine();  // read a line
                System.out.println(code);  // print the line read
                // building the tree
                letterTree = new BinaryNode<>(code.charAt(0));
                current = this.root;
                index = 2;
                for (; index < code.length() - 1; index++)
                {
                    if (code.charAt(index) == '.')
                    {
                        current = current.getLeftChild();
                    }
                    else // must be '_'
                    {
                        current = current.getRightChild();
                    }
                }
                if (code.charAt(index) == '.')
                {
                    current.setLeftChild(letterTree);
                }
                else // must be '_'
                {
                    current.setRightChild(letterTree);
                }
            }
            file.close();

        } catch (FileNotFoundException fnfe)
        {
            System.out.println("Unable to find MorseCode.txt, exiting");
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    private void decode(String encoded)
    {
        System.out.println("Decoding \"" + encoded + "\"");
        BinaryNode<Character> current = this.root;
        String answer = "";

        try
        {
            Scanner scan = new Scanner(encoded);
            String code;

            while (scan.hasNext())
            {
                code = scan.next();
                for (int i = 0; i < code.length(); i++)
                {
                    Character currentChar = code.charAt(i);
                    if (currentChar == '.' && current.hasLeftChild())
                    {
                        current = current.getLeftChild();
                    }
                    else if (currentChar == '_' && current.hasRightChild())
                    {
                        current = current.getRightChild();
                    }
                    else
                    {
                        throw new InputMismatchException();
                    }
                }
                answer += current.getData();
                current = this.root;
            }
            System.out.println("The decoded string is \"" + answer + "\"");
        }
        catch (InputMismatchException ime)
        {
            System.out.println("Not a Morse pattern.");
        }
    }

    public void display()
    {
        System.out.println(display(this.root));
    }

    public String display(BinaryNode<Character> current)
    {
        String s = "";
        if (current.getLeftChild() != null)
        {
            s += current.getLeftChild().getData() + " " + display(current.getLeftChild());
        }
        if (current.getRightChild() != null)
        {
            s += current.getRightChild().getData() + " " + display(current.getRightChild());
        }
        //s += "\n";
        return s;
    }

    public LevelOrderIterator getLevelOrderIterator()
    {
        return new LevelOrderIterator();
    } // end getLevelOrderIterator


    private class LevelOrderIterator implements Iterator
    {
        private LinkedBlockingQueue<BinaryNode<Character>> nodeQueue;

        public LevelOrderIterator()
        {
            this.nodeQueue = new LinkedBlockingQueue<>();
            if (root != null)
            {
                this.nodeQueue.offer(root);
            }
        } // end default constructor

        public boolean hasNext()
        {
            return !this.nodeQueue.isEmpty();
        } // end hasNext

        public BinaryNode<Character> next()
        {
            BinaryNode<Character> nextNode;

            if (hasNext())
            {
                nextNode = this.nodeQueue.poll();
                BinaryNode<Character> leftChild = nextNode.getLeftChild();
                BinaryNode<Character> rightChild = nextNode.getRightChild();

                // add to queue in order of recursive calls
                if (leftChild != null)
                    this.nodeQueue.offer(leftChild);

                if (rightChild != null)
                    this.nodeQueue.offer(rightChild);
            }
            else
            {
                throw new NoSuchElementException();
            }

            return nextNode;
        } // end next

        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end LevelOrderIterator


    public static void main(String[] args)
    {
        MorseCode morseCode = new MorseCode();
        morseCode.buildMorseCodeTree();

        LevelOrderIterator iter = morseCode.getLevelOrderIterator();
        System.out.println("\nThe Morse Code Tree in the level order:");
        while (iter.hasNext())
        {
            System.out.print(iter.next().getData() + " ");
        }
        System.out.println("\n\n");
        //morseCode.display();
        String response = "";
        Scanner keyboard = new Scanner(System.in);
        do
        {
            do
            {
                System.out.println("Please enter a message in Morse Code, use space as a seperator.");
                response = keyboard.nextLine();
            } while (!response.matches("[._ ]+"));

            morseCode.decode(response);
            System.out.println("Would you like to decode another message? (yes/no)");
            response = keyboard.nextLine();
        } while (response.equalsIgnoreCase("yes"));
    }
}