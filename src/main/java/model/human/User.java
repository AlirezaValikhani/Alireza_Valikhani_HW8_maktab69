package model.human;

public class User {
    private Integer id;
    private String firstName;
    private String lastname;
    private String userName;
    private String password;
    private String email;

    public User(Integer id, String firstName, String lastname,
                String userName, String password, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String firstName, String lastname,
                String userName, String password, String email) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public User(Integer id, String firstName, String lastname, String userName, String password) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
