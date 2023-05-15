package cz.tul.klimo.bank.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AccountTest {

    private Account account;
    private User user;
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        user = new User();
        account = new Account("USD", user);
        transaction = new Transaction();
    }

    @Test
    public void testGetMena() {
        String mena = account.getMena();
        Assertions.assertEquals("USD", mena);
    }

    @Test
    public void testSetMena() {
        account.setMena("CZK");
        String mena = account.getMena();
        Assertions.assertEquals("CZK", mena);
    }

    @Test
    public void testGetZustatek() {
        double zustatek = account.getZustatek();
        Assertions.assertEquals(0, zustatek);
    }

    @Test
    public void testSetZustatek() {
        account.setZustatek(20.0);
        double zustatek = account.getZustatek();
        Assertions.assertEquals(20.0, zustatek);
    }

    @Test
    public void testGetId() {
        int id = account.getId();
        Assertions.assertEquals(0, id);
    }

    @Test
    public void testSetId() {
        account.setId(5);
        int id = account.getId();
        Assertions.assertEquals(5, id);
    }

    @Test
    public void testVklad() {
        account.vklad(100.0);
        double zustatek = account.getZustatek();
        Assertions.assertEquals(100.0, zustatek);
    }

    @Test
    public void testPlatbaWithSufficientBalance() {
        account.vklad(100.0);
        boolean result = account.platba(50.0);
        double zustatek = account.getZustatek();
        Assertions.assertTrue(result);
        Assertions.assertEquals(50.0, zustatek);
    }

    @Test
    public void testPlatbaWithInsufficientBalance() {
        account.vklad(100.0);
        boolean result = account.platba(150.0);
        double zustatek = account.getZustatek();
        Assertions.assertFalse(result);
        Assertions.assertEquals(100.0, zustatek);
    }

    @Test
    public void testGetUser() {
        User retrievedUser = account.getUser();
        Assertions.assertEquals(user, retrievedUser);
    }

    @Test
    public void testSetUser() {
        User newUser = new User();
        account.setUser(newUser);
        User retrievedUser = account.getUser();
        Assertions.assertEquals(newUser, retrievedUser);
    }

    @Test
    public void testConstructorWithMenaAndUser() {
        Assertions.assertEquals("USD", account.getMena());
        Assertions.assertEquals(user, account.getUser());
        Assertions.assertEquals(0.0, account.getZustatek());
        Assertions.assertEquals(0, account.getId());
        List<Transaction> transactions = account.getTransactions();
        Assertions.assertNotNull(transactions);
        Assertions.assertTrue(transactions.isEmpty());
    }

    @Test
    public void testGetTransactions() {
        List<Transaction> transactions = account.getTransactions();
        Assertions.assertNotNull(transactions);
        Assertions.assertEquals(0, transactions.size());
    }

    @Test
    public void testDefaultConstructor() {
        Account account = new Account();
        Assertions.assertNotNull(account);
        Assertions.assertNull(account.getMena());
        Assertions.assertEquals(0.0, account.getZustatek());
        Assertions.assertNull(account.getUser());
        Assertions.assertNotNull(account.getTransactions());
        Assertions.assertEquals(0, account.getTransactions().size());
    }

    @Test
    public void testSetTransaction() {
        Account account = new Account("USD", new User());
        Transaction transaction = new Transaction("Platba", 100.0, account);

        account.setTransaction(transaction);

        List<Transaction> transactions = account.getTransactions();
        Assertions.assertEquals(1, transactions.size());
        Assertions.assertTrue(transactions.contains(transaction));
    }
}

