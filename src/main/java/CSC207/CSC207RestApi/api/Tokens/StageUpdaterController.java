package CSC207.CSC207RestApi.api.Tokens;

import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.User;
import CSC207.CSC207RestApi.service.Tokens.TokensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("api/tokens/stage")
@RestController
public class StageUpdaterController {
    private final TokensService tokensService;

    @Autowired
    public StageUpdaterController(TokensService tokensService) {
        this.tokensService = tokensService;
    }

    @PutMapping(path = "{stage}")
    public ResponseEntity<String> login(@PathVariable("stage") String stage, @RequestBody Token token) {
        System.out.println("stage update response");
        System.out.println(stage);
        System.out.println(token.getUsername());
        System.out.println(token.getToken());
        tokensService.updateStage(token, stage);
        return new ResponseEntity<>("{Status: posted}", HttpStatus.OK);
    }
}
