package CSC207.CSC207RestApi.service.Leaderboard;

import CSC207.CSC207RestApi.dao.Leaderboard.LeaderBoardDao;
import CSC207.CSC207RestApi.model.LeaderBoard;
import CSC207.CSC207RestApi.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderBoardService {
    private final LeaderBoardDao leaderBoardDao;

    @Autowired
    public LeaderBoardService(@Qualifier("jsonLeaderBoardDao") LeaderBoardDao leaderBoardDao) {
        this.leaderBoardDao = leaderBoardDao;
    }

    public int insertScore(Score score, String game) {
        int position = topScore(score.getScore(), game);
        if (position < 0) {
            return position;
        }
        List<Score> scoresToInsert = new ArrayList<>();

        List<Score> oldLeaderboard = getScores(game);

        for (int i = 0; i < 9; i++) {
            if (i == position) {
                scoresToInsert.add(score);
            }
            else {
                scoresToInsert.add(oldLeaderboard.get(i));
            }
        }

        return leaderBoardDao.insertScore(scoresToInsert, game);
    }

    private int topScore(String score, String game) {
        int placement = -1;
        List<Score> scores = getScores(game);
        if (scores == null) {
            return placement - 1;
        }
        for (Score topScore : scores) {
            if (Integer.valueOf(topScore.getScore()) < Integer.valueOf(score)) {
                placement = scores.indexOf(topScore);
                break;
            }
        }
        return placement;
    }

    public LeaderBoard getLeaderBoard(String game) {
        return leaderBoardDao.getLeaderBoard(game);
    }
    public List<Score> getScores(String game) {
        return leaderBoardDao.getScores(game);
    }

    public List<LeaderBoard> getAllScores() {
        return leaderBoardDao.getLeaderBoards();
    }

    public void addLeaderBoard(LeaderBoard leaderBoard) {
        leaderBoardDao.addLeaderBoard(leaderBoard);
    }
}
