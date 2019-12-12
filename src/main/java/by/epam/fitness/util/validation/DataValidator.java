package by.epam.fitness.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Data validator.
 */
public class DataValidator {
    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z][\\w-_.]{2,19}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
    private static final Pattern NAME_SURNAME_PATRONYMIC_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]{3,20}");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[\\w-_.]{3,20}");
    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("[0-9]{16}");
    private static final Pattern INPUT_TEXT_PATTERN = Pattern.compile("[A-Za-zА-Яа-я0-9][A-Za-zА-Яа-я,.'!?()\\s0-9]{1,299}");
    private static final Pattern EXERCISE_DESCRIPTION_PATTERN = Pattern.compile("[A-Za-zА-Яа-яёЁ0-9][A-Za-zА-Яа-яёЁ,.()\\s0-9]{4,399}");
    private static final Pattern EXERCISE_NAME_PATTERN = Pattern.compile("[a-zA-Zа-яА-ЯёЁ\\s]{2,100}");
    private static final Pattern SHA512_PATTERN = Pattern.compile("[a-f0-9]{128}");
    private static final Pattern INPUT_IDENTIFIABLE_ID_PATTERN = Pattern.compile("[\\d]{1,20}");
    private static final Pattern SET_REPEATS_NUMBER_PATTERN = Pattern.compile("[1-9][0-9]?");
    private static final Pattern COST_PATTERN = Pattern.compile("^\\d{1,4}(\\.\\d{3})?$");

    /**
     * Is login valid boolean.
     *
     * @param login the login
     * @return the boolean
     */
    public static boolean isLoginValid(String login) {
        Matcher matcher = LOGIN_PATTERN.matcher(login);
        return matcher.matches();
    }

    /**
     * Is password valid boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean isPasswordValid(String password) {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }

    /**
     * Is email valid boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isEmailValid(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    /**
     * Is hash valid boolean.
     *
     * @param hash the hash
     * @return the boolean
     */
    public static boolean isHashValid(String hash) {
        Matcher matcher = SHA512_PATTERN.matcher(hash);
        return matcher.matches();
    }

    /**
     * Is card number valid boolean.
     *
     * @param cardNumber the card number
     * @return the boolean
     */
    public static boolean isCardNumberValid(String cardNumber) {
        Matcher matcher = CARD_NUMBER_PATTERN.matcher(cardNumber);
        return matcher.matches();
    }

    /**
     * Is name valid boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean isNameValid(String name) {
        Matcher matcher = NAME_SURNAME_PATRONYMIC_PATTERN.matcher(name);
        return matcher.matches();
    }

    /**
     * Is exercise name valid boolean.
     *
     * @param exerciseName the exercise name
     * @return the boolean
     */
    public static boolean isExerciseNameValid(String exerciseName) {
        Matcher matcher = EXERCISE_NAME_PATTERN.matcher(exerciseName);
        return matcher.matches();
    }

    /**
     * Is surname valid boolean.
     *
     * @param surname the surname
     * @return the boolean
     */
    public static boolean isSurnameValid(String surname) {
        Matcher matcher = NAME_SURNAME_PATRONYMIC_PATTERN.matcher(surname);
        return matcher.matches();
    }

    /**
     * Is patronymic valid boolean.
     *
     * @param patronymic the patronymic
     * @return the boolean
     */
    public static boolean isPatronymicValid(String patronymic) {
        Matcher matcher = NAME_SURNAME_PATRONYMIC_PATTERN.matcher(patronymic);
        return matcher.matches();
    }

    /**
     * Is identifiable id valid boolean.
     *
     * @param userId the user id
     * @return the boolean
     */
    public static boolean isIdentifiableIdValid(String userId) {
        Matcher matcher = INPUT_IDENTIFIABLE_ID_PATTERN.matcher(userId);
        return matcher.matches();
    }

    /**
     * Is nutrition description valid boolean.
     *
     * @param description the description
     * @return the boolean
     */
    public static boolean isNutritionDescriptionValid(String description) {
        Matcher matcher = INPUT_TEXT_PATTERN.matcher(description);
        return matcher.matches();
    }

    /**
     * Is comment content valid boolean.
     *
     * @param commentContent the comment content
     * @return the boolean
     */
    public static boolean isCommentContentValid(String commentContent) {
        Matcher matcher = INPUT_TEXT_PATTERN.matcher(commentContent);
        return matcher.matches();
    }

    /**
     * Is exercise description valid boolean.
     *
     * @param exerciseDescription the exercise description
     * @return the boolean
     */
    public static boolean isExerciseDescriptionValid(String exerciseDescription) {
        Matcher matcher = EXERCISE_DESCRIPTION_PATTERN.matcher(exerciseDescription);
        return matcher.matches();
    }

    /**
     * Is set number valid boolean.
     *
     * @param setNumber the set number
     * @return the boolean
     */
    public static boolean isSetNumberValid(String setNumber) {
        Matcher matcher = SET_REPEATS_NUMBER_PATTERN.matcher(setNumber);
        return matcher.matches();
    }

    /**
     * Is repeats number valid boolean.
     *
     * @param repeatsNumber the repeats number
     * @return the boolean
     */
    public static boolean isRepeatsNumberValid(String repeatsNumber) {
        Matcher matcher = SET_REPEATS_NUMBER_PATTERN.matcher(repeatsNumber);
        return matcher.matches();
    }

    /**
     * Is cost valid boolean.
     *
     * @param cost the cost
     * @return the boolean
     */
    public static boolean isCostValid(String cost){
        Matcher matcher = COST_PATTERN.matcher(cost);
        return matcher.matches();
    }
}
