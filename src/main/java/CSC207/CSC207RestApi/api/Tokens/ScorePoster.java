package CSC207.CSC207RestApi.api.Tokens;

import CSC207.CSC207RestApi.model.Score;
import CSC207.CSC207RestApi.model.ScorePostToken;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.service.Tokens.TokensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/tokens/score")
@RestController
public class ScorePoster {

    private final TokensService tokensService;

    @Autowired
    public ScorePoster(TokensService tokensService) {
        this.tokensService = tokensService;
    }

    @PutMapping(path = "{game}")
    public void addScore(@PathVariable("game") String game, @RequestBody ScorePostToken token) {
        Token userToken = token.getToken();
        Score userScore = token.getScore();
        tokensService.insertScore(userToken, userScore, game);
    }
}

