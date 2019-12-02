package CSC207.CSC207RestApi.dao.Tokens;

import CSC207.CSC207RestApi.model.Token;

public interface TokensDao {
    /**
     * adds a token to the database
     * @param token a user authentication token
     */
    void addToken(Token token);

    /**
     *  verifys if the user token is valid by returning the username
     * @param token a user authentication token
     * @return the name of the username
     */
    String getUsername(Token token);

    /**
     * delete all the tokens in this database used for shutting the server down
     * or for testing purposes
     */
    void deleteTokens();
}
