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

    /**
     * inserts a user with a specific UUID
     * @param id the UUID of this user
     * @param user the users information
     * @return int representing whether this operation is successful
     */
    @Override
    public int insertUser(UUID id, User user) {
        UserDataBase DB = jsonHelper.ReadJson();
        List<User> users =  DB.getUsers();
        user.setUserId(id);
        users.add(user);
        jsonHelper.writeJson(DB);
        return 1;
    }

    /**
     * gets the list of all the users information
     * @return gets all the user info from the database
     */
    @Override
    public List<User> selectAllUsers() {
        return jsonHelper.ReadJson().getUsers();
    }

    /**
     * gets the login users user info
     * @param loginUser the users information used for login
     * @return returns the users usersname if successful
     */
    @Override
    public String getLoginUser(User loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        UserDataBase DB = jsonHelper.ReadJson();
        for (User user : DB.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user.getUsername();
            }
        }
        return null;
    }

    /**
     * get the users user info object based off the user name
     * @param username this users username
     * @return the users user info object
     */
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

    /**
     * updates the user in the database
     * @param updatedUser the user information to be updated based off username
     * @return a integer representing the status of this operation
     */
    @Override
    public int updateUser(User updatedUser) {
        // 1 means successfully added -3 means user not found
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
