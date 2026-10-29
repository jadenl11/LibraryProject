package library.exception;

import library.model.LibraryException;

public class PatronIdNotFoundException extends LibraryException{

    public PatronIdNotFoundException(String id){
        super("no such patron " + id);
    }
}
