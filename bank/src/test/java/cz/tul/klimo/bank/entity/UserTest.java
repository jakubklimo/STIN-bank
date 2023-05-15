package cz.tul.klimo.bank.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("John", "Doe", "john.doe@example.com", "password");
    }

    @Test
    public void testGetKlientNum() {
        int klientNum = user.getKlientNum();
        Assertions.assertEquals(0, klientNum); // Předpokládáme, že nově vytvořený uživatel bude mít ID 0
    }

    @Test
    public void testGetHeslo() {
        String heslo = user.getHeslo();
        Assertions.assertEquals("password", heslo);
    }

    @Test
    public void testGetId() {
        int id = user.getId();
        Assertions.assertEquals(0, id); // Předpokládáme, že nově vytvořený uživatel bude mít ID 0
    }

    @Test
    public void testSetId() {
        user.setId(1);
        int id = user.getId();
        Assertions.assertEquals(1, id);
    }

    @Test
    public void testSetJmeno() {
        user.setJmeno("Jane");
        String jmeno = user.getJmeno();
        Assertions.assertEquals("Jane", jmeno);
    }

    @Test
    public void testGetPrijmeni() {
        String prijmeni = user.getPrijmeni();
        Assertions.assertEquals("Doe", prijmeni);
    }

    @Test
    public void testSetPrijmeni() {
        user.setPrijmeni("Smith");
        String prijmeni = user.getPrijmeni();
        Assertions.assertEquals("Smith", prijmeni);
    }

    @Test
    public void testGetEmail() {
        String email = user.getEmail();
        Assertions.assertEquals("john.doe@example.com", email);
    }

    @Test
    public void testSetEmail() {
        user.setEmail("jane.smith@example.com");
        String email = user.getEmail();
        Assertions.assertEquals("jane.smith@example.com", email);
    }

    @Test
    public void testSetHeslo() {
        user.setHeslo("newpassword");
        String heslo = user.getHeslo();
        Assertions.assertEquals("newpassword", heslo);
    }

    @Test
    public void testSetAccounts() {
        Account account1 = new Account();
        Account account2 = new Account();
        List<Account> accounts = List.of(account1, account2);

        user.setAccounts(accounts);

        List<Account> userAccounts = user.getAccounts();
        Assertions.assertEquals(accounts, userAccounts);
    }

    @Test
    public void testSetAccount() {
        Account account = new Account();
        user.setAccount(account);

        List<Account> accounts = user.getAccounts();
        Assertions.assertEquals(1, accounts.size());
        Assertions.assertEquals(account, accounts.get(0));
    }
}

