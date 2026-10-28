package library.model;

/**
 * PROVIDED: the base type for every problem your program reports to the user.
 *
 * You write the more specific subclasses (see the design rules in README.md)
 * and decide when each one is thrown.
 */
public class LibraryException extends Exception
{
    public LibraryException(String message)
    {
        super(message);
    }
}
