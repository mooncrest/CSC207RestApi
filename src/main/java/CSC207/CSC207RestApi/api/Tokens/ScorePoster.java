package CSC207.CSC207RestApi.api.Tokens;

import CSC207.CSC207RestApi.model.Score;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.service.Tokens.TokensService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("api/tokens/score")
@RestController
public class ScorePoster {

    private final TokensService tokensService;

    @Autowired
    public ScorePoster(TokensService tokensService) {
        this.tokensService = tokensService;
    }

    @PutMapping(path = "{game}")
    public void addScore(@PathVariable("game") String game, @RequestBody ObjectNode json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Token token = mapper.readValue(json.get("token").toString(), Token.class);
            Score score = mapper.readValue(json.get("score").toString(), Score.class);
            tokensService.insertScore(token, score, game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

