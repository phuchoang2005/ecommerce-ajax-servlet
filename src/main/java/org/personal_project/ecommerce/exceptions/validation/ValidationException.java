package org.personal_project.ecommerce.exceptions.validation;

import org.personal_project.ecommerce.enums.HttpStatusEnum;
import org.personal_project.ecommerce.exceptions.BaseException;

public class ValidationException extends BaseException {
    public ValidationException(String message){
        super(
                HttpStatusEnum.BAD_REQUEST.code(),
                HttpStatusEnum.BAD_REQUEST.message(),
                message
        );
    }
}
