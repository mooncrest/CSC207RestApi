package CSC207.CSC207RestApi.dao.Users;

import CSC207.CSC207RestApi.dao.JsonHelper;
import CSC207.CSC207RestApi.model.LeaderBoardDataBase;
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
}
