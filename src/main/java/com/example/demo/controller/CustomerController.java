// Model class representing an authentication request

package com.example.demo.model;

public class AuthRequest {

    // Fields for login ID and password
    private String login_id;
    private String password;

    // Default constructor
    public AuthRequest() {
    }

    // Parameterized constructor for setting login ID and password
    public AuthRequest(String login_id, String password) {
        this.login_id = login_id;
        this.password = password;
    }

    // Getter method for retrieving login ID
    public String getLogin_id() {
        return login_id;
    }

    // Setter method for setting login ID
    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    // Getter method for retrieving password
    public String getPassword() {
        return password;
    }

    // Setter method for setting password
    public void setPassword(String password) {
        this.password = password;
    }
}
