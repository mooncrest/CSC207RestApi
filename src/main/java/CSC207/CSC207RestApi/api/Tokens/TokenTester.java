package CSC207.CSC207RestApi.api.Tokens;

import CSC207.CSC207RestApi.model.User;
import CSC207.CSC207RestApi.service.Tokens.JsonTokensService;
import CSC207.CSC207RestApi.service.Tokens.TokensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/tokens/testing")
@RestController
public class TokenTester {
    private final TokensService tokensService;

    @Autowired
    public TokenTester(@Qualifier("jsonTokensService")TokensService tokensService) {
        this.tokensService = tokensService;
    }

    @PostMapping
    public void login(@RequestBody User user) {
        if (user.getUsername().equals("AdminTesting") && user.getPassword().equals("SomethingSecure")) {
            tokensService.deleteTokens();
        }
    }
}
