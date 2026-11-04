package library.app;

import java.util.ArrayList;
import java.util.List;

import library.items.Book;
import library.items.Dvd;
import library.items.Magazine;
import library.model.LibraryException;
import library.model.LibraryItem;
import library.model.Money;

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
    private List<Patron> patronList;
    private List<LibraryItem> itemList;
    private int day;
    private int commandCount;
    private int errorCount;

    public Library(){
        patronList = new ArrayList<Patron>();
        itemList = new ArrayList<LibraryItem>();
        day = 1;
        commandCount = 0;
        errorCount = 0;
    }
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
                errorCount++;
                commandCount++;
                throw new LibraryException("unknown command " + cmd + ".");
        }
    }

    /** Checks that the command has between min and max words (counting the command word). */
    private void needArgs(String[] t, int min, int max) throws LibraryException
    {
        if (t.length < min || t.length > max){
            commandCount++;
            errorCount++;
            throw new LibraryException("wrong number of arguments for " + t[0] + ".");
        }
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
        commandCount++;
        for(Patron patron : patronList){
            if(patron.getId().equals(id)){
                errorCount++;
                throw new LibraryException("patron " + id + " already exists.");
            }
        }
        
        patronList.add(new Patron(id, name));
        return "Added patron " + id + " (" + name + ").";
    }

    /** type is "BOOK", "DVD", or "MAGAZINE". */
    private String addItem(String type, String id, String title) throws LibraryException
    {
        commandCount++;

        for(LibraryItem item : itemList){
            if(item.getId().equals(id)){
                errorCount++;
                throw new LibraryException("item " + id + " already exists.");
            }
        }

        if(type.equals("BOOK"))
            itemList.add(new Book(id, title));
        else if(type.equals("DVD"))
            itemList.add(new Dvd(id, title));
        else if(type.equals("MAGAZINE"))
            itemList.add(new Magazine(id, title));
        return "Added " + type + " " + id + " \"" + title + "\".";
    }

    private String checkOut(String patronId, String itemId) throws LibraryException
    {
        commandCount++;

        Patron patron = findPatron(patronId);
        LibraryItem item = findItem(itemId);

        if (item.isCheckedOut() ==  true) {
            errorCount++;
            throw new LibraryException("\"" + item.getTitle() + "\" ("+ itemId + ") is already checked out.");
        }

        if (patron.getNumItems() > 2) {
            errorCount++;
            throw new LibraryException(patron.getName() + " already has 3 items checked out.");
        }

        patron.checkOut(item, day);
        item.setIsCheckedOut(true);
        item.setIsCheckedOutBy(patronId, day);
        return patron.getName() + " checked out " + "\"" + item.getTitle() + "\" (" + itemId + ")" + ", due day " + item.getDueDay() + ".";
    }

    private String returnItem(String itemId) throws LibraryException
    {
        commandCount++;
        LibraryItem item = findItem(itemId);

        if (item.isCheckedOut() ==  false) {
            errorCount++;
            throw new LibraryException("\"" + item.getTitle() + "\" ("+ itemId + ") is not checked out.");
        }

        Patron patron = findPatron(item.getCheckedOutBy());
        patron.returnItem(findItem(itemId));
        item.setIsCheckedOut(false);
        String late = (item.getDaysLate(day) == 0) ? "on time" : ", " + item.getDaysLate(day) + "day(s) late. Fee: " + Money.format(item.getLateFee(day));
        return patron.getName() + " returned \"" + item.getTitle() + "\" (" + itemId + ") " + late + "."; 
    }

    /** days arrives as text. It might not be a number. */
    private String advance(String days) throws LibraryException
    {
        commandCount++;
        try{
        int advanceAmount = Integer.parseInt(days);
        if(advanceAmount <= 0){
            errorCount++;
            throw new LibraryException("days must be a positive whole number.");
        }
        day += advanceAmount;
        return "Advanced " + days + " day(s). Today is day " + day + ".";
        }
        catch(NumberFormatException e){
            errorCount++;
            throw new LibraryException("days must be a positive whole number.");
        }
    }

    /** cents arrives as text. It might not be a number. */
    private String pay(String patronId, String cents) throws LibraryException
    {
        commandCount++;

        try{
        Patron patron = findPatron(patronId);

        if(patron.getBalance() < Integer.parseInt(cents)){
            errorCount++;
            throw new LibraryException(patron.getName() + " only owes " + Money.format(patron.getBalance()) + ".");
        }

        if(Integer.parseInt(cents) < 0){
            errorCount++;
            throw new LibraryException("amount must be a positive whole number of cents.");
        }

        patron.pay(Integer.parseInt(cents));
        return patron.getName() + " paid" + Money.format(Integer.parseInt(cents)) + ". Balance: $" + patron.getBalance();
        }
        
        catch(NumberFormatException e){
            errorCount++;
            throw new LibraryException("amount must be a positive whole number of cents.");
        }
    }

    private String status(String patronId) throws LibraryException
    {
        commandCount++;
        Patron patron = findPatron(patronId);
        return patron.getName() + " (" + patron.getId() + "):" + " owes " + Money.format(patron.getBalance()) + ", " 
            + patron.getNumItems() + " item(s) checked out" + patron.getItemStatus(day); 
    }

    private String report() throws LibraryException
    {
        commandCount++;
        String output = "";
        
        for(LibraryItem item : itemList){
            output += item.getId() + " " + item.getClass().getSimpleName().toUpperCase() + " \"" + item.getTitle() + "\""; 
            if(item.isCheckedOut()){
                output += " - checked out by " + item.getCheckedOutBy() + ", due day " + item.getDueDay();
            }
            else
                output += " - available";
            output += "\n";
        }

        return output + "Total fees owed: " + Money.format(totalFeesOwed());
    }

    public String finalReport(){
        return "Processed " + commandCount  + " commands (" + errorCount + " errors)."; 
    }

    private Patron findPatron(String patronId) throws LibraryException{
        for(Patron patron : patronList){
            if(patron.getId().equals(patronId))
                return patron;
        }

        errorCount++;
        throw new LibraryException("no such patron " + patronId + ".");
    }

    private LibraryItem findItem(String itemId) throws LibraryException{
        for(LibraryItem item : itemList){
            if(item.getId().equals(itemId))
                return item;
        }

        errorCount++;
        throw new LibraryException("no such item " + itemId + ".");
    }

    private int totalFeesOwed(){
        int total = 0;
        for(LibraryItem item : itemList)
            total += item.getLateFee(day);
        return total;
    }
}
