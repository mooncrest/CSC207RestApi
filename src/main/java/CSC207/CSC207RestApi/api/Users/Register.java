package CSC207.CSC207RestApi.api.Users;

import CSC207.CSC207RestApi.model.LoginInfo;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.User;
import CSC207.CSC207RestApi.service.Tokens.TokensService;
import CSC207.CSC207RestApi.service.Users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/users/register")
@RestController
public class Register {
    private final UsersService usersService;

    @Autowired
    public Register(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public void register(@RequestBody User user) {
        usersService.register(user);
    }
}
