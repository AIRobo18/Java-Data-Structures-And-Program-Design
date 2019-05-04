/**
 * A class that implements a cipher with repeating key algorithm.
 *
 * @author Robert Aroutiounian
 * @version 10/9/2015
 */


public class Cipher
{
    private QueueInterface<Integer> keyQueueE;
    private QueueInterface<Integer> keyQueueD;
    private int[] key;

    public Cipher(int... key)
    {

//        System.out.println("IN Cipher constructor - IMPLEMENT ME");

        this.key = key;
        this.keyQueueE = new CircularArrayQueue<>(this.key.length);
        this.keyQueueD = new CircularArrayQueue<>(this.key.length);
        for (int i = 0; i < this.key.length; i++)
        {
            this.keyQueueE.enqueue(this.key[i]);
            this.keyQueueD.enqueue(this.key[i]);
        }
    }

    public String encode(String message)
    {
        StringBuilder encoded = new StringBuilder();

//        System.out.println("IN encode method - IMPLEMENT ME");

        for (int i = 0; i < message.length(); i++)
        {
            char currentCharacter = message.charAt(i);
            encoded.append((char)(currentCharacter + this.keyQueueE.getFront()));
            this.keyQueueE.enqueue(this.keyQueueE.dequeue());
        }
        return encoded.toString();
    }

    public String decode(String encoded)
    {
        StringBuilder decoded = new StringBuilder();

//        System.out.println("IN decode method - IMPLEMENT ME");

        for (int i = 0; i < encoded.length(); i++)
        {
            char currentCharacter = encoded.charAt(i);
            decoded.append((char)(currentCharacter - this.keyQueueD.getFront()));
            this.keyQueueD.enqueue(this.keyQueueD.dequeue());
        }
        return decoded.toString();
    }


    public static void main(String args[])
    {
        System.out.println("**************  TESTING THE CIPHER  **************\n");
        Cipher cipher = new Cipher(5, 12, -3, 8, -9, 4, 10);
        String encoded = cipher.encode("All programmers are playwrights and all computers are lousy actors.");
        System.out.println("--->The original message encoded is:");
        System.out.println(encoded);
        String decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

        encoded = cipher.encode("There is no elevator to success, You have to take the stairs...");
        System.out.println("\n--->The original message encoded is:");
        System.out.println(encoded);
        decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

        cipher = new Cipher(3, 1, 7, 4, 2, 5);
        encoded = cipher.encode("knowledge is power");
        System.out.println("\n--->The original message encoded is:");
        System.out.println(encoded);
        decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

        cipher = new Cipher(3, 1, 7, 4, 2, 5);
        encoded = cipher.encode("race car");
        System.out.println("\n--->The original message encoded is:");
        System.out.println(encoded);
        decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);
    }
} // end DropoutStack


