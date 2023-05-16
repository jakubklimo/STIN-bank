package cz.tul.klimo.bank.service;

import cz.tul.klimo.bank.database.TransactionDatabase;
import cz.tul.klimo.bank.entity.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class TransactionDatabaseServiceTest {
    @Mock
    private TransactionDatabase transactionDatabase;

    @InjectMocks
    private TransactionDatabaseService transactionDatabaseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTransaction() {
        Transaction transaction = new Transaction();

        transactionDatabaseService.createTransaction(transaction);

        verify(transactionDatabase, times(1)).save(transaction);
    }

    @Test
    public void testGetTransactionById() {
        int transactionId = 1;
        Transaction transaction = new Transaction();
        when(transactionDatabase.findById(transactionId)).thenReturn(transaction);

        Transaction retrievedTransaction = transactionDatabaseService.getTransactionById(transactionId);

        Assertions.assertEquals(transaction, retrievedTransaction);
        verify(transactionDatabase, times(1)).findById(transactionId);
    }
}

