package by.epam.fitness.mail;

public class SendingEmail {

    public static void verify(String login, String userEmail, String userHash) {
        String message = "Your Verification Link : " + "http://localhost:8080/trainingWeb_war_exploded/controller?" +
                "command=activate&key1=" + userEmail + "&key2=" + login + "&key3=" + userHash;
        new GoogleMailThread("007checkmailphp", "375299300520", "Email Verification Link",
                login, message).start();
    }

    public static void restorePassword(String login, String userEmail, String userHash) {
        String message = "Link to restore your password: " + "http://localhost:8080/trainingWeb_war_exploded" +
                "/passwordRestore?key1=" + userEmail + "&key2=" + login + "&key3=" + userHash;
        new GoogleMailThread("007checkmailphp", "375299300520", "Password Restore Link",
                userEmail, message).start();
    }
}
