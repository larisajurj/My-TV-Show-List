package utilities;
public class UserSession {
    private static UserSession instance;
    private String username;
    // Other user session data

    private UserSession() {
        // Private constructor to prevent direct instantiation
    }

    public static UserSession getInstance() {
        if (instance == null) {
            synchronized (UserSession.class) {
                if (instance == null) {
                    instance = new UserSession();
                }
            }
        }
        return instance;
    }

    // Getters and setters for user session data
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
