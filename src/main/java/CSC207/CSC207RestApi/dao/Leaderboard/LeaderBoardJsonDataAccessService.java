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
    private final String fileName = "LeaderBoards.json";
    private final String jsonDBType = "leader";

    // -2 means leaderBoard does not exists 1 mean successfully inserted score
    @Override
    public int setScores(List<Score> score, String game) {
        LeaderBoardDataBase DB = (LeaderBoardDataBase)  JsonHelper.ReadJson(fileName, jsonDBType);
        LeaderBoard leaderBoard = getLeaderBoardHelper(game, DB);

        if (leaderBoard == null) {
            return -2;
        }

        leaderBoard.setScores(score);
        JsonHelper.writeJson(fileName, jsonDBType, DB);
        return 1;
    }

    @Override
    public LeaderBoard getLeaderBoard(String game) {
        LeaderBoardDataBase DB = (LeaderBoardDataBase)  JsonHelper.ReadJson(fileName, jsonDBType);
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
    public List<LeaderBoard> getLeaderBoards() {
        LeaderBoardDataBase DB = (LeaderBoardDataBase)  JsonHelper.ReadJson(fileName, jsonDBType);
        List<LeaderBoard> leaderBoards = DB.getLeaderBoards();
        return leaderBoards;
    }

    @Override
    public void addLeaderBoard(LeaderBoard leaderBoard) {
        LeaderBoardDataBase DB = (LeaderBoardDataBase)  JsonHelper.ReadJson(fileName, jsonDBType);
        DB.addLeaderBoard(leaderBoard);
        JsonHelper.writeJson(fileName, jsonDBType, DB);
    }
}
