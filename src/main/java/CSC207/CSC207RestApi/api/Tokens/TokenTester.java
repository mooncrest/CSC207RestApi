package CSC207.CSC207RestApi.api.Tokens;

import CSC207.CSC207RestApi.model.Score;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.User;
import CSC207.CSC207RestApi.service.Tokens.TokensService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("api/tokens/testing")
@RestController
public class TokenTester {
    private final TokensService tokensService;

    @Autowired
    public TokenTester(TokensService tokensService) {
        this.tokensService = tokensService;
    }

    @PostMapping
    public void login(@RequestBody User user) {
        if (user.getUsername().equals("AdminTesting") && user.getPassword().equals("SomethingSecure")) {
            tokensService.deleteTokens();
        }
    }
}
