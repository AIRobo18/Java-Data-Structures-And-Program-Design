import java.util.Random;

/**
 * Created by robert.aroutiounian3 on 8/27/15.
 */
public class PiggyBank
{
    private BagInterface<Money> piggyBank;
    private int capacity;

    public PiggyBank(int newSize, int newCapacity)
    {
        this.capacity = newCapacity;
        this.piggyBank = new ResizableArrayBag<Money>(newSize);
    }

    // sees if there is any room left in the piggy bank and adds a new currency
    public void add(Money newMoney)
    {
        boolean addMore;
        do
        {
            addMore = false;
            try
            {
                if (this.piggyBank.getCurrentSize() < this.capacity)
                {
                    this.piggyBank.add(newMoney);
                }
                else
                {
                    throw new PiggyBankFullException("Piggy bank is full");
                }
            } catch (PiggyBankFullException pbfe)
            {
                System.out.println(pbfe.getMessage());
                addMore = true;
            }
        }
        while (addMore);
    }

    // shakes the piggy bank until one random currnecy falls out
    // @return randomly removes one currency from the piggy bank
    public Money remove()
    {
        return this.piggyBank.remove();
    }

    // checks to see if piggy bank is empty
    // @return a boolean stating where piggy bank is empty or not
    public boolean isEmpty()
    {
        if (this.piggyBank.getCurrentSize() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // checks to see if there is any more room in the piggy bank to add currency
    // @return a boolean stating whether there is anymore room for additional currency in the piggy bank
    public boolean isFull()
    {
        if (this.piggyBank.getCurrentSize() == this.capacity)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // check to see how many currencies are in the piggy bank
    // @return an int value representing the number of object in the piggy bank
    public int getCapacity()
    {
        return this.piggyBank.getCurrentSize();
    }

    // empties out the piggy bank one by one, tosses it, and sees if it landed on heads or tails
    // @return the number of objects that landed on heads
    public int emptyAndCountHeads()
    {
        int count = 0;
        Money random = this.piggyBank.remove();

        while (this.piggyBank.getCurrentSize() > 0);
        {
            random.toss();
            if (random.isHeads())
            {
                count ++;
            }
        }

        return count;
    }

    // states how many currencies landed on heads and how much their overall total is
    // @return string telling the user the number of heads landed and their overall sum
    public String toString()
    {
        return this.piggyBank.remove().toString() + " landed " + this.piggyBank.remove().getValue();
    }
}
