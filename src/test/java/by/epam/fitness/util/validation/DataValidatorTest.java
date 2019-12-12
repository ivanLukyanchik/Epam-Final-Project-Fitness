package by.epam.fitness.util.validation;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DataValidatorTest {

    @Test
    public void testCheckLogin() {
        boolean isValid = DataValidator.isLoginValid("qwerty123");
        assertTrue(isValid);
    }

    @Test
    public void testNegativeCheckLogin1() {
        boolean isValid = DataValidator.isLoginValid("qwe ty123");
        assertFalse(isValid);
    }

    @Test
    public void testNegativeCheckLogin2() {
        boolean isValid = DataValidator.isLoginValid("qwety123#");
        assertFalse(isValid);
    }

    @Test
    public void testCheckPassword() {
        boolean isValid = DataValidator.isPasswordValid("password341");
        assertTrue(isValid);
    }

    @Test
    public void testNegativeCheckPassword1() {
        boolean isValid = DataValidator.isPasswordValid("pas<sword341");
        assertFalse(isValid);
    }

    @Test
    public void testNegativeCheckPassword2() {
        boolean isValid = DataValidator.isPasswordValid("p1");
        assertFalse(isValid);
    }

    @Test
    public void testNegativeCheckPassword3() {
        boolean isValid = DataValidator.isPasswordValid("pa41 dssa");
        assertFalse(isValid);
    }

    @Test
    public void testCheckName() {
        boolean isValid = DataValidator.isNameValid("Name");
        assertTrue(isValid);
    }

    @Test
    public void testNegativeCheckName1() {
        boolean isValid = DataValidator.isNameValid("Nam e");
        assertFalse(isValid);
    }

    @Test
    public void testNegativeCheckName2() {
        boolean isValid = DataValidator.isNameValid("Name&");
        assertFalse(isValid);
    }

    @Test
    public void testCheckLastName() {
        boolean isValid = DataValidator.isNameValid("LastName");
        assertTrue(isValid);
    }

    @Test
    public void testNegativeCheckLastName1() {
        boolean isValid = DataValidator.isSurnameValid("Last Name");
        assertFalse(isValid);
    }

    @Test
    public void testNegativeCheckLastName2() {
        boolean isValid = DataValidator.isSurnameValid("LastName!");
        assertFalse(isValid);
    }

    @Test
    public void testCheckCardNumber() {
        boolean isValid = DataValidator.isCardNumberValid("1111222244445555");
        assertTrue(isValid);
    }

    @Test
    public void testNegativeCheckCardNumber1() {
        boolean isValid = DataValidator.isCardNumberValid("11112272244445555");
        assertFalse(isValid);
    }

    @Test
    public void testNegativeCheckCardNumber2() {
        boolean isValid = DataValidator.isCardNumberValid("11112t2244445555");
        assertFalse(isValid);
    }

    @Test
    public void testCheckCost1() {
        boolean isValid = DataValidator.isCostValid("100");
        assertTrue(isValid);
    }

    @Test
    public void testCheckCost2() {
        boolean isValid = DataValidator.isCostValid("100.150");
        assertTrue(isValid);
    }


    @Test
    public void testNegativeCheckCost1() {
        boolean isValid = DataValidator.isCostValid("1030.34.5");
        assertFalse(isValid);
    }

    @Test
    public void testNegativeCheckCost2() {
        boolean isValid = DataValidator.isCostValid("1t3");
        assertFalse(isValid);
    }

    @Test
    public void testCheckEmail() {
        boolean isValid = DataValidator.isEmailValid("email@email.com");
        assertTrue(isValid);
    }

    @Test
    public void testNegativeCheckEmail1() {
        boolean isValid = DataValidator.isEmailValid("emailemail.com");
        assertFalse(isValid);
    }

    @Test
    public void testNegativeCheckEmail2() {
        boolean isValid = DataValidator.isEmailValid("ema@ilema@il.com");
        assertFalse(isValid);
    }

    @Test
    public void testPositiveCheckHash() {
        boolean isValid = DataValidator.isHashValid("3174df04f91a261085360f48fd66bf92dcdfed6b05338ced6f411517fbe99a37843204ca2e5ccf33ca66aa509e6ddfd7386d450e670fa197b641b8a5fb57e607");
        assertTrue(isValid);
    }

    @Test
    public void testNegativeCheckHash1() {
        boolean isValid = DataValidator.isHashValid("adkfokrofopepwlewlpldfm");
        assertFalse(isValid);
    }

    @Test
    public void testNegativeCheckHash2() {
        boolean isValid = DataValidator.isHashValid("33174df04f91a261085360f48fd66bf92dcdfed6b05338ced6f411517fbe99a37843204ca2e5ccf33ca66aa509e6ddfd7386d450e670fa197b641b8a5fb57e607");
        assertFalse(isValid);
    }

    @Test
    public void testPositiveCheckInputText() {
        boolean isValid = DataValidator.isCommentContentValid("Hello, man! This is my comment");
    }

    @Test
    public void testNegativeCheckInputText() {
        boolean isValid = DataValidator.isCommentContentValid("Hello%$&");
    }

    @Test
    public void testPositiveId() {
        boolean isValid = DataValidator.isIdentifiableIdValid("15");
    }

    @Test
    public void testNegativeId() {
        boolean isValid = DataValidator.isCommentContentValid("13.2");
    }

    @Test
    public void testPositiveSets() {
        boolean isValid = DataValidator.isIdentifiableIdValid("99");
    }

    @Test
    public void testNegativeSets1() {
        boolean isValid = DataValidator.isCommentContentValid("0");
    }

    @Test
    public void testNegativeSets2() {
        boolean isValid = DataValidator.isCommentContentValid("100");
    }
}