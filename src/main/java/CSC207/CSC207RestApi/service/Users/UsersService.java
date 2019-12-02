package CSC207.CSC207RestApi.service.Users;

import CSC207.CSC207RestApi.model.Score;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.User;

import java.util.List;

public interface UsersService {

    int addPerson(User user);

    List<User> getAllUsers();

    User getUser(String username);

    Token login(User user);

    int insertScore(Score score, String game);

    int register(User user);

    void updateStage(String username, String stage);

    void updateTimePlayed(String username, String newTimePlayed);
}
