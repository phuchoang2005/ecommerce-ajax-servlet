package org.phuchoang2005.ecommerce.exceptions.database;

import org.phuchoang2005.ecommerce.enums.HttpStatusEnum;
import org.phuchoang2005.ecommerce.exceptions.BaseException;

public class DatabaseException extends BaseException {
    public DatabaseException(String message){
        super(
                HttpStatusEnum.INTERNAL_ERROR.code(),
                HttpStatusEnum.INTERNAL_ERROR.message(),
                message
        );
    }
}
