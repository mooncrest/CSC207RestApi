package CSC207.CSC207RestApi.dao.Users;

import CSC207.CSC207RestApi.model.User;

import java.util.List;
import java.util.UUID;

public interface UsersDao {
    public int insertUser(UUID id, User user);

    default int insertUser(User user) {
        // assume UUID is unique (very unlikely to have duplicates not sure what to do if there are)
        UUID id = UUID.randomUUID();
        user.setUserId(id);
        return insertUser(id, user);
    }

    public List<User> selectAllUsers();

    public String getLoginUser(User user);

    public User getUserInfo(String username);

    public int updateUser(User updatedUser);
}
