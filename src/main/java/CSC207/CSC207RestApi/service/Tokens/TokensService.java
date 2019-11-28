package CSC207.CSC207RestApi.service.Tokens;

import CSC207.CSC207RestApi.dao.Tokens.TokensDao;
import CSC207.CSC207RestApi.model.Score;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.User;
import CSC207.CSC207RestApi.service.Leaderboard.LeaderBoardService;
import CSC207.CSC207RestApi.service.Users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TokensService {
    private final UsersService usersService;
    private final LeaderBoardService leaderBoardService;

    private final TokensDao tokensDao;

    @Autowired
    public TokensService(UsersService usersService, LeaderBoardService leaderBoardService,
                         @Qualifier("tokenDao") TokensDao tokensDao) {
        this.leaderBoardService = leaderBoardService;
        this.usersService = usersService;
        this.tokensDao = tokensDao;
    }

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
        int leaderBoardStatusCode = leaderBoardService.insertScore(score, game);
        // -1 means not a top score -2 means game does not exist, 1 mean everything ran successfully

        // posts scores to UsersLocal
        int usersStatusCode = usersService.insertScore(score, game);
        // -1 means not a top score, -2 means game does not exist 1 means game successfully added,
        // -3 means user not found

        // currently just returns one but should tell us if code is inserted or not
        return 1;
    }

    public Token login(User user) {
        Token token = usersService.login(user);
        if (token == null) {
            return null;
        }
        tokensDao.addToken(token);
        return token;
    }

    public void deleteTokens() {
        tokensDao.deleteTokens();
    }

    public User getUser(Token token) {
        String tokenUser = tokensDao.getUsername(token);
        if (tokenUser == null) {
            return null;
        }

        return usersService.getUser(tokenUser);
    }

    public void updateStage(Token token, String stage) {
        String tokenUser = tokensDao.getUsername(token);
        if (tokenUser == null) {
            return;
        }
        System.out.println(tokenUser);
        usersService.updateStage(tokenUser, stage);
    }
}
