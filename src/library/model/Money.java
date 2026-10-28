package library.model;

public class Money
{
    private Money() { }

    /** 1075 becomes "$10.75" */
    public static String format(int cents)
    {
        return String.format("$%d.%02d", cents / 100, cents % 100);
    }
}
