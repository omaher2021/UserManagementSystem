public class Validator {

    // Username: at least 3 chars, not null, no spaces only
    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return username.trim().length() >= 3;
    }

    // Password: at least 6 chars, not null
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return password.length() >= 6;
    }
}