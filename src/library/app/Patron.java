package library.app;

import java.util.ArrayList;

import library.model.LibraryItem;

public class Patron
{
    private ArrayList<LibraryItem> itemList;
    private String name;
    private String id;
    private double balance;
    private double owe;

    public Patron(){
        balance = 0;
        itemList = new ArrayList<LibraryItem>();
    }

    public Patron(String myName, int cents){
        name = myName;
        balance = cents;
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

    public void checkOut(LibraryItem item, int dayI){
        if(itemList.size() < 3){
            itemList.add(item);
        }
    }

    public void returnItem(LibraryItem item){
        itemList.remove(item);
    }

    
}
