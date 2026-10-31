package library.app;

import java.util.ArrayList;

import library.model.LibraryItem;

public class Patron
{
    private ArrayList<LibraryItem> itemList;
    private String name;
    private String id;
    private int balance;

    public Patron(){
        itemList = new ArrayList<LibraryItem>();
    }

    public Patron(String myId, String myName){
        name = myName;
        id = myId;
        itemList = new ArrayList<LibraryItem>();
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getBalance(){
        return balance;
    }

    public void pay(int cents){
        balance -= cents;
    }

    public void checkOut(LibraryItem item, int dayI){
        if(itemList.size() < 3){
            itemList.add(item);
        }
    }

    public void returnItem(LibraryItem item){
        itemList.remove(item);
    } 

    public int getNumItems(){
        return itemList.size();
    }

    public String getItemStatus(){
        String status = "";

        for(LibraryItem item: itemList){
            status += "\n  " + item.getId() + " \"" + item.getTitle() + "\" due day " + item.getDueDay();
        }

        return status;
    }

    public boolean ifBook(String bookId){
        for(LibraryItem item: itemList){
            if(item.getId().equals(bookId)) return true;
        }
        return false;
    }
}
