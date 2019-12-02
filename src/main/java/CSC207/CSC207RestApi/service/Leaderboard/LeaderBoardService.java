package CSC207.CSC207RestApi.service.Leaderboard;

import CSC207.CSC207RestApi.model.LeaderBoard;
import CSC207.CSC207RestApi.model.LeaderBoardDataBase;
import CSC207.CSC207RestApi.model.Score;

import java.util.List;

public interface LeaderBoardService {
    /**
     * Inserts the score if it is a high score into a specific leaderboard
     * @param score the score to be submitted
     * @param game the name of the game
     * @return an integer representation of the status code
     */
    int insertScore(Score score, String game);

    /**
     * gets a leader board with name game
     * @param game the name of the game
     * @return a leaderboard for this game
     */
    LeaderBoard getLeaderBoard(String game);

    /**
     * get the list of scores for this specific game
     * @param game the name of the game
     * @return the list of high scores associated with this game
     */
    List<Score> getScores(String game);

    /**
     * get all the leaderboards. this is used only by admins
     * @return the whole entire leaderboard database
     */
    LeaderBoardDataBase getAllScores();

    /**
     * add a specific leaderBoard to database used only by admins
     * @param leaderBoard the leaderboard to be added
     */
    void addLeaderBoard(LeaderBoard leaderBoard);
}
