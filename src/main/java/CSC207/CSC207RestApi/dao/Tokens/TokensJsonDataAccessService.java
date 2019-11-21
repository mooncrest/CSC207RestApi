package CSC207.CSC207RestApi.dao.Tokens;

import CSC207.CSC207RestApi.dao.JsonHelper;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.TokenDataBase;
import org.springframework.stereotype.Repository;

@Repository("tokenDao")
public class TokensJsonDataAccessService implements TokensDao {
    private final JsonHelper<TokenDataBase> jsonHelper = new JsonHelper<>(TokenDataBase.class, "Tokens.json");
    @Override
    public void addToken(Token token) {
        TokenDataBase DB = jsonHelper.ReadJson();
        DB.addToken(token);
        jsonHelper.writeJson(DB);
    }

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

    @Override
    public void deleteTokens() {
        jsonHelper.writeJson(new TokenDataBase());
    }
}
