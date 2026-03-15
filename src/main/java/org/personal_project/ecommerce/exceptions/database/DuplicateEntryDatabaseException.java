package org.personal_project.ecommerce.exceptions.database;


import org.personal_project.ecommerce.enums.HttpStatusEnum;

public class DuplicateEntryDatabaseException extends DatabaseException{
    private final int status;
    private final String error;
    public DuplicateEntryDatabaseException(String message){
        super(message);
        this.status = HttpStatusEnum.DUPLICATE_ENTRY.code();
        this.error = HttpStatusEnum.DUPLICATE_ENTRY.message();
    }
}
