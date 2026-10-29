package library.exception;

import library.model.LibraryException;

public class PatronIdAlreadyUsedException extends LibraryException{

    public PatronIdAlreadyUsedException(String id){
        super("patron " + id + " already exists." );
    }
}
