package CSC207.CSC207RestApi.dao.Tokens;

import CSC207.CSC207RestApi.dao.JsonHelper;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.TokenDataBase;
import org.springframework.stereotype.Repository;

@Repository("tokenDao")
public class TokensJsonDataAccessService implements TokensDao {
    private final String fileName = "Tokens.json";
    private final String jsonDBType = "tokens";

    @Override
    public void addToken(Token token) {
        TokenDataBase DB = (TokenDataBase) JsonHelper.ReadJson(fileName, jsonDBType);
        DB.addToken(token);
        JsonHelper.writeJson(fileName, jsonDBType, DB);
    }

    @Override
    public String getUsername(Token userToken) {
        String access = userToken.getToken();
        TokenDataBase DB = (TokenDataBase) JsonHelper.ReadJson(fileName, jsonDBType);
        for (Token token: DB.getTokens()) {
            if (token.getToken().equals(access) && token.getUsername().equals(userToken.getUsername())) {
                return token.getUsername();
            }
        }
        return null;
    }
}
