package CSC207.CSC207RestApi.dao.Users;

import CSC207.CSC207RestApi.dao.JsonHelper;
import CSC207.CSC207RestApi.model.LeaderBoardDataBase;
import CSC207.CSC207RestApi.model.LoginInfo;
import CSC207.CSC207RestApi.model.User;
import CSC207.CSC207RestApi.model.UserDataBase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("jsonUserDao")
public class UserJsonDataAccessService implements UsersDao {
    private final String fileName = "Users.json";
    private final String jsonDBType = "user";

    @Override
    public int insertPerson(UUID id, User user) {
        UserDataBase DB = (UserDataBase)JsonHelper.ReadJson(fileName, jsonDBType);
        List<User> users =  DB.getUsers();
        user.setUserId(id);
        users.add(user);
        JsonHelper.writeJson(fileName, jsonDBType, DB);
        return 1;
    }

    @Override
    public List<User> selectAllPeople() {
        return ((UserDataBase)JsonHelper.ReadJson(fileName, jsonDBType)).getUsers();
    }

    @Override
    public String getUserInfo(LoginInfo loginInfo) {
        String email = loginInfo.getEmail();
        String password = loginInfo.getPassword();
        // make DB an iterator
        UserDataBase DB = (UserDataBase)JsonHelper.ReadJson(fileName, jsonDBType);
        for (User user : DB.getUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user.getUsername();
            }
        }
        return null;
    }

    @Override
    public User getUserInfo(String username) {
        UserDataBase DB = (UserDataBase)JsonHelper.ReadJson(fileName, jsonDBType);
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
        UserDataBase DB = (UserDataBase)JsonHelper.ReadJson(fileName, jsonDBType);
        for (User user : DB.getUsers()) {
            if (user.getUsername().equals(updatedUser.getUsername())) {
                List<User> users = DB.getUsers();
                users.remove(user);
                users.add(updatedUser);
                JsonHelper.writeJson(fileName, jsonDBType, DB);
                return 1;
            }
        }
        return -3;
    }
}
