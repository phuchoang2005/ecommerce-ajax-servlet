package org.personal_project.ecommerce.enums;

public enum HttpStatusEnum {

    SUCCESS(200, "Success"),
    CREATED(201, "Created"),

    BAD_REQUEST(400, "Bad request"),
    UNAUTHORIZED(401, "Unauthorized"),
    DUPLICATE_ENTRY(409, "Duplicate"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Resource not found"),

    INTERNAL_ERROR(500, "Internal server error"),

    BAD_GATEWAY(502, "Bad Gateway");

    private final int code;
    private final String defaultMessage;

    HttpStatusEnum(int code, String message){
        this.code = code;
        this.defaultMessage = message;
    }

    public int code(){
        return code;
    }

    public String message(){
        return defaultMessage;
    }
}

