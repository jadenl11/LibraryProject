package library.app;

import java.util.Scanner;

/**
 * TODO: read the script file named in args[0], run each command,
 * and print the results. See "Main" in README.md.
 * Do NOT call System.exit.
 */
public class Main
{
    public static String execute(String line) throws LibraryException
    {
        String[] t = line.trim().split("\\s+");
        String cmd = t[0];
        switch (cmd)
        {
            case "PATRON":
                needArgs(t, 3, 3);
                return addPatron(t[1], t[2]);
            case "BOOK":
            case "DVD":
            case "MAGAZINE":
                needArgs(t, 3, Integer.MAX_VALUE);
                return addItem(cmd, t[1], joinFrom(t, 2));
            case "CHECKOUT":
                needArgs(t, 3, 3);
                return checkOut(t[1], t[2]);
            case "RETURN":
                needArgs(t, 2, 2);
                return returnItem(t[1]);
            case "ADVANCE":
                needArgs(t, 2, 2);
                return advance(t[1]);
            case "PAY":
                needArgs(t, 3, 3);
                return pay(t[1], t[2]);
            case "STATUS":
                needArgs(t, 2, 2);
                return status(t[1]);
            case "REPORT":
                needArgs(t, 1, 1);
                return report();
            default:
                // Optional: replace this with a more specific exception of your own.
                throw new LibraryException("unknown command " + cmd + ".");
        }
    }

    /** Checks that the command has between min and max words (counting the command word). */
    private static void needArgs(String[] t, int min, int max) throws LibraryException
    {
        // Optional: replace this with a more specific exception of your own.
        if (t.length < min || t.length > max)
            throw new LibraryException("wrong number of arguments for " + t[0] + ".");
    }

    /** Joins the words from position start to the end with single spaces. */
    private static String joinFrom(String[] t, int start)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < t.length; i++)
        {
            if (i > start)
                sb.append(" ");
            sb.append(t[i]);
        }
        return sb.toString();
    }


    public static void main(String[] args)
    {
        Scanner scanner = new Scanner("01_basic.txt");

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            execute(line);
        }
    }
}
