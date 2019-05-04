import sun.awt.image.ImageWatched;/** * A class of bags whose entries are stored in a chain of linked nodes. * The bag is never full. * * @author Robert Aroutiounian * @version 08/14/2015 */public class LinkedBag<T extends Comparable<? super T>> implements BagInterface<T>{    private Node<T> firstNode;       // reference to first node    private int numberOfEntries;    public LinkedBag()    {        this.firstNode = null;        this.numberOfEntries = 0;    } // end default constructor    /**     * Adds a new entry to this bag.     *     * @param newEntry the object to be added as a new entry     * @return true     */    public boolean add(T newEntry) // OutOfMemoryError possible    {        // add to beginning of chain:        Node<T> newNode = new Node<>(newEntry);        newNode.next = this.firstNode;  // make new node reference rest of chain                                        // (firstNode is null if chain is empty)        this.firstNode = newNode;       // new node is at beginning of chain        this.numberOfEntries++;        return true;    } // end add    /**     * Retrieves all entries that are in this bag.     *     * @return a newly allocated array of all the entries in the bag     */    public T[] toArray()    {        // the cast is safe because the new array contains null entries        @SuppressWarnings("unchecked")        T[] result = (T[]) new Object[this.numberOfEntries]; // unchecked cast        int index = 0;        Node<T> currentNode = this.firstNode;        while ((index < this.numberOfEntries) && (currentNode != null))        {            result[index] = currentNode.data;            index++;            currentNode = currentNode.next;        } // end while        return result;    } // end toArray    /**     * Sees whether this bag is empty.     *     * @return true if the bag is empty, or false if not     */    public boolean isEmpty()    {        return this.numberOfEntries == 0;    } // end isEmpty    /**     * Gets the number of entries currently in this bag.     *     * @return the integer number of entries currently in the bag     */    public int getCurrentSize()    {        return this.numberOfEntries;    } // end getCurrentSize    /**     * Counts the number of times a given entry appears in this bag.     *     * @param anEntry the entry to be counted     * @return the number of times anEntry appears in the bag     */    public int getFrequencyOf(T anEntry)    {        int frequency = 0;        int counter = 0;        Node<T> currentNode = this.firstNode;        while ((counter < this.numberOfEntries) && (currentNode != null))        {            if (anEntry.equals(currentNode.data))            {                frequency++;            } // end if            counter++;            currentNode = currentNode.next;        } // end while        return frequency;    } // end getFrequencyOf    /**     * Tests whether this bag contains a given entry.     *     * @param anEntry the entry to locate     * @return true if the bag contains anEntry, or false otherwise     */    public boolean contains(T anEntry)    {        return getReferenceTo(anEntry) != null;    } // end contains    // Locates a given entry within this bag.    // Returns a reference to the node containing the entry, if located,    // or null otherwise.    private Node<T> getReferenceTo(T anEntry)    {        boolean found = false;        Node<T> currentNode = this.firstNode;        while (!found && (currentNode != null))        {            if (anEntry.equals(currentNode.data))                found = true;            else                currentNode = currentNode.next;        } // end while        return currentNode;    } // end getReferenceTo    /**     * Removes all entries from this bag.     */    public void clear()    {        while (!isEmpty())            remove();    } // end clear    /**     * Removes one unspecified entry from this bag, if possible.     *     * @return either the removed entry, if the removal     * was successful, or null     */    public T remove()    {        T result = null;        if (this.firstNode != null)        {            result = this.firstNode.data;            this.firstNode = this.firstNode.next; // remove first node from chain            this.numberOfEntries--;        } // end if        return result;    } // end remove    /**     * Removes one occurrence of a given entry from this bag, if possible.     *     * @param anEntry the entry to be removed     * @return true if the removal was successful, or false otherwise     */    public boolean remove(T anEntry)    {        boolean result = false;        Node<T> nodeN = getReferenceTo(anEntry);        if (nodeN != null)        {            nodeN.data = this.firstNode.data; // replace located entry with entry in first node            this.firstNode = this.firstNode.next; // remove first node from chain            this.numberOfEntries--;            result = true;        } // end if        return result;    } // end remove    // ****** IMPLEMENT THE FOLLOWING METHODS ********    /**     * Displays all the elements in the bag     */    public void display()    {        // INT counter        // CREATE a new node class and set it to the first node        // WHILE counter is less than number of entries and current node is not null        //     PRINT current node        //     INCRIMENT counter        //     SET current node to next node        System.out.print("There are " + this.numberOfEntries + " element(s): ");        Node<T> currentNode = this.firstNode;        while ((currentNode != null))        {            System.out.print(currentNode.data + " ");            currentNode = currentNode.next;        }        System.out.println("");    } // end display    /**     * Checks if the given bag called other is the same as the bag     *     * @param otherBag the other bag to be compared with     * @return true both bags are the same     */    public boolean equals(BagInterface<T> otherBag)    {        // INT counter        // CREATE a new node class and set it to the first node        // WHILE counter is less than number of entries and current node is not null and same is true        //     IF data in other node is not the same as the data in the current node        //         same is flase        //     INCRIMENT counter        LinkedBag<T> other = (LinkedBag<T>) otherBag;        boolean same = true;        if (this.numberOfEntries == other.numberOfEntries)        {            Node<T> currentNode = this.firstNode;            Node<T> otherCurrentNode = other.firstNode;            while ((currentNode != null) && (otherCurrentNode != null) && (same))            {                if (!(otherCurrentNode.data.equals(currentNode.data)))                {                    same = false;                }                currentNode = currentNode.next;                otherCurrentNode = otherCurrentNode.next;            }        }        else        {            same = false;        }        return same;    }    /**     * Removes the largest element from the bag     *     * @return - null if the element was not found or the element     * <p/>     * NOTE: the method must traverse the data with a while loop; calls remove()     */    public T removeMax()    {        // INT counter        // CREATE a new node class and set it to the first node        // WHILE counter is less than number of entries and current node is not null        //     IF the data in current node is larger than largest        //         largest is set to current node data        //          remove current node        //     INCRIMENT counter        //     SET currnet node to next node        T largest = null; // return value        if (this.firstNode != null)        {            Node<T> currentNode = this.firstNode;            Node<T> maxNode = this.firstNode;            while (currentNode != null)            {                if (maxNode.data.compareTo(currentNode.data) < 0)                {                    maxNode = currentNode;                    largest = maxNode.data;                }                currentNode = currentNode.next;            }            remove(maxNode.data);        }        else        {            System.out.println("Bag is empty");        }        return largest;    } // end remove element    /**     * Removes every occurrence of a given entry from this bag.     * For efficiency it traverses the data and removes entries as it traverses the list     * without calling any other method     *     * @param anEntry the entry to be removed     */    public void removeEvery(T anEntry)    {        // INT counter        // CREATE a new node class and set it to the first node        // WHILE counter is less than number of entries and current node is not null        //     IF the data in currnet node is same as anEntry        //         remove node        //     INCRIMENT counter        //     SET current node to next node        //     create a previous node and have the previous point to where the current is pointing to        if (this.firstNode != null)        {            Node<T> currentNode = this.firstNode;            Node<T> previousNode = null;            while (currentNode != null)            {//                System.out.println("Beginning of while loop");                if (currentNode.data.equals(anEntry))                {//                    System.out.println("Beginning of first if");                    if (previousNode == null)                    {//                        System.out.println("Beginning of second if");//                        System.out.println("Removing first node");                        this.firstNode = currentNode.next;                        currentNode = this.firstNode;                    }                    else                    {//                        System.out.println("Beginning of else statement");                        previousNode.next = currentNode.next;//                        System.out.println("previous points to next current");                        currentNode = currentNode.next;//                        System.out.println("current is next current");                    }                    this.numberOfEntries--;                }                else                {                    previousNode = currentNode;                    currentNode = currentNode.next;                }            }        }        else        {            System.out.println("Bag is empty");        }        // use one loop only, change appropriate pointers, no calls to other methods    } // end removeEvery    /**     * Gets the smallest value in this bag.     *     * @returns a reference to the smallest object, or null if the bag is empty     */    public T getMin()    {        // INT counter        // CREATE a new node class and set it to the first node        // WHILE counter is less than number of entries and current node is not null        //     IF the data in current node is smaller than smallestValue        //         smallest is set to current node data        //     INCRIMENT counter        //     SET currnet node to next node                   T smallestValue = null;        if (this.firstNode != null)        {            Node<T> currentNode = this.firstNode;            Node<T> minNode = this.firstNode;            smallestValue = this.firstNode.data;            while (currentNode != null)            {                if (minNode.data.compareTo(currentNode.data) > 0)                {                    minNode = currentNode;                    smallestValue = minNode.data;                }                currentNode = currentNode.next;            }        }        else        {            System.out.println("Bag is empty");        }        return smallestValue;    } // end getMin    /**     * Creates a new bag that combines the contents of this bag and a     * second given bag without affecting the original two bags.     *     * @param otherBag the given bag     * @return a bag that is the union of the two bags     */    public BagInterface<T> union(BagInterface<T> otherBag)    {        // INT counter        // CREATE a new node class and set it to the first node        // WHILE counter is less than number of entries and current node is not null        //     ADD current node to union bag        //     INCRIMENT counter        //     SET current node to next node        // INT counter2        // CREATE another new node class and set it to other first node        // WHILE counter is less than number of entries in other and current2 node is not null        //     ADD current2 node to union bag        //     INCRIMENT counter2        //     SET current2 node to next node        LinkedBag<T> other = (LinkedBag<T>) otherBag;        LinkedBag<T> unionBag = new LinkedBag<>();        Node<T> currentNode = this.firstNode;        while (currentNode != null)        {            unionBag.add(currentNode.data);            currentNode = currentNode.next;        }        Node<T> otherCurrentNode = other.firstNode;        while (otherCurrentNode != null)        {            unionBag.add(otherCurrentNode.data);            otherCurrentNode = otherCurrentNode.next;        }        return unionBag;    } // end union    /**     * Creates a new bag that contains those objects that occur in both this     * bag and a second given bag without affecting the original two bags.     *     * @param otherBag the given bag     * @return a bag that is the intersection of the two bags     */    public BagInterface<T> intersection(BagInterface<T> otherBag)    {        // ADD the elements from other bag to copyOfOtherBag        // INT counter        // CREATE a new node class and set it to the this first node        // WHILE counter is less than number of entries and current node is not null        //     IF other bag contains the current node        //         ADD the current node to the intersectionBag        //     INCRIMENT counter        //     SET currnet node to next node        LinkedBag<T> other = (LinkedBag<T>) otherBag;        LinkedBag<T> intersectionBag = new LinkedBag<>();        LinkedBag<T> copyOfOtherBag = new LinkedBag<>();        Node<T> currentNode = this.firstNode;//        System.out.println("Running intersection");        if (this.firstNode != null)        {//            System.out.println("Beginning of first if");            Node<T> otherCurrentNode = other.firstNode;            while (otherCurrentNode != null)            {//                System.out.println("Beginning of first while");                copyOfOtherBag.add(otherCurrentNode.data);                otherCurrentNode = otherCurrentNode.next;            }            while (currentNode != null)            {//                System.out.println("Beginning of second while");                if (copyOfOtherBag.remove(currentNode.data))                {//                    System.out.println("Beginning of second if");                    intersectionBag.add(currentNode.data);                }                currentNode = currentNode.next;            }        }        else        {            System.out.println("Bag is empty");        }        // do not call contains, call remove(anEntry) instead        return intersectionBag;    } // end intersection    /**     * Creates a new bag of objects that would be left in this bag     * after removing those that also occur in a second given bag     * without affecting the original two bags.     *     * @param otherBag the given bag     * @return a bag that is the difference of the two bags     */    public BagInterface<T> difference(BagInterface<T> otherBag)    {        // ADD nodes from this bag to differenceBag        // INT counter        // CREATE a new node class and set it to the first node        // WHILE counter is less than number of entries and current node is not         //     IF the node exists in the other bag        //         REMOVE the current node from differenceBag        //     INCRIMENT counter        //     SET currnet node to next node        LinkedBag<T> other = (LinkedBag<T>) otherBag;        LinkedBag<T> differenceBag = new LinkedBag<>();        Node<T> currentNode = this.firstNode;        Node<T> otherCurrentNode = other.firstNode;        if (currentNode != null)        {            while (currentNode != null)            {                differenceBag.add(currentNode.data);                currentNode = currentNode.next;            }            while (otherCurrentNode != null)            {                differenceBag.remove(otherCurrentNode.data);                otherCurrentNode = otherCurrentNode.next;            }        }        else        {            System.out.println("Bag is empty");        }        // do not call contains, call remove(anEntry) instead        return differenceBag;    } // end difference    public void moveFirstToEnd()    {        // INT counter        // CREATE a new node class and set it to the first node        // SET the first node to next node        // ADD current node//        System.out.println(this.firstNode.data);//        System.out.println(this.firstNode.next.data);        if (this.firstNode != null)        {            Node<T> moveNode = this.firstNode;//            System.out.println("first of move is: " + moveNode.data);            Node<T> lastNode = this.firstNode;//            System.out.println("first of last is: " + lastNode.data);            while (lastNode.next != null)            {                lastNode = lastNode.next;            }//            System.out.println("last in node is: " + lastNode.data);            lastNode.next = moveNode;            this.firstNode = moveNode.next;            moveNode.next = null;        }        else        {            System.out.println("The bag is empty");        }        // do not create a new node, just change appropriate pointers    } // end moveToEnd    /**     * Replaces an entry in this bag with a given object.     *     * @param replacement the given object     * @return the original entry in the bag that was replaced     */    public T replace(T replacement)    {        // SET the first node to the replacement        T replacedEntry = null;        Node<T> currentNode = this.firstNode;        replacedEntry = currentNode.data;        currentNode.data = replacement;        // do not create a new node, just change the data        return replacedEntry;    } // end replace    /**     * Checks if all the elements of the given bag are also included in the other bag     *     * @param otherBag bag to check     * @return returns true if all the elements of the given bag are also included in the other bag     */    public boolean isSubset(BagInterface<T> otherBag)    {        // take the difference of the two bags        // IF the differnce is empty        //     RETURN true        // ELSE         //     RETURN false        LinkedBag<T> other = (LinkedBag<T>) otherBag;        LinkedBag<T> test = (LinkedBag<T>) this.difference(other);        if (test.isEmpty())        {            return true;        }        else        {            return false;        }        // utilize difference method    }    private class Node<S>    {        private S data; // entry in bag        private Node<S> next; // link to next node        private Node(S dataPortion)        {            this(dataPortion, null);        } // end constructor        private Node(S dataPortion, Node<S> nextNode)        {            this.data = dataPortion;            this.next = nextNode;        } // end constructor    } // end Node    public static void main(String[] args)    {        System.out.println("RUNNING TEST CASES");        BagInterface<String> bag1 = new LinkedBag<>();        BagInterface<String> bag2 = new LinkedBag<>();        BagInterface<String> testBag = new LinkedBag<>();        bag1.add("A");        bag1.add("B");        bag1.add("A");        bag1.add("C");        bag1.add("B");        // testing display        System.out.println("\n***Testing display method***");        System.out.println("bag1 is ");        bag1.display();        System.out.println("bag2 is ");        bag2.display();        System.out.println("After removing the first element " + bag1.remove() + " from bag1, it contains");        bag1.display();        // testing equals        System.out.println("\n***Testing equals method***");        System.out.println("Are bag1 and bag2 equal? --> " + (bag1.equals(bag2) ? "YES" : "NO"));        System.out.println("Are bag2 and bag1 equal? --> " + (bag2.equals(bag1) ? "YES" : "NO"));        bag2.add("A");        bag2.add("C");        bag2.add("A");        bag2.add("B");        bag2.add("X");        System.out.println("bag2:");        bag2.display();        System.out.println("Are bag1 and bag2 equal? --> " + (bag1.equals(bag2) ? "YES" : "NO"));        System.out.println("Removed " + bag2.remove() + " from bag2.");        bag2.display();        System.out.println("Are bag1 and bag2 equal now? --> " + (bag1.equals(bag2) ? "YES" : "NO"));        LinkedBag<String> bagCopyOfBag1 = new LinkedBag<String>();        bagCopyOfBag1.add("A");        bagCopyOfBag1.add("B");        bagCopyOfBag1.add("A");        bagCopyOfBag1.add("C");        System.out.println("Created bagCopyOfBag1:");        bagCopyOfBag1.display();        System.out.println("Are bag1 and bagCopyOfBag1 equal? --> " + (bag1.equals(bagCopyOfBag1) ? "YES" : "NO"));        bag1.clear();        bag1.add("A");        bag1.add("A");        bag1.add("B");        bag1.add("X");        bag1.add("A");        bag1.add("C");        bag1.add("A");        bag2.clear();        bag2.add("A");        bag2.add("B");        bag2.add("B");        bag2.add("A");        bag2.add("C");        bag2.add("C");        bag2.add("D");        // testing smallest        System.out.println("\n***Testing smallest method***");        System.out.print("bag1: ");        bag1.display();        System.out.print("bag2: ");        bag2.display();        System.out.println("The smallest item in bag1 is: " + bag1.getMin());        System.out.println("The smallest item in bag2 is: " + bag2.getMin());        System.out.println("\n***Testing union, removeMax, intersection, difference and subset methods***");        System.out.print("bag1: ");        bag1.display();        System.out.print("bag2: ");        bag2.display();        // testing union        System.out.println("\n***Testing union method***");        BagInterface<String> everything = bag1.union(bag2);        System.out.println("The union of bag1 and bag2 is ");        everything.display();        // testing removeMax        System.out.println("\n***Testing removeMax method***");        String largest = everything.removeMax();        System.out.println("Removed the largest element \"" + largest + "\" from the union bag; the current content is:");        everything.display();        everything.clear();        largest = everything.removeMax();        if (largest == null)            System.out.println("The bag is empty and removeMax returned null - CORRECT");        else            System.out.println("The bag is empty bur removeMax returned did not return null - INCORRECT");        // testing intersection        System.out.println("\n***Testing intersection method***");        BagInterface<String> commonItems = bag1.intersection(bag2);        System.out.println("The intersection of bag1 and bag2 is ");        commonItems.display();        // testing difference        System.out.println("\n***Testing difference method***");        BagInterface<String> leftOver = bag1.difference(bag2);        System.out.println("The difference of bag1 and bag2 is ");        leftOver.display();        leftOver = bag2.difference(bag1);        System.out.println("The difference of bag2 and bag1 is ");        leftOver.display();        // testing subset        System.out.println("\n***Testing subset method***");        System.out.println("Is bag1 a subset of bag1 ? --> " + (bag1.isSubset(bag1) ? "YES" : "NO"));        System.out.println("Is bag1 a subset of bag2 ? --> " + (bag1.isSubset(bag2) ? "YES" : "NO"));        LinkedBag<String> emptyBag = new LinkedBag<>();        System.out.println("Is an empty bag a subset of bag2 ? --> " + (emptyBag.isSubset(bag2) ? "YES" : "NO"));        System.out.println("Is bag2 a subset of an empty bag ? --> " + (bag2.isSubset(emptyBag) ? "YES" : "NO"));        LinkedBag<String> bag3 = new LinkedBag<>();        LinkedBag<String> bag4 = new LinkedBag<>();        bag3.add("A");        bag3.add("B");        bag3.add("C");        System.out.println("Created bag3:");        bag3.display();        bag4.add("B");        bag4.add("C");        bag4.add("A");        System.out.println("Created bag4:");        bag4.display();        System.out.println("Is bag3 a subset of bag4 ? --> " + (bag3.isSubset(bag4) ? "YES" : "NO"));        bag4.add("Z");        System.out.println("Is bag3 a subset of bag4 after adding \"Z\" to it ? --> " + (bag3.isSubset(bag4) ? "YES" : "NO"));        System.out.println("Is bag4 a subset of bag3 ? --> " + (bag4.isSubset(bag3) ? "YES" : "NO"));        System.out.println("Adding  \"Z\" to bag3 twice");        bag3.add("Z");        bag3.add("Z");        System.out.println("Bag3 is:");        bag3.display();        System.out.println("Bag4 is:");        bag4.display();        System.out.println("Is bag3 a subset of bag4 ? --> " + (bag3.isSubset(bag4) ? "YES" : "NO"));        bag1.clear();        bag1.add("A");        bag1.add("A");        bag1.add("B");        bag1.add("X");        bag1.add("A");        bag1.add("C");        bag1.add("A");        // testing replace        System.out.println("\n***Testing replace method***");        System.out.println("Bag1 contains:");        bag1.display();        System.out.println("Replacing an element with \"X\"");        bag1.replace("X");        System.out.println("Now bag1 contains:");        bag1.display();        // testing removeEvery        System.out.println("\n***Testing removeEvery method***");        System.out.println("Bag1 contains:");        bag1.display();        System.out.println("Removing all \"Z\"");        bag1.removeEvery("Z");        System.out.println("After removing all \"Z\" bag1 contains:");        bag1.display();        System.out.println("Removing all \"X\"");        bag1.removeEvery("X");        System.out.println("After removing all \"X\" bag1 contains:");        bag1.display();        System.out.println("After adding two \"A\" bag1 contains:");        bag1.add("A");        bag1.add("A");        bag1.display();        System.out.println("Removing all \"A\"");        bag1.removeEvery("A");        System.out.println("After removing all \"A\" bag1 contains:");        bag1.display();        System.out.println("Removing all \"B\"");        bag1.removeEvery("B");        System.out.println("After removing all \"B\" bag1 contains:");        bag1.display();        System.out.println("\n*** TESTING moveFirstToEnd ***");        System.out.println("Cycling list 1 once");        testBag.clear();        testBag.add("C");        testBag.add("B");        testBag.add("A");        System.out.println("List before:");        testBag.display();        testBag.moveFirstToEnd();        System.out.println("List after:");        testBag.display();        System.out.println();        System.out.println("Cycling list 3 three times");        testBag.clear();        testBag.add("B");        testBag.add("C");        testBag.add("A");        System.out.println("List before:");        testBag.display();        testBag.moveFirstToEnd();        testBag.moveFirstToEnd();        testBag.moveFirstToEnd();        System.out.println("List after:");        testBag.display();        System.out.println();        System.out.println("Cycling list of length 0");        testBag.clear();        System.out.println("List before:");        testBag.display();        testBag.moveFirstToEnd();        System.out.println("List after:");        testBag.display();        System.out.println();        System.out.println("Cycling list of length 1");        testBag.clear();        testBag.add("B");        System.out.println("List before:");        testBag.display();        testBag.moveFirstToEnd();        System.out.println("List after:");        testBag.display();        System.out.println();        System.out.println("Cycling list of length 2");        testBag.clear();        testBag.add("A");        testBag.add("B");        System.out.println("List before:");        testBag.display();        testBag.moveFirstToEnd();        System.out.println("List after:");        testBag.display();        System.out.println();    } // end main} // end LinkedBag