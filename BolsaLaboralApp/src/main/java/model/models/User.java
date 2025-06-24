package model.models;

public class User {
    private int id;
    private String username;
    private String email;
    private String passwordHash;
    private Profile profile;

    public User(int id, String username, String email, String passwordHash, Profile profile) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.profile = profile;
    }

    public User(int id, String username, String email, String passwordHash) {
        this(id, username, email, passwordHash, null);
    }

    public boolean authenticate(String inputEmail, String inputPassword) {
        return this.email.equals(inputEmail) && this.passwordHash.equals(inputPassword);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
