import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String login;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private long user_id;

    public User(String login, String firstname, String lastname, String email, String password) {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login.length() >= 5 & !login.contains(" ")){
            this.login = login;
        } else {
            System.out.println("Login cannot be shorter than 5 symbols and contains spaces");
        }
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() >= 5 & !password.contains(" ")) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] bytes = md.digest(password.getBytes());
                StringBuilder builder = new StringBuilder();
                for (byte b : bytes) {
                    builder.append(String.format("%02x ", b));
                    password = builder.toString();
                }

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            this.password = password;
        } else {
            System.out.println("Password cannot be shorter than 5 symbols and contains spaces");
        }
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
