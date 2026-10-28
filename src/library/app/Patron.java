package library.app;

/**
 * TODO (Partner B): a person who borrows items. You decide what a Patron
 * knows and what it can do. (You may also change or delete this file.)
 */
public class Patron
{
    private ArrayList<E> itemList;
    private String name;
    private double balance;
    private double owe;

    public Patron(){
        itemlist = new ArrayList<LibraryItem>();
    }

    public void addItem(LibraryItem item){
        if(itemList.size() < 3){
            itemList.add(item);
        }
    }

    public void returnItem(LibraryItem item){
        balance -= item.getLateFee();
        itemList.remove(item);
    }
}
