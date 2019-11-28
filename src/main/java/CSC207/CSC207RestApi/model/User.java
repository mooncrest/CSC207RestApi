package CSC207.CSC207RestApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class User {
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("userScores")
    private List<LeaderBoard> userScores;
    @JsonProperty("totalScore")
    private String totalPoints;
    @JsonProperty("timePlayed")
    private String timePlayed;
    @JsonProperty("userID")
    private UUID userId;
    @JsonProperty("currentStage")
    private String currentStage;

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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
        this.password = password;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<LeaderBoard> getUserScores() {
        return userScores;
    }

    public void setUserScores(List<LeaderBoard> userScores) {
        this.userScores = userScores;
    }

    public void addUserScores(LeaderBoard userScores) {
        this.userScores.add(userScores);
    }
}
