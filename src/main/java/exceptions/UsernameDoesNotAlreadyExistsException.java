package exceptions;

public class UsernameDoesNotAlreadyExistsException extends Exception {
    private String username;

    public UsernameDoesNotAlreadyExistsException(String username) {
        super(String.format("Incorrect Username or Password!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

