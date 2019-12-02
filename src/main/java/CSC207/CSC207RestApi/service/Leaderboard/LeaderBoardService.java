package CSC207.CSC207RestApi.service.Leaderboard;

import CSC207.CSC207RestApi.model.LeaderBoard;
import CSC207.CSC207RestApi.model.LeaderBoardDataBase;
import CSC207.CSC207RestApi.model.Score;

public interface LeaderBoardService {
    int insertScore(Score score, String game);

    LeaderBoard getLeaderBoard(String game);

    LeaderBoardDataBase getAllScores();

    void addLeaderBoard(LeaderBoard leaderBoard);
}
