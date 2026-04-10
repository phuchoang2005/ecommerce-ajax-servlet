package org.phuchoang2005.ecommerce.exceptions.authentication;

import org.phuchoang2005.ecommerce.enums.HttpStatusEnum;
import org.phuchoang2005.ecommerce.exceptions.BaseException;

public class AuthenticationException extends BaseException {
    public AuthenticationException(String message){
        super(
                HttpStatusEnum.UNAUTHORIZED.code(),
                HttpStatusEnum.UNAUTHORIZED.message(),
                message
        );
    }
}
