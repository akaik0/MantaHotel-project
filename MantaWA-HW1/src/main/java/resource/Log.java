package resource;
import java.sql.Timestamp;

//Class which allows to memorize the log in and log out of a user(manager or front office)
public class Log {

    private String email;
    private Timestamp login;
    private Timestamp logout;

    public Log(String userMail, Timestamp login, Timestamp logout){
        this.email = userMail;
        this.login = login;
        this.logout = logout;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getLogin() {
        return login;
    }

    public Timestamp getLogout() {
        return logout;
    }
}