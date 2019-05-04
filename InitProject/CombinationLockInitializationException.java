/**
 * CombinationLockInitializationException - a runtime exception that signals that the counter could
 * not be created.
 *
 * @author Anna Bieszczad
 * @version 08/14/2015
 */
@SuppressWarnings("serial")
public class CombinationLockInitializationException extends RuntimeException
{
    public CombinationLockInitializationException(String errorMessage)
    {
        super(errorMessage);
    }
}
