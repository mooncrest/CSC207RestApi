package CSC207.CSC207RestApi.api.Tokens;

import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.User;
import CSC207.CSC207RestApi.service.Tokens.JsonTokensService;
import CSC207.CSC207RestApi.service.Tokens.TokensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/tokens/user")
@RestController
public class GetTokenUser {

    private final TokensService tokensService;

    @Autowired
    public GetTokenUser(@Qualifier("jsonTokensService") TokensService tokensService) {
        this.tokensService = tokensService;
    }

    @PostMapping
    public ResponseEntity<User> getUser(@RequestBody Token token) {
        User user = tokensService.getUser(token);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
