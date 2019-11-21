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
    // -2 means leaderBoard does not exists 1 mean successfully inserted score
    @Override
    public int setScores(List<Score> score, String game) {
        LeaderBoardDataBase DB = jsonHelper.ReadJson();
        LeaderBoard leaderBoard = getLeaderBoardHelper(game, DB);

        if (leaderBoard == null) {
            return -2;
        }

        leaderBoard.setScores(score);
        jsonHelper.writeJson(DB);
        return 1;
    }

    @Override
    public LeaderBoard getLeaderBoard(String game) {
        LeaderBoardDataBase DB = jsonHelper.ReadJson();
        return getLeaderBoardHelper(game, DB);
    }

    private LeaderBoard getLeaderBoardHelper(String game, LeaderBoardDataBase DB) {
        List<LeaderBoard> leaderBoards = DB.getLeaderBoards();
        for (LeaderBoard board: leaderBoards) {
            if (game.equals(board.getGame())) {
                return board;
            }
        }
        return null;
    }

    @Override
    public List<Score> getScores(String game) {
        LeaderBoard leaderBoard = getLeaderBoard(game);
        if (leaderBoard == null) {
            return null;
        }
        return leaderBoard.getScores();
    }

    @Override
    public LeaderBoardDataBase getLeaderBoards() {
        LeaderBoardDataBase DB = jsonHelper.ReadJson();
        return DB;
    }

    @Override
    public void addLeaderBoard(LeaderBoard leaderBoard) {
        LeaderBoardDataBase DB = jsonHelper.ReadJson();
        DB.addLeaderBoard(leaderBoard);
        jsonHelper.writeJson(DB);
    }
}
