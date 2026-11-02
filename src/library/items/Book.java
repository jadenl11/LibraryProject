package library.items;

import library.model.LibraryItem;

/** A book is a loanable item with a 21-day loan and 25-cent daily late fee. */
public class Book extends LibraryItem
{
    public Book(String id, String title)
    {
        super(id, title);
    }

    @Override
    protected int getLoanLength()
    {
        return 21;
    }

    @Override
    protected int getLateFeePerDay()
    {
        return 25;
    }

    @Override
    protected int getMaxLateFee()
    {
        return 0;
    }
}
