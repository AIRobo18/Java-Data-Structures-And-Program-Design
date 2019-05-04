/**
 * @version 10/9/15.
 */
public class CircularArrayQueue<T> implements QueueInterface<T>
{
    private T[] queue; // Circular array of queue entries and one unused location
    private int frontIndex; // Index of front entry
    private int backIndex;  // Index of back entry
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 3;
    private static final int MAX_CAPACITY = 10000;

    public CircularArrayQueue()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    public CircularArrayQueue(int initialCapacity)
    {
        checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        this.queue = tempQueue;
        this.frontIndex = 0;
        this.backIndex = initialCapacity;
        this.initialized = true;
    } // end constructor

    public void enqueue(T newEntry)
    {
        //System.out.println("enqueue(" + newEntry + ")");               // ***TESTING
        checkInitialization();
        ensureCapacity();
        this.backIndex = (this.backIndex + 1) % this.queue.length; // Index of location after current back of queue
        this.queue[this.backIndex] = newEntry;
        //System.out.println("queue[" + backIndex + "] = " + newEntry);  // ***TESTING
    } // end enqueue

    public T getFront()
    {
        checkInitialization();
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return this.queue[this.frontIndex];
    } // end getFront

    public T dequeue()
    {
        checkInitialization();
        if (isEmpty())
            throw new EmptyQueueException();
        else
        {
            T front = this.queue[this.frontIndex];
            this.queue[this.frontIndex] = null;
            this.frontIndex = (this.frontIndex + 1) % this.queue.length; // Index of new front of queue
            return front;
        }
    } // end dequeue

    public boolean isEmpty()
    {
        return this.frontIndex == ((this.backIndex + 1) % this.queue.length);
    } // end isEmpty

    public void clear()
    {
        checkInitialization();
        if (!isEmpty())
        { // deallocates only the used portion
            for (int index = this.frontIndex; index != this.backIndex; index = (index + 1) % this.queue.length)
            {
                this.queue[index] = null;
            }

            this.queue[this.backIndex] = null;
        }

        this.frontIndex = 0;
        this.backIndex = this.queue.length - 1;
    } // end clear


    // Throws an exception if this object is not initialized.
    private void checkInitialization()
    {
        if (!this.initialized)
            throw new SecurityException("CircularArrayQueue object is not initialized properly.");
    } // end checkInitialization

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a queue " +
                    "whose capacity exceeds " +
                    "allowed maximum.");
    } // end checkCapacity

    // Doubles the size of the array queue if it is full.
    // Precondition: checkInitialization has been called.
    private void ensureCapacity()
    {
        if (this.frontIndex == ((this.backIndex + 2) % this.queue.length)) // If array is full,
        {                                                   // double size of array
            //System.out.println("Doubling Array Size");                              // ***TESTING
            T[] oldQueue = this.queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize - 1); // Queue capacity is 1 fewer than array length

            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            this.queue = tempQueue;

            // Number of queue entries = oldSize - 1; index of last entry = oldSize - 2
            for (int index = 0; index < oldSize - 1; index++)
            {
                this.queue[index] = oldQueue[this.frontIndex];
                //System.out.println("queue[" + index + "] = " + oldQueue[frontIndex]); // ***TESTING
                this.frontIndex = (this.frontIndex + 1) % oldSize;
            } // end for

            this.frontIndex = 0;
            this.backIndex = oldSize - 2;
           //System.out.println("End ensureCapacity(): newSize = " + newSize);        // ***TESTING
        }
    } // end ensureCapacity
}
