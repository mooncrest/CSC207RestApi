package CSC207.CSC207RestApi.service.Users;

import CSC207.CSC207RestApi.dao.Users.UsersDao;
import CSC207.CSC207RestApi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private final UsersDao userDao;

    @Autowired
    public UsersService(@Qualifier("jsonUserDao") UsersDao userDao) {
        this.userDao = userDao;
    }

    public int addPerson(User user) {
        return userDao.insertPerson(user);
    }

    public List<User> getAllPeople() {
        return userDao.selectAllPeople();
    }

//    public Optional<Person> getPersonById(UUID id) {
//        return personDao.selectPersonById(id);
//    }
}
