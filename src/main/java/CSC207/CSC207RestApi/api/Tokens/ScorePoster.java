package CSC207.CSC207RestApi.api.Tokens;

import CSC207.CSC207RestApi.model.Score;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.service.Tokens.JsonTokensService;
import CSC207.CSC207RestApi.service.Tokens.TokensService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("api/tokens/score")
@RestController
public class ScorePoster {

    private final TokensService tokensService;

    @Autowired
    public ScorePoster(@Qualifier("jsonTokensService")TokensService tokensService) {
        this.tokensService = tokensService;
    }

    @PutMapping(path = "{game}")
    public ResponseEntity<String> addScore(@PathVariable("game") String game, @RequestBody ObjectNode json) {
        try {
            System.out.println("score response");
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(json.get("token"));
            System.out.println(json.get("score"));
            System.out.println(game);
            Token token = mapper.readValue(json.get("token").toString(), Token.class);
            Score score = mapper.readValue(json.get("score").toString(), Score.class);
            tokensService.insertScore(token, score, game);
            return new ResponseEntity<>("{Status: posted}", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

