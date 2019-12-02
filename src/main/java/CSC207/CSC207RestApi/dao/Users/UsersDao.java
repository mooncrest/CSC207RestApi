package CSC207.CSC207RestApi.dao.Users;

import CSC207.CSC207RestApi.model.User;

import java.util.List;
import java.util.UUID;

public interface UsersDao {
    /**
     * inserts a user with a specific UUID
     * @param id the UUID of this user
     * @param user the users information
     * @return int representing whether this operation is successful
     */
    int insertUser(UUID id, User user);

    /**
     * Generates the UUID for the user here
     * @param user this users information
     * @return int representing whether this operation is successful
     */
    default int insertUser(User user) {
        // assume UUID is unique (very unlikely to have duplicates not sure what to do if there are)
        UUID id = UUID.randomUUID();
        user.setUserId(id);
        return insertUser(id, user);
    }

    /**
     * gets the list of all the users information
     * @return gets all the user info from the database
     */
    List<User> selectAllUsers();

    /**
     * gets the login users user info
     * @param user the users information used for login
     * @return returns the users usersname if successful
     */
    String getLoginUser(User user);

    /**
     * get the users user info object based off the user name
     * @param username this users username
     * @return the users user info object
     */
    User getUserInfo(String username);

    /**
     * updates the user in the database
     * @param updatedUser the user information to be updated based off username
     * @return a integer representing the status of this operation
     */
    int updateUser(User updatedUser);
}
