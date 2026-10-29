package library.exception;

import library.model.LibraryException;
import library.model.Money;

public class PatronOweLimitException extends LibraryException{

    public PatronOweLimitException(String name, int owe){
        super(name + " owes " + Money.format(owe) + " and cannot check out items.");
    }
}
