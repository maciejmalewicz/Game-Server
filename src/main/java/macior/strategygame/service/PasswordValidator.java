package macior.strategygame.service;

public final class PasswordValidator {

    private static String UPPER_CASE_REGEX = ".*[A-Z].*";
    private static String LOWER_CASE_REGEX = ".*[a-z].*";
    private static String DIGIT_REGEX = ".*[0-9].*";
    private static String LENGTH_REGEX = ".........*";

    public static int isValid(String toValidate){
        if (!toValidate.matches(LENGTH_REGEX)){
            return 2;
        } else if (!toValidate.matches(LOWER_CASE_REGEX)){
            return 3;
        } else if (!toValidate.matches(UPPER_CASE_REGEX)){
            return 4;
        } else if (!toValidate.matches(DIGIT_REGEX)){
            return 5;
        } else {
            return 0;
        }
    }
}
