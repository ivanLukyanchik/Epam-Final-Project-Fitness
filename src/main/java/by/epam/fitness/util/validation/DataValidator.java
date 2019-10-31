package by.epam.fitness.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z][\\w-_.]{2,19}$");
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
    private static final Pattern NAME_SURNAME_PATTERN = Pattern.compile("^[a-zA-Z]{2,19}");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[\\w-_.]{3,19}$");
    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("^[1-9]{16}");
    private static final Pattern INPUT_TEXT_PATTERN = Pattern.compile("^[A-Za-z0-9][A-Za-z,.()\\s0-9]{1,300}");

    public boolean isLoginValid(String login){
        Matcher matcher = LOGIN_PATTERN.matcher(login);
        return matcher.matches();
    }

    public boolean isPasswordValid(String password){
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }

    public boolean isEmailValid(String email){
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public boolean isCardNumberValid(String cardNumber){
        Matcher matcher = CARD_NUMBER_PATTERN.matcher(cardNumber);
        return matcher.matches();
    }

    public boolean isNameValid(String name){
        Matcher matcher = NAME_SURNAME_PATTERN.matcher(name);
        return matcher.matches();
    }

    public boolean isSurnameValid(String surname){
        Matcher matcher = NAME_SURNAME_PATTERN.matcher(surname);
        return matcher.matches();
    }

    public boolean isNutritionDescriptionValid(String description){
        Matcher matcher = INPUT_TEXT_PATTERN.matcher(description);
        return matcher.matches();
    }

    public boolean isCommentContentValid(String commentContent){
        Matcher matcher = INPUT_TEXT_PATTERN.matcher(commentContent);
        return matcher.matches();
    }
}
