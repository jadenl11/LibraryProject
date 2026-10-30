package library.items;

import library.model.LibraryItem;

/** A magazine has a 14-day loan and no late fee. */
public class Magazine extends LibraryItem
{
    private boolean isAvailable;
    public Magazine(String id, String title)
    {
        super(id, title);
        isAvailable = true;
    }

    @Override
    protected int getLoanLength()
    {
        return 14;
    }

    @Override
    protected int getLateFeePerDay()
    {
        return 0;
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
