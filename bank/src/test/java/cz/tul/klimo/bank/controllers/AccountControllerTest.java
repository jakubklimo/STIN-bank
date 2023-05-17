package cz.tul.klimo.bank.controllers;

import cz.tul.klimo.bank.database.UserDatabase;
import cz.tul.klimo.bank.entity.Account;
import cz.tul.klimo.bank.entity.User;
import cz.tul.klimo.bank.service.AccountDatabaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountControllerTest {

    @Mock
    private AccountDatabaseService accountDatabase;

    @Mock
    private UserDatabase userDatabase;

    @InjectMocks
    private AccountController accountController;

    private MockHttpServletRequest request;
    private MockHttpSession session;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);
    }

    @Test
    public void testShowCreateAccPage() {
        String result = accountController.showCreateAccPage();
        assertEquals("createAcc", result);
    }

    /*@Test
    public void testProcessCreateAcc_withInvalidUser() {
        // Set up
        session.setAttribute("user", null);
        request.setParameter("mena", "CZK");

        // Execute
        String result = accountController.processCreateAcc(request, session);

        // Verify
        //verifyZeroInteractions(accountDatabase);
        assertEquals("redirect:/index", result);
    }*/

    @Test
    public void testClose() {
        String result = accountController.close();
        assertEquals("redirect:/home", result);
    }
}

