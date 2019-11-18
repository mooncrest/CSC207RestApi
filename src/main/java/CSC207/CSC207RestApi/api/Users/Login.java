package CSC207.CSC207RestApi.api.Users;

import CSC207.CSC207RestApi.model.LoginInfo;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.service.Users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/users/login")
@RestController
public class Login {

    private final UsersService usersService;

    @Autowired
    public Login(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<Token> login(@RequestBody LoginInfo loginInfo) {
        Token token = usersService.login(loginInfo);
        return new ResponseEntity<Token>(token, HttpStatus.OK);
    }
}
