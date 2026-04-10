package org.phuchoang2005.ecommerce.exceptions.validation;

import org.phuchoang2005.ecommerce.enums.HttpStatusEnum;
import org.phuchoang2005.ecommerce.exceptions.BaseException;

public class ValidationException extends BaseException {
    public ValidationException(String message){
        super(
                HttpStatusEnum.BAD_REQUEST.code(),
                HttpStatusEnum.BAD_REQUEST.message(),
                message
        );
    }
}
