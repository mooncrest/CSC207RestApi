package CSC207.CSC207RestApi.service.Tokens;

import CSC207.CSC207RestApi.model.Score;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.User;

public interface TokensService {
    /**
     * inserts this score but first we verify the token
     * @param token access token for a specific user
     * @param score the score this user made
     * @param game the name of the game
     * @return a status code whether this was successful
     */
    int insertScore(Token token, Score score, String game);

    /**
     * Should first verify if the user is valid if login is valid and there is a token grab it if not
     * go and create a new one
     * @param user the users login credentials
     * @return access token for this user
     */
    Token login(User user);

    /**
     * delete all the tokens saved in this database
     */
    void deleteTokens();

    /**
     * get the user info for this specific token
     * @param token
     * @return
     */
    User getUser(Token token);

    /**
     * update the stage this user is allowed to access
     * @param token the authentication token for this user
     * @param stage the stage to be updated to
     */
    void updateStage(Token token, String stage);

    /**
     * updates the time played for this user
     * @param token the authentication token for this user
     * @param timePlayed the stage to be updated to
     */
    void updateTimePlayed(Token token, String timePlayed);
}
