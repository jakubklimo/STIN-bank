package cz.tul.klimo.bank.service;

import cz.tul.klimo.bank.database.AccountDatabase;
import cz.tul.klimo.bank.entity.Account;
import cz.tul.klimo.bank.service.AccountDatabaseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class AccountDatabaseServiceTest {
    @Mock
    private AccountDatabase accountDatabase;

    @InjectMocks
    private AccountDatabaseService accountDatabaseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAccount() {
        Account account = new Account();

        accountDatabaseService.createAccount(account);

        verify(accountDatabase, times(1)).save(account);
    }

    @Test
    public void testGetAccountById() {
        int accountId = 1;
        Account account = new Account();
        when(accountDatabase.findById(accountId)).thenReturn(account);

        Account retrievedAccount = accountDatabaseService.getAccountById(accountId);

        Assertions.assertEquals(account, retrievedAccount);
        verify(accountDatabase, times(1)).findById(accountId);
    }
}

