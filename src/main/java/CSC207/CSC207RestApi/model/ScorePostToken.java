package CSC207.CSC207RestApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScorePostToken {
    @JsonProperty("token")
    private Token token;
    @JsonProperty("score")
    private Score score;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
