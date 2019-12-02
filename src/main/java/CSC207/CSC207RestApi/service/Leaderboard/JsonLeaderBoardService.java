package CSC207.CSC207RestApi.service.Leaderboard;

import CSC207.CSC207RestApi.dao.Leaderboard.LeaderBoardDao;
import CSC207.CSC207RestApi.model.LeaderBoard;
import CSC207.CSC207RestApi.model.LeaderBoardDataBase;
import CSC207.CSC207RestApi.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("jsonLeaderService")
public class JsonLeaderBoardService implements LeaderBoardService {
    private final LeaderBoardDao leaderBoardDao;

    @Autowired
    public JsonLeaderBoardService(@Qualifier("jsonLeaderBoardDao") LeaderBoardDao leaderBoardDao) {
        this.leaderBoardDao = leaderBoardDao;
    }

    /**
     * Inserts the score if it is a high score into a specific leaderboard
     * @param score the score to be submitted
     * @param game the name of the game
     * @return an integer representation of the status code
     */
    @Override
    public int insertScore(Score score, String game) {
        // -1 means not a top score -2 means game does not exist, 1 mean everything ran successfully
        int position = topScore(score.getScore(), game);

        if (position < 0) {
            return position;
        }

        List<Score> scoresToInsert = new ArrayList<>();

        List<Score> oldLeaderBoard = getScores(game);

        for (int i = 0; i < 10; i++) {
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

    /**
     * A helper method to get the placement of this score
     * @param score the score to check
     * @param game the name of the game
     * @return the placement of this user on this leaderboard
     */
    private int topScore(String score, String game) {
        // -1 means is not a top score -2 means game leaderBoard does not exist
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
    /**
     * gets a leader board with name game
     * @param game the name of the game
     * @return a leaderboard for this game
     */
    public LeaderBoard getLeaderBoard(String game) {
        return leaderBoardDao.getLeaderBoard(game);
    }

    /**
     * get the list of scores for this specific game
     * @param game the name of the game
     * @return the list of high scores associated with this game
     */
    public List<Score> getScores(String game) {
        return leaderBoardDao.getScores(game);
    }

    /**
     * get all the leaderboards. this is used only by admins
     * @return the whole entire leaderboard database
     */
    public LeaderBoardDataBase getAllScores() {
        return leaderBoardDao.getLeaderBoards();
    }

    /**
     * add a specific leaderBoard to database used only by admins
     * @param leaderBoard the leaderboard to be added
     */
    public void addLeaderBoard(LeaderBoard leaderBoard) {
        leaderBoardDao.addLeaderBoard(leaderBoard);
    }
}
