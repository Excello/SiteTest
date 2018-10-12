package data;

public class User {
    public static final User USER_ADMIN = new User("admin", "password", "Administrator");
    public static final User USER_JDOE = new User("jdoe", "password", "John Doe");

    private String userName;
    private String password;
    private String name;

    public User(String userName, String password, String name) {
        this.userName = userName;
        this.password = password;
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
