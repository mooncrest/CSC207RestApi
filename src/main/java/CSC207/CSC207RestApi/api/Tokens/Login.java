package CSC207.CSC207RestApi.api.Tokens;

import CSC207.CSC207RestApi.model.LoginInfo;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.service.Tokens.TokensService;
import CSC207.CSC207RestApi.service.Users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/tokens/login")
@RestController
public class Login {

    private final TokensService tokensService;

    @Autowired
    public Login(TokensService tokensService) {
        this.tokensService = tokensService;
    }

    @PostMapping
    public ResponseEntity<Token> login(@RequestBody LoginInfo loginInfo) {
        Token token = tokensService.login(loginInfo);
        return new ResponseEntity<Token>(token, HttpStatus.OK);
    }
}

