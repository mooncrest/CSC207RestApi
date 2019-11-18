package CSC207.CSC207RestApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class Score {
    @JsonProperty("score")
    private String score;

    public void setScore(String score) {
        this.score = score;
    }
    public String getScore() {
        return score;
    }
}
