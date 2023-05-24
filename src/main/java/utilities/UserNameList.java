package utilities;

public class UserNameList {
    private String username;
    private String password;
    private String favQuote;
    private int profilePic;

    public UserNameList(String username, String password, String favQuote, int profilePic) {
        this.username = username;
        this.password = password;
        this.favQuote = favQuote;
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFavQuote() {
        return favQuote;
    }

    public void setFavQuote(String favQuote) {
        this.favQuote = favQuote;
    }
}
