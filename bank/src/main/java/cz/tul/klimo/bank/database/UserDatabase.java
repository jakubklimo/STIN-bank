package cz.tul.klimo.bank.database;

import cz.tul.klimo.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDatabase extends JpaRepository<User, Integer>{
    User findById(int id);
    @Query("SELECT MAX(u.id) FROM Users u")
    Integer findMaxId();
}
