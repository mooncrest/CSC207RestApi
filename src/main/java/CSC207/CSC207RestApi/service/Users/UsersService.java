package CSC207.CSC207RestApi.service.Users;

import CSC207.CSC207RestApi.dao.Tokens.TokensDao;
import CSC207.CSC207RestApi.dao.Users.UsersDao;
import CSC207.CSC207RestApi.model.LoginInfo;
import CSC207.CSC207RestApi.model.Token;
import CSC207.CSC207RestApi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {
    private final UsersDao userDao;
    private final TokensDao tokensDao;

    @Autowired
    public UsersService(@Qualifier("jsonUserDao") UsersDao userDao,
                        @Qualifier("tokenDao") TokensDao tokensDao) {
        this.userDao = userDao;
        this.tokensDao = tokensDao;
    }

    public int addPerson(User user) {
        return userDao.insertPerson(user);
    }

    public List<User> getAllPeople() {
        return userDao.selectAllPeople();
    }

    public Token login(LoginInfo loginInfo) {
        String username = userDao.getUserInfo(loginInfo);
        if (username == null) {
            return null;
        }
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setUsername(username);
        tokensDao.addToken(token);
        return token;
    }
}
