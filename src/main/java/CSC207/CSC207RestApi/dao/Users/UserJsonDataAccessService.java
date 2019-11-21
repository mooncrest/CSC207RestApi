package CSC207.CSC207RestApi.dao.Users;

import CSC207.CSC207RestApi.dao.JsonHelper;
import CSC207.CSC207RestApi.model.User;
import CSC207.CSC207RestApi.model.UserDataBase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("jsonUserDao")
public class UserJsonDataAccessService implements UsersDao {
    private final JsonHelper<UserDataBase> jsonHelper = new JsonHelper<>(UserDataBase.class, "Users.json");

    @Override
    public int insertUser(UUID id, User user) {
        UserDataBase DB = jsonHelper.ReadJson();
        List<User> users =  DB.getUsers();
        user.setUserId(id);
        users.add(user);
        jsonHelper.writeJson(DB);
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return jsonHelper.ReadJson().getUsers();
    }

    @Override
    public String getLoginUser(User loginUser) {
        String email = loginUser.getEmail();
        String password = loginUser.getPassword();
        UserDataBase DB = jsonHelper.ReadJson();
        for (User user : DB.getUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user.getUsername();
            }
        }
        return null;
    }

    @Override
    public User getUserInfo(String username) {
        UserDataBase DB = jsonHelper.ReadJson();
        for (User user : DB.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    // 1 means successfully added -3 means user not found
    @Override
    public int updateUser(User updatedUser) {
        UserDataBase DB = jsonHelper.ReadJson();
        for (User user : DB.getUsers()) {
            if (user.getUsername().equals(updatedUser.getUsername())) {
                List<User> users = DB.getUsers();
                users.remove(user);
                users.add(updatedUser);
                jsonHelper.writeJson(DB);
                return 1;
            }
        }
        return -3;
    }
}
