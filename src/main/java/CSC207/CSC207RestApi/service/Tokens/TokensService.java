package CSC207.CSC207RestApi.service.Tokens;

import CSC207.CSC207RestApi.model.Score;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.User;

public interface TokensService {

    int insertScore(Token token, Score score, String game);

    Token login(User user);

    void deleteTokens();

    User getUser(Token token);

    void updateStage(Token token, String stage);

    void updateTimePlayed(Token token, String timePlayed);
}
