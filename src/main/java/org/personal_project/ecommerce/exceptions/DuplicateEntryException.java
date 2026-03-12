package org.personal_project.ecommerce.exceptions;


public class DuplicateEntryException extends DatabaseException{
    public DuplicateEntryException(String message){
        super(message);
    }
}
