package CSC207.CSC207RestApi.service.Users;

import CSC207.CSC207RestApi.dao.Tokens.TokensDao;
import CSC207.CSC207RestApi.dao.Users.UsersDao;
import CSC207.CSC207RestApi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsersService {
    private final UsersDao userDao;

    @Autowired
    public UsersService(@Qualifier("jsonUserDao") UsersDao userDao) {
        this.userDao = userDao;
    }

    public int addPerson(User user) {
        return userDao.insertUser(user);
    }

    public List<User> getAllPeople() {
        return userDao.selectAllUsers();
    }

    public Token login(User user) {
        String username = userDao.getUserInfo(user);
        if (username == null) {
            return null;
        }
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setUsername(username);
        return token;
    }

    // -1 means not a top score, -2 means game does not exist 1 means game successfully added,
    // -3 means user not found
    public int insertScore(Score score, String game) {
        // user has to exist since token verified this previously
        User user = userDao.getUserInfo(score.getUsername());

        LeaderBoard leaderBoard = getUserScoreLeaderBoard(user, game);

        if (leaderBoard == null) {
            return -2;
        }

        int placement = getPlacement(score, leaderBoard);

        if (placement == -1) {
            return -1;
        }

        List<Score> scoresToInsert = new ArrayList<>();

        List<Score> oldLeaderBoard = leaderBoard.getScores();

        for (int i = 0; i < 9; i++) {
            if (i == placement) {
                scoresToInsert.add(score);
            } else if (i > placement) {
                scoresToInsert.add(oldLeaderBoard.get(i - 1));
            }
            else {
                scoresToInsert.add(oldLeaderBoard.get(i));
            }
        }
        leaderBoard.setScores(scoresToInsert);

        return userDao.updateUser(user);
    }

    private int getPlacement(Score score, LeaderBoard leaderBoard) {
        int placement = -1;

        List<Score> scores = leaderBoard.getScores();

        for (Score topScore : scores) {
            if (Integer.valueOf(topScore.getScore()) < Integer.valueOf(score.getScore())) {
                placement = scores.indexOf(topScore);
                break;
            }
        }
        return placement;
    }

    private LeaderBoard getUserScoreLeaderBoard(User user, String game) {
        for (LeaderBoard leaderBoard : user.getUserScores()) {
            if (leaderBoard.getGame().equals(game)) {
                return leaderBoard;
            }
        }
        return null;
    }

    // -1 username taken, 1 mean successfully registered
    public int register(User user) {
        User originalUser = userDao.getUserInfo(user.getUsername());
        if (originalUser != null) {
            return -1;
        }

        // want a builder
        String username = user.getUsername();
        user.setTimePlayed("0");
        user.setTotalPoints("0");
        List<LeaderBoard> userScores = new ArrayList<>();
        LeaderBoard leaderBoard = new LeaderBoard();
        userScores.add(generateEmptyLeaderBoard("TetrisGame", username));
        userScores.add(generateEmptyLeaderBoard("MazeGame", username));
        userScores.add(generateEmptyLeaderBoard("RhythmGame", username));
        userScores.add(generateEmptyLeaderBoard("WrapperGame", username));
        user.setUserScores(userScores);
        userDao.insertUser(user);
        return 1;
    }
    private LeaderBoard generateEmptyLeaderBoard(String game, String username) {
        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard.setGame(game);
        List<Score> scores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Score score = new Score();
            score.setScore("0");
            score.setUsername(username);
            scores.add(score);
        }
        leaderBoard.setScores(scores);
        return leaderBoard;
    }
}
