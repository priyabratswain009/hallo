package com.sunknowledge.dme.rcm.commonconstant;

public class RegexConstant {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    public static final String NAME_REGEX = "^[a-zA-Z\\s]*$";
    public static final String ALPHANUMERIC_REGEX = "^[a-zA-Z0-9]+$";
    public static final String NUMERIC_REGEX = "^\\d{10}$";
    public static final String ALPHABET_REGEX = "^[a-zA-Z]+$";
    public static final String GENDER_REGEX = "(?:m|M|male|Male|f|F|female|Female|FEMALE|MALE)";
}
