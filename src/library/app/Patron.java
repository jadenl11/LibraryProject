package library.app;

import java.util.ArrayList;

import library.model.LibraryItem;

public class Patron
{
    private ArrayList<LibraryItem> itemList;
    private String name;
    private String id;
    private double balance;

    public Patron(){
        itemlist = new ArrayList<LibraryItem>();
    }

    public Patron(String myName, String myId, int cents, int day){
        name = myName;
        id = myId;
        balance = cents;
        dayCount = day;
        itemlist = new ArrayList<LibraryItem>();
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

    public int itemCount(){
        return itemList.size();
    }

    public int getLateFee(){
        if (balance > 0) {
            return 0;
        }
        return balance;
    }

    public void checkOut(LibraryItem item, int dayI){
        if(itemCount() < 3){
            itemList.add(item);
        }
    }

    public void returnItem(LibraryItem item){
        itemList.remove(item);
    }

    public void pay(int cents){
        balance += cents;
    } 
}
