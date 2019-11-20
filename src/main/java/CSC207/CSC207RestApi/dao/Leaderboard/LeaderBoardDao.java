package CSC207.CSC207RestApi.dao.Leaderboard;

import CSC207.CSC207RestApi.model.LeaderBoard;
import CSC207.CSC207RestApi.model.LeaderBoardDataBase;
import CSC207.CSC207RestApi.model.Score;

import java.util.List;

public interface LeaderBoardDao {
    public int setScores(List<Score> score, String game);

    public LeaderBoard getLeaderBoard(String game);

    public List<Score> getScores(String game);

    public LeaderBoardDataBase getLeaderBoards();

    public void addLeaderBoard(LeaderBoard leaderBoard);
}
