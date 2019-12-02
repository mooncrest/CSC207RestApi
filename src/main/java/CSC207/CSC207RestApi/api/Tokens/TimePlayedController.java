package CSC207.CSC207RestApi.api.Tokens;

import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.service.Tokens.JsonTokensService;
import CSC207.CSC207RestApi.service.Tokens.TokensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/tokens/timeplayed")
@RestController
public class TimePlayedController {
    private final TokensService tokensService;

    @Autowired
    public TimePlayedController(@Qualifier("jsonTokensService")TokensService tokensService) {
        this.tokensService = tokensService;
    }

    /**
     * updates this users time played
     * @param timePlayed the new time played by this user
     * @param token this users access token
     * @return a json object status code
     */
    @PutMapping(path = "{duration}")
    public ResponseEntity<String> login(@PathVariable("duration") String timePlayed, @RequestBody Token token) {
        System.out.println("response time played");
        System.out.println(timePlayed);
        System.out.println(token.getToken());
        System.out.println(token.getUsername());
        tokensService.updateTimePlayed(token, timePlayed);
        return new ResponseEntity<>("{Status: updated}", HttpStatus.OK);
    }
}
