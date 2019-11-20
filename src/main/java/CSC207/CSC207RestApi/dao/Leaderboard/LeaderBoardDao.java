package CSC207.CSC207RestApi.dao.Leaderboard;

import CSC207.CSC207RestApi.model.LeaderBoard;
import CSC207.CSC207RestApi.model.Score;

import java.util.List;

public interface LeaderBoardDao {
    int setScores(List<Score> score, String game);

    LeaderBoard getLeaderBoard(String game);

    List<Score> getScores(String game);

    List<LeaderBoard> getLeaderBoards();

    void addLeaderBoard(LeaderBoard leaderBoard);
}
