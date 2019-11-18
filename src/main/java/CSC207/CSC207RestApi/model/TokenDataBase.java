package CSC207.CSC207RestApi.model;

import java.util.ArrayList;
import java.util.List;

public class TokenDataBase {
    private List<Token> tokens = new ArrayList<>();

    public void addToken(Token token) {
        tokens.add(token);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
}
