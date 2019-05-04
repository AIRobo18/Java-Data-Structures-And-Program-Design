/**
 * A class that implements a stack empty exception.
 *
 * @author Anna Bieszczad
 * @version 09/14/2015
 */
public class InsufficientNumberOfElementsOnStackException extends RuntimeException
{
    public InsufficientNumberOfElementsOnStackException(String reason)
    {
        super(reason);
    }
}
