package cz.tul.klimo.bank.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cz.tul.klimo.bank.entity.User;

@Service
public class UserDatabaseService {
    @Autowired
    private UserDatabase userDatabase;

    public User createUser(User user){
        return userDatabase.save(user);
    }

    public User getById(int userId){
        return userDatabase.findById(userId);
    }

    public int getMaxId(){
        return userDatabase.findMaxId();
    }

}
