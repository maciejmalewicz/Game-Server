package macior.strategygame.service.utilities;

import macior.strategygame.service.utilities.errors.MenuErrors;

public final class PasswordValidator {

    private static String UPPER_CASE_REGEX = ".*[A-Z].*";
    private static String LOWER_CASE_REGEX = ".*[a-z].*";
    private static String DIGIT_REGEX = ".*[0-9].*";
    private static String LENGTH_REGEX = ".........*";

    public static int isValid(String toValidate){
        if (!toValidate.matches(LENGTH_REGEX)){
            return MenuErrors.PASSWORD_TOO_SHORT;

        } else if (!toValidate.matches(LOWER_CASE_REGEX)){
            return MenuErrors.PASSWORD_WITHOUT_LOWERCASE;

        } else if (!toValidate.matches(UPPER_CASE_REGEX)){
            return MenuErrors.PASSWORD_WITHOUT_UPPERCASE;

        } else if (!toValidate.matches(DIGIT_REGEX)){
            return MenuErrors.PASSWORD_WITHOUT_DIGIT;
        } else {
            return 0;
        }
    }
}
