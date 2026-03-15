package org.personal_project.ecommerce.exceptions.authentication;

import org.personal_project.ecommerce.enums.HttpStatusEnum;
import org.personal_project.ecommerce.exceptions.BaseException;

public class AuthenticationException extends BaseException {
    public AuthenticationException(String message){
        super(
                HttpStatusEnum.UNAUTHORIZED.code(),
                HttpStatusEnum.UNAUTHORIZED.message(),
                message
        );
    }
}
