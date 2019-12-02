package CSC207.CSC207RestApi.api.Users;

import CSC207.CSC207RestApi.model.User;
import CSC207.CSC207RestApi.service.Users.JsonUsersService;
import CSC207.CSC207RestApi.service.Users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/users/testing")
@RestController
public class UsersTester {

    private final UsersService usersService;

    @Autowired
    public UsersTester(@Qualifier("jsonUsersService") UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public void addPerson(@RequestBody User user) {
        usersService.addPerson(user);
    }

    @GetMapping
    public List<User> getAllPerson() {
        return usersService.getAllUsers();
    }
}