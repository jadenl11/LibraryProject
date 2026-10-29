package library.exception;

import library.model.LibraryException;
import library.model.Money;

public class PatronItemLimit extends LibraryException{

    public PatronItemLimit(String name){
        super(name + " already has 3 items checked out.");
    }
}
