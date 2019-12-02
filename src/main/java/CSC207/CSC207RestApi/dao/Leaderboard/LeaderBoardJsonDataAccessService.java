package CSC207.CSC207RestApi.dao.Leaderboard;

import CSC207.CSC207RestApi.dao.JsonHelper;
import CSC207.CSC207RestApi.model.LeaderBoard;
import CSC207.CSC207RestApi.model.LeaderBoardDataBase;
import CSC207.CSC207RestApi.model.Score;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("jsonLeaderBoardDao")
public class LeaderBoardJsonDataAccessService implements LeaderBoardDao {
    private final JsonHelper<LeaderBoardDataBase> jsonHelper = new JsonHelper<>(LeaderBoardDataBase.class, "LeaderBoards.json");

    /**
     * replaces the scores in leader board of game
     * @param score the list of scores to input
     * @param game the name of the game
     * @return int value to determine if its successfull
     */
    @Override
    public int setScores(List<Score> score, String game) {
        // -2 means leaderBoard does not exists 1 mean successfully inserted score
        LeaderBoardDataBase DB = jsonHelper.ReadJson();
        LeaderBoard leaderBoard = getLeaderBoardHelper(game, DB);

        if (leaderBoard == null) {
            return -2;
        }

        leaderBoard.setScores(score);
        jsonHelper.writeJson(DB);
        return 1;
    }

    /**
     *  get the name of the leaderboard of name game
     * @param game the name of the leaderboard
     * @return the leaderboard with the name game
     */
    @Override
    public LeaderBoard getLeaderBoard(String game) {
        LeaderBoardDataBase DB = jsonHelper.ReadJson();
        return getLeaderBoardHelper(game, DB);
    }

    /**
     * gets the leaderboard of a specific game
     * @param game the name of the leaderboard
     * @param DB the whole database of leaderboards
     * @return the specific leaderboard with name game
     */
    private LeaderBoard getLeaderBoardHelper(String game, LeaderBoardDataBase DB) {
        List<LeaderBoard> leaderBoards = DB.getLeaderBoards();
        for (LeaderBoard board: leaderBoards) {
            if (game.equals(board.getGame())) {
                return board;
            }
        }
        return null;
    }

    /**
     *  get the list of scores in the leaderboard of name game
     * @param game the name of the leaderboard
     * @return get only the scores of the leader of the name game
     */
    @Override
    public List<Score> getScores(String game) {
        LeaderBoard leaderBoard = getLeaderBoard(game);
        if (leaderBoard == null) {
            return null;
        }
        return leaderBoard.getScores();
    }
    /**
     * gets all the leaderboards
     * @return returns all the leaderboards stored
     */
    @Override
    public LeaderBoardDataBase getLeaderBoards() {
        LeaderBoardDataBase DB = jsonHelper.ReadJson();
        return DB;
    }

    /**
     * Adds a leaderboard should be used only by admins
     * @param leaderBoard adds a leaderboard to the leaderboard data base
     */
    @Override
    public void addLeaderBoard(LeaderBoard leaderBoard) {
        LeaderBoardDataBase DB = jsonHelper.ReadJson();
        DB.addLeaderBoard(leaderBoard);
        jsonHelper.writeJson(DB);
    }
}
