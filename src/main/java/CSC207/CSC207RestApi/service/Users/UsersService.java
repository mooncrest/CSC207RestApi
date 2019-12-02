package CSC207.CSC207RestApi.service.Users;

import CSC207.CSC207RestApi.model.Score;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.User;

import java.util.List;

public interface UsersService {
    /**
     * gets all the users
     * @return List of java objects of users representing json user data
     */
    int addPerson(User user);

    /**
     * gets all the users
     * @return List of java objects of users representing json user data
     */
    List<User> getAllUsers();

    /**
     * gets a specific user based off username
     * @param username the username to search for
     * @return the user if the user could be found in the data base
     */
    User getUser(String username);

    /**
     * tries to the log the user in and verify user info
     * @param user the user information to be verified
     * @return access token for the user if the user information is valid
     */
    Token login(User user);

    /**
     * inserts the score of the user into his own personal leaderboards
     * @param score the score to be posted
     * @param game the name for the leaderboard
     * @return a integer response code
     */
    int insertScore(Score score, String game);

    /**
     * tries to register the user
     * @param user the users information to be registered
     * @return a integer response code
     */
    int register(User user);

    /**
     * updates the stage the user can now access
     * @param username the username of this user
     * @param stage the stage to be updated to
     */
    void updateStage(String username, String stage);

    /**
     * update this users time played
     * @param username the username of the user
     * @param newTimePlayed the new time played to be updated
     */
    void updateTimePlayed(String username, String newTimePlayed);
}
