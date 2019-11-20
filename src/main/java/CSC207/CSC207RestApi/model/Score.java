package CSC207.CSC207RestApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Score {
    @JsonProperty("score")
    private String score;
    @JsonProperty("username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }
}
