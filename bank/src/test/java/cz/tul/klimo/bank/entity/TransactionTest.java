package cz.tul.klimo.bank.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TransactionTest {

    private Transaction transaction;
    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account("USD", new User());
        transaction = new Transaction("Platba", 100.0, account);
    }

    @Test
    public void testGetId() {
        int id = transaction.getId();
        Assertions.assertEquals(0, id);
    }

    @Test
    public void testSetId() {
        transaction.setId(1);
        int id = transaction.getId();
        Assertions.assertEquals(1, id);
    }

    @Test
    public void testGetTyp() {
        String typ = transaction.getTyp();
        Assertions.assertEquals("Platba", typ);
    }

    @Test
    public void testSetTyp(){
        transaction.setTyp("Vklad");
        String typ = transaction.getTyp();
        Assertions.assertEquals("Vklad", typ);
    }

    @Test
    public void testGetCastka() {
        double castka = transaction.getCastka();
        Assertions.assertEquals(100.0, castka);
    }

    @Test
    public void testSetCastka(){
        transaction.setCastka(230.0);
        double castka = transaction.getCastka();
        Assertions.assertEquals(230.0, castka);
    }

    @Test
    public void testGetPrijemce() {
        int prijemce = transaction.getPrijemce();
        Assertions.assertEquals(0, prijemce);
    }

    @Test
    public void testSetPrijemce(){
        transaction.setPrijemce(586);
        int prijemce = transaction.getPrijemce();
        Assertions.assertEquals(586, prijemce);
    }

    @Test
    public void testGetDatum() {
        LocalDateTime datum = transaction.getDatum();
        Assertions.assertNotNull(datum);
    }

    @Test
    public void testSetDatum(){
        LocalDateTime newDate = LocalDateTime.now().minusDays(1);
        transaction.setDatum(newDate);
        Assertions.assertEquals(newDate.getDayOfYear(), transaction.getDatum().getDayOfYear());
    }

    @Test
    public void testGetAccount() {
        Account transactionAccount = transaction.getAccount();
        Assertions.assertEquals(account, transactionAccount);
    }

    @Test
    public void testSetAccount(){
        transaction.setAccount(new Account("CZK", new User()));
        Account transactionAccount = transaction.getAccount();
        Assertions.assertEquals(transaction.getAccount(), transactionAccount);
    }

    @Test
    public void testConstructorWithFourArguments() {
        LocalDateTime customDateTime = LocalDateTime.of(2022, 1, 1, 10, 30);
        transaction = new Transaction("Výběr", 50.0, account);
        transaction.setDatum(customDateTime);
        Assertions.assertEquals("Výběr", transaction.getTyp());
        Assertions.assertEquals(50.0, transaction.getCastka());
        Assertions.assertEquals(0, transaction.getPrijemce());
        Assertions.assertEquals(customDateTime, transaction.getDatum());
        Assertions.assertEquals(account, transaction.getAccount());
    }

    @Test
    public void testConstructorWithThreeArguments() {
        transaction = new Transaction("Platba", 100.0, 123456, account);
        Assertions.assertEquals("Platba", transaction.getTyp());
        Assertions.assertEquals(100.0, transaction.getCastka());
        Assertions.assertEquals(123456, transaction.getPrijemce());
        Assertions.assertNotNull(transaction.getDatum());
        Assertions.assertEquals(account, transaction.getAccount());
    }

    @Test
    public void testConstructorWithDefaultValues() {
        transaction = new Transaction();
        Assertions.assertNull(transaction.getTyp());
        Assertions.assertEquals(0.0, transaction.getCastka());
        Assertions.assertEquals(0, transaction.getPrijemce());
        Assertions.assertNull(transaction.getDatum());
        Assertions.assertNull(transaction.getAccount());
    }
}

