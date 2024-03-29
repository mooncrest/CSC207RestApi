package CSC207.CSC207RestApi.api.Users;

import CSC207.CSC207RestApi.model.User;
import CSC207.CSC207RestApi.service.Users.JsonUsersService;
import CSC207.CSC207RestApi.service.Users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public Register(@Qualifier("jsonUsersService") UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * tries to register this user
     * @param user the users information
     * @return the status whether this user could be registered or not.
     */
    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) {
        // add check for blank name
        int status = usersService.register(user);
        if (status == -1) {
            return new ResponseEntity<>("Status: taken", HttpStatus.PRECONDITION_FAILED);
        }

        else if (status == 1) {
            return new ResponseEntity<>("{Status: registered}", HttpStatus.OK);
        }
        return null;
    }
}
