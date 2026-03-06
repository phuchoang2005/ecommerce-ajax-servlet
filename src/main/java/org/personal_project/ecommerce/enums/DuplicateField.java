package org.personal_project.ecommerce.enums;

public enum DuplicateField {
    USERNAME("uk_username", "Username", "username"),
    EMAIL("uk_profile_email", "Email", "email"),
    PHONE("uk_profile_phone", "Phone", "phone");

    private final String constraintName;
    private final String friendlyName;
    private final String fieldName;

    DuplicateField(String constraintName, String friendlyName, String fieldName) {
        this.constraintName = constraintName;
        this.friendlyName = friendlyName;
        this.fieldName = fieldName;
    }

    public static DuplicateField fromErrorMessage(String errorMessage) {
        for (DuplicateField field : values()) {
            if (errorMessage.contains(field.constraintName)) {
                return field;
            }
        }
        return null;
    }

    public String getConstraintName(){return this.constraintName;}
    public String getFriendlyName(){return this.friendlyName;}
    public String getFieldName(){return this.fieldName;}
}