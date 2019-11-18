package CSC207.CSC207RestApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginInfo {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
