package library.items;

import library.model.LibraryItem;

/** A DVD is a loanable item with a 7-day loan and a capped late fee. */
public class Dvd extends LibraryItem
{
    public Dvd(String id, String title)
    {
        super(id, title);
    }

    @Override
    protected int getLoanLength()
    {
        return 7;
    }

    @Override
    protected int getLateFeePerDay()
    {
        return 100;
    }

    @Override
    protected int getMaxLateFee()
    {
        return 1000;
    }
}
