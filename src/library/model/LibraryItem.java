package library.model;


public abstract class LibraryItem
{
    private final String id;
    private final String title;
    private boolean checkedOut;
    private String checkedOutBy;
    private int dueDay;

    protected LibraryItem(String id, String title)
    {
        this.id = id;
        this.title = title;
        this.checkedOut = false;
        this.checkedOutBy = null;
        this.dueDay = 0;
    }

    public void setCheckedDate(int dayCheckedOut){

    }

    protected abstract int getLoanLength();

    protected abstract int getLateFeePerDay();

    protected abstract int getMaxLateFee();

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public boolean isCheckedOut()
    {
        return checkedOut;
    }

    public void setIsCheckedOut(boolean checked){
        checkedOut = checked;
    }

    public String getCheckedOutBy()
    {
        return checkedOutBy;
    }

    public void setIsCheckedOutBy(String patronId)
    {
        checkedOutBy = patronId;
    }

    public int getDueDay()
    {
        return dueDay;
    }


    public void checkIn()
    {
        checkedOut = false;
        checkedOutBy = null;
        dueDay = 0;
    }

    public int getDaysLate(int today)
    {
        if (!checkedOut)
            return 0;

        int lateDays = today - dueDay;
        if(lateDays<0){
            return lateDays*-1;
        }
        else{
            return 0;
        }
    }

    public int getLateFee(int today)
    {
        int lateDays = getDaysLate(today);
        if (lateDays <= 0)
            return 0;

        int fee = lateDays * getLateFeePerDay();
        int maxFee = getMaxLateFee();
        if (maxFee > 0)
            return Math.min(fee, maxFee);
        return fee;
    }
}
