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
    private final TokensDao tokensDao;

    @Autowired
    public LeaderBoardService(@Qualifier("jsonLeaderBoardDao") LeaderBoardDao leaderBoardDao,
                              @Qualifier("tokenDao") TokensDao tokensDao) {
        this.leaderBoardDao = leaderBoardDao;
        this.tokensDao = tokensDao;
    }

    public int insertScore(Token token, Score score, String game) {
        String tokenUser = tokensDao.getUsername(token);
        String scoreUser = score.getUsername();

        // need to post to user scores as well
        
        if (scoreUser == null || tokenUser == null) {
            return -3;
        } else if (!scoreUser.equals(tokenUser)) {
            return -4;
        }

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
