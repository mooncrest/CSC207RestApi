package CSC207.CSC207RestApi.service.Users;

import CSC207.CSC207RestApi.dao.Users.UsersDao;
import CSC207.CSC207RestApi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("jsonUsersService")
public class JsonUsersService implements UsersService{
    private final UsersDao userDao;

    @Autowired
    public JsonUsersService(@Qualifier("jsonUserDao") UsersDao userDao) {
        this.userDao = userDao;
    }

    /**
     * gets all the users
     * @return List of java objects of users representing json user data
     */
    @Override
    public int addPerson(User user) {
        return userDao.insertUser(user);
    }

    /**
     * gets all the users
     * @return List of java objects of users representing json user data
     */
    @Override
    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    /**
     * gets a specific user based off username
     * @param username the username to search for
     * @return the user if the user could be found in the data base
     */
    @Override
    public User getUser(String username) {
        return userDao.getUserInfo(username);
    }

    /**
     * tries to the log the user in and verify user info
     * @param user the user information to be verified
     * @return access token for the user if the user information is valid
     */
    @Override
    public Token login(User user) {
        String username = userDao.getLoginUser(user);
        if (username == null) {
            return null;
        }
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setUsername(username);
        return token;
    }

    /**
     * inserts the score of the user into his own personal leaderboards
     * @param score the score to be posted
     * @param game the name for the leaderboard
     * @return a integer response code
     */
    @Override
    public int insertScore(Score score, String game) {
        // -1 means not a top score, -2 means game does not exist 1 means game successfully added,
        // -3 means user not found
        // user has to exist since token verified this previously
        User user = userDao.getUserInfo(score.getUsername());
        LeaderBoard leaderBoard = null;
        if (user != null) {
            int totalScore = Integer.valueOf(user.getTotalPoints());
            int addOnScore = Integer.valueOf(score.getScore());
            user.setTotalPoints(String.valueOf(totalScore + addOnScore));
            leaderBoard = getUserScoreLeaderBoard(user, game);
        }
        if (leaderBoard == null) {
            return -2;
        }

        int placement = getPlacement(score, leaderBoard);

        if (placement == -1) {
            return -1;
        }

        List<Score> scoresToInsert = new ArrayList<>();

        List<Score> oldLeaderBoard = leaderBoard.getScores();

        for (int i = 0; i < 10; i++) {
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

    /**
     * Helper to get the users placement relative to the current leaderboard
     * @param score the score the user made
     * @param leaderBoard the leaderboard to be searched
     * @return the placement index
     */
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

    /**
     * get this users specific personal leaderboard
     * @param user the user information
     * @param game the name of the game of the leaderboard
     * @return the specific personal leaderboard that matches the game name
     */
    private LeaderBoard getUserScoreLeaderBoard(User user, String game) {
        for (LeaderBoard leaderBoard : user.getUserScores()) {
            if (leaderBoard.getGame().equals(game)) {
                return leaderBoard;
            }
        }
        return null;
    }

    /**
     * tries to register the user
     * @param user the users information to be registered
     * @return a integer response code
     */
    @Override
    public int register(User user) {
        // -1 username taken, 1 mean successfully registered
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
        user.setCurrentStage("0");
        userDao.insertUser(user);
        return 1;
    }

    /**
     * generates a blank leaderboard for initializing a new user
     * @param game the name of the game
     * @param username the username for this user
     * @return generates a personal leaderboard with empty scores of 0 by username
     */
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

    /**
     * updates the stage the user can now access
     * @param username the username of this user
     * @param stage the stage to be updated to
     */
    @Override
    public void updateStage(String username, String stage) {
        User user = getUser(username);
        int stageValue = Integer.parseInt(user.getCurrentStage());
        if (Integer.parseInt(stage) > stageValue && stageValue < 5) {
            user.setCurrentStage(String.valueOf(stageValue + 1));
        }
        userDao.updateUser(user);
    }

    /**
     * update this users time played
     * @param username the username of the user
     * @param newTimePlayed the new time played to be updated
     */
    @Override
    public void updateTimePlayed(String username, String newTimePlayed) {
//        System.out.println("updateTimePlayedUserService");
        User user = getUser(username);
//        System.out.println(user);
//        System.out.println(username);
//        System.out.println(newTimePlayed);
        long totalTimePlayed = Integer.parseInt(user.getTimePlayed()) + Integer.parseInt(newTimePlayed);
        user.setTimePlayed(String.valueOf(totalTimePlayed));
        userDao.updateUser(user);
    }
}
