package org.personal_project.ecommerce.exceptions.database;

import org.personal_project.ecommerce.enums.HttpStatusEnum;
import org.personal_project.ecommerce.exceptions.BaseException;

public class DatabaseException extends BaseException {
    public DatabaseException(String message){
        super(
                HttpStatusEnum.INTERNAL_ERROR.code(),
                HttpStatusEnum.INTERNAL_ERROR.message(),
                message
        );
    }
}
