package library.items;

import library.model.LibraryItem;

/** A book is a loanable item with a 21-day loan and 25-cent daily late fee. */
public class Book extends LibraryItem
{
    private boolean isAvailable;
    public Book(String id, String title)
    {
        super(id, title);
        isAvailable = true;
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

    public void setAvailable(boolean available){
        isAvailable = available;
    }
}
