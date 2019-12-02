package CSC207.CSC207RestApi.service.Tokens;

import CSC207.CSC207RestApi.dao.Tokens.TokensDao;
import CSC207.CSC207RestApi.model.Score;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.User;
import CSC207.CSC207RestApi.service.Leaderboard.JsonLeaderBoardService;
import CSC207.CSC207RestApi.service.Users.JsonUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("jsonTokensService")
public class JsonTokensService implements TokensService{
    private final JsonUsersService jsonUsersService;
    private final JsonLeaderBoardService jsonLeaderBoardService;

    private final TokensDao tokensDao;

    @Autowired
    public JsonTokensService(JsonUsersService jsonUsersService, JsonLeaderBoardService jsonLeaderBoardService,
                             @Qualifier("tokenDao") TokensDao tokensDao) {
        this.jsonLeaderBoardService = jsonLeaderBoardService;
        this.jsonUsersService = jsonUsersService;
        this.tokensDao = tokensDao;
    }

    /**
     * inserts this score but first we verify the token
     * @param token access token for a specific user
     * @param score the score this user made
     * @param game the name of the game
     * @return a status code whether this was successful
     */
    public int insertScore(Token token, Score score, String game) {
        String tokenUser = tokensDao.getUsername(token);
        String scoreUser = score.getUsername();

        // first check to determine if token posting credentials match
        if (tokenUser == null) {
            // invalid token or expired token
            return -1;
        } else if (scoreUser == null) {
            // no user name associated with the score
            return -2;
        } else if (!scoreUser.equals(tokenUser)) {
            // the token username and score username does not match
            return -3;
        }
        // checks to see if game exists

        // posts scores to LeaderBoards
        int leaderBoardStatusCode = jsonLeaderBoardService.insertScore(score, game);
        // -1 means not a top score -2 means game does not exist, 1 mean everything ran successfully

        // posts scores to UsersLocal
        int usersStatusCode = jsonUsersService.insertScore(score, game);
        // -1 means not a top score, -2 means game does not exist 1 means game successfully added,
        // -3 means user not found

        // currently just returns one but should tell us if code is inserted or not
        return 1;
    }

    /**
     * Should first verify if the user is valid if login is valid and there is a token grab it if not
     * go and create a new one
     * @param user the users login credentials
     * @return access token for this user
     */
    public Token login(User user) {
        Token token = jsonUsersService.login(user);
        if (token == null) {
            return null;
        }
        tokensDao.addToken(token);
        return token;
    }

    /**
     * delete all the tokens saved in this database
     */
    public void deleteTokens() {
        tokensDao.deleteTokens();
    }

    /**
     * get the user info for this specific token
     * @param token
     * @return
     */
    public User getUser(Token token) {
        String tokenUser = tokensDao.getUsername(token);
        if (tokenUser == null) {
            return null;
        }

        return jsonUsersService.getUser(tokenUser);
    }

    /**
     * update the stage this user is allowed to access
     * @param token the authentication token for this user
     * @param stage the stage to be updated to
     */
    public void updateStage(Token token, String stage) {
        String tokenUser = tokensDao.getUsername(token);
        if (tokenUser == null) {
            return;
        }
        jsonUsersService.updateStage(tokenUser, stage);
    }

    /**
     * updates the time played for this user
     * @param token the authentication token for this user
     * @param timePlayed the stage to be updated to
     */
    public void updateTimePlayed(Token token, String timePlayed) {
        String tokenUser = tokensDao.getUsername(token);
        if (tokenUser == null) {
            return;
        }
//        System.out.println("updateTimePlayed");
//        System.out.println(tokenUser);
//        System.out.println(timePlayed);
        jsonUsersService.updateTimePlayed(tokenUser, timePlayed);
    }
}
