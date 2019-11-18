package CSC207.CSC207RestApi.dao.Users;

import CSC207.CSC207RestApi.model.User;

import java.util.List;
import java.util.UUID;

public interface UsersDao {
    int insertPerson(UUID id, User user);

    default int insertPerson(User user) {
        // assume UUID is unique (very unlikely to have duplicates not sure what to do if there are)
        UUID id = UUID.randomUUID();
        user.setUserId(id);
        return insertPerson(id, user);
    }

    List<User> selectAllPeople();
}
