package library.app;

import java.util.ArrayList;
import java.util.List;

import library.items.Book;
import library.items.Dvd;
import library.model.LibraryException;
import library.model.LibraryItem;

/**
 * The library itself. It carries out one command at a time.
 *
 * PROVIDED: execute(), needArgs(), and joinFrom() read a command line and
 * call the right method below. You do not need to change them.
 *
 * YOUR JOB: write the command methods (addPatron, addItem, checkOut, ...)
 * and decide what the Library needs to remember. Each method returns the
 * text to print, or throws an exception when a rule is broken.
 */
public class Library
{
    List<Patron> patronList = new ArrayList<Patron>();
    List<LibraryItem> itemList = new ArrayList<LibraryItem>();
    // TODO: what does the Library need to remember? (Fields must be private.)

    /* ================= PROVIDED: command reading ================= */

    /** Runs one command line and returns the text to print. */
    public String execute(String line) throws LibraryException
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
    private void needArgs(String[] t, int min, int max) throws LibraryException
    {
        // Optional: replace this with a more specific exception of your own.
        if (t.length < min || t.length > max)
            throw new LibraryException("wrong number of arguments for " + t[0] + ".");
    }

    /** Joins the words from position start to the end with single spaces. */
    private String joinFrom(String[] t, int start)
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

    /* ================= YOUR JOB: the commands ================= */

    // For each method: check the rules in the order given in README.md
    // (section 4), do the work, and return the line to print.

    private String addPatron(String id, String name) throws LibraryException
    {
        // TODO
            patronList.add(new Patron(id, name));
        throw new LibraryException("PATRON is not written yet.");
    }

    /** type is "BOOK", "DVD", or "MAGAZINE". */
    private String addItem(String type, String id, String title) throws LibraryException
    {
        // TODO (this is the one place Library may look at the type to create the right item)
        if(type.equals("Book"))
            itemList.add(new Book(id, title));
        else if(type.equals("Dvd"))
            itemList.add(new Dvd(id, title));
        else if(type.equals("Magazine"))
            itemList.add(new Dvd(id, title));
        throw new LibraryException(type + " is not written yet.");
    }

    private String checkOut(String patronId, String itemId) throws LibraryException
    {
        for(Patron patron : patronList){
            if(patron.getId().equals(patronId)){
                for(LibraryItem item : itemList){
                    if(item.getId().equals(itemId)){
                        item.setAvailable(false);
                        patron.checkOut(itemId);
                    }
                }
            }
        }
        // TODO
        throw new LibraryException("CHECKOUT is not written yet.");
    }

    private String returnItem(String itemId) throws LibraryException
    {
        for(LibratyItem item : itemList){
            if(item.getId().equals(itemId))
                item.setAvailable(true);
        }
        throw new LibraryException("RETURN is not written yet.");
    }

    /** days arrives as text. It might not be a number. */
    private String advance(String days) throws LibraryException
    {
        // TODO
        for(Patron patron : patronList){
            patron.dayCount(days);
        }
        throw new LibraryException("ADVANCE is not written yet.");
    }

    /** cents arrives as text. It might not be a number. */
    private String pay(String patronId, String cents) throws LibraryException
    {
        for(Patron patron : patronList){
            if(patron.getId().equals(patronId)){
                patron.pay(cents);
                return patron.getName() + " has payed " + cents;
            }
        }
        throw new LibraryException("PAY is not written yet.");
    }

    private String status(String patronId) throws LibraryException
    {
        for(Patron patron : patronList){
            if(patron.getId().equals(patronId)){
                patron.pay(cents);
                return patron.getName() + " has payed " + cents;
            }
        }
        throw new LibraryException("STATUS is not written yet.");
    }

    private String report() throws LibraryException
    {
        // TODO
        throw new LibraryException("REPORT is not written yet.");
    }
}
