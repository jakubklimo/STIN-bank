package cz.tul.klimo.bank.database;

import cz.tul.klimo.bank.entity.User;
import cz.tul.klimo.bank.service.UserDatabaseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class UserDatabaseServiceTest {
    @Mock
    private UserDatabase userDatabase;

    @InjectMocks
    private UserDatabaseService userDatabaseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        when(userDatabase.save(user)).thenReturn(user);

        User createdUser = userDatabaseService.createUser(user);

        Assertions.assertEquals(user, createdUser);
        verify(userDatabase, times(1)).save(user);
    }

    @Test
    public void testGetById() {
        int userId = 1;
        User user = new User();
        when(userDatabase.findById(userId)).thenReturn(user);

        User retrievedUser = userDatabaseService.getById(userId);

        Assertions.assertEquals(user, retrievedUser);
        verify(userDatabase, times(1)).findById(userId);
    }

    @Test
    public void testGetMaxId() {
        int maxId = 100;
        when(userDatabase.findMaxId()).thenReturn(maxId);

        int retrievedMaxId = userDatabaseService.getMaxId();

        Assertions.assertEquals(maxId, retrievedMaxId);
        verify(userDatabase, times(1)).findMaxId();
    }
}

