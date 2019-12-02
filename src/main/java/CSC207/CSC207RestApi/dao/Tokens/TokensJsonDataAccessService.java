package CSC207.CSC207RestApi.dao.Tokens;

import CSC207.CSC207RestApi.dao.JsonHelper;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.TokenDataBase;
import org.springframework.stereotype.Repository;

@Repository("tokenDao")
public class TokensJsonDataAccessService implements TokensDao {
    private final JsonHelper<TokenDataBase> jsonHelper = new JsonHelper<>(TokenDataBase.class, "Tokens.json");

    /**
     * adds a token to the database
     * @param token a user authentication token
     */
    @Override
    public void addToken(Token token) {
        TokenDataBase DB = jsonHelper.ReadJson();
        DB.addToken(token);
        jsonHelper.writeJson(DB);
    }

    /**
     *  verifys if the user token is valid by returning the username
     * @param userToken a user authentication token
     * @return the name of the username
     */
    @Override
    public String getUsername(Token userToken) {
        String access = userToken.getToken();
        TokenDataBase DB = jsonHelper.ReadJson();
        for (Token token: DB.getTokens()) {
            if (token.getToken().equals(access) && token.getUsername().equals(userToken.getUsername())) {
                return token.getUsername();
            }
        }
        return null;
    }

    /**
     * delete all the tokens in this database used for shutting the server down
     * or for testing purposes
     */
    @Override
    public void deleteTokens() {
        jsonHelper.writeJson(new TokenDataBase());
    }
}
