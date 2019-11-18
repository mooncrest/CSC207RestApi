package CSC207.CSC207RestApi.model;

import java.util.ArrayList;
import java.util.List;

public class UserDataBase {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
