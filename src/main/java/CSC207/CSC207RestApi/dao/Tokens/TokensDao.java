package CSC207.CSC207RestApi.dao.Tokens;

import CSC207.CSC207RestApi.model.Token;

public interface TokensDao {
    public void addToken(Token token);

    public String getUsername(Token token);

    public void deleteTokens();
}
