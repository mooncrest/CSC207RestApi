package CSC207.CSC207RestApi.api.Tokens;

import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.service.Tokens.TokensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/tokens/timeplayed")
@RestController
public class TimePlayedController {
    private final TokensService tokensService;

    @Autowired
    public TimePlayedController(TokensService tokensService) {
        this.tokensService = tokensService;
    }

    @PutMapping(path = "{duration}")
    public ResponseEntity<String> login(@PathVariable("duration") String timePlayed, @RequestBody Token token) {
        tokensService.updateTimePlayed(token, timePlayed);
        return new ResponseEntity<>("{Status: updated}", HttpStatus.OK);
    }
}
