package CSC207.CSC207RestApi.dao.Leaderboard;

import CSC207.CSC207RestApi.model.LeaderBoard;
import CSC207.CSC207RestApi.model.LeaderBoardDataBase;
import CSC207.CSC207RestApi.model.Score;

import java.util.List;

public interface LeaderBoardDao {
    /**
     * replaces the scores in leader board of game
     * @param score the list of scores to input
     * @param game the name of the game
     * @return int value to determine if its successfull
     */
    int setScores(List<Score> score, String game);

    /**
     *  get the name of the leaderboard of name game
     * @param game the name of the leaderboard
     * @return the leaderboard with the name game
     */
    LeaderBoard getLeaderBoard(String game);

    /**
     *  get the list of scores in the leaderboard of name game
     * @param game the name of the leaderboard
     * @return get only the scores of the leader of the name game
     */
    List<Score> getScores(String game);

    /**
     * gets all the leaderboards
     * @return returns all the leaderboards stored
     */
    LeaderBoardDataBase getLeaderBoards();

    /**
     * Adds a leaderboard should be used only by admins
     * @param leaderBoard adds a leaderboard to the leaderboard data base
     */
    void addLeaderBoard(LeaderBoard leaderBoard);
}
