package cz.tul.klimo.bank.controllers;

import cz.tul.klimo.bank.entity.Account;
import cz.tul.klimo.bank.entity.Currency;
import cz.tul.klimo.bank.entity.Transaction;
import cz.tul.klimo.bank.service.AccountDatabaseService;
import cz.tul.klimo.bank.service.CurrencyService;
import cz.tul.klimo.bank.service.TransactionDatabaseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {
    @Mock
    private AccountDatabaseService accountDatabase;
    @Mock
    private TransactionDatabaseService transactionDatabase;
    @Mock
    private CurrencyService currencyService;
    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest request;
    @Mock
    private Model model;
    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /*@Test
    public void testShowLogPage() {
        // Mock session attribute
        when(session.getAttribute("idUcet")).thenReturn("1");

        // Mock account and transactions
        Account account = new Account();
        account.setTransaction(new Transaction("Vklad", 100, account));
        account.setTransaction(new Transaction("Platba", 50, 2, account));
        when(accountDatabase.getAccountById(1)).thenReturn(account);

        // Invoke the showLogPage method
        String result = transactionController.showLogPage(session, model);

        // Verify that the model attributes were set and the transaction log page was returned
        assertEquals("transactionLog", result);
        verify(model, times(1)).addAttribute("cisloUctu", 1);
        verify(model, times(1)).addAttribute("transactions", account.getTransactions());
    }*/
}

