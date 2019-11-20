package CSC207.CSC207RestApi.service.Leaderboard;

import CSC207.CSC207RestApi.dao.Leaderboard.LeaderBoardDao;
import CSC207.CSC207RestApi.dao.Tokens.TokensDao;
import CSC207.CSC207RestApi.model.LeaderBoard;
import CSC207.CSC207RestApi.model.Score;
import CSC207.CSC207RestApi.model.Token;
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

    // -1 means not a top score -2 means game does not exist, 1 mean everything ran successfully
    public int insertScore(Score score, String game) {

        int position = topScore(score.getScore(), game);

        if (position < 0) {
            return position;
        }

        List<Score> scoresToInsert = new ArrayList<>();

        List<Score> oldLeaderBoard = getScores(game);

        for (int i = 0; i < 9; i++) {
            if (i == position) {
                scoresToInsert.add(score);
            } else if (i > position) {
                scoresToInsert.add(oldLeaderBoard.get(i - 1));
            }
            else {
                scoresToInsert.add(oldLeaderBoard.get(i));
            }
        }

        return leaderBoardDao.setScores(scoresToInsert, game);
    }

    // -1 means is not a top score -2 means game leaderBoard does not exist
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
