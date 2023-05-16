package cz.tul.klimo.bank.controllers;

import cz.tul.klimo.bank.service.UserDatabaseService;
import cz.tul.klimo.bank.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserDatabaseService userService;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    void processRegister() {
        // Arrange
        String jmeno = "John";
        String prijmeni = "Doe";
        String email = "johndoe@example.com";
        String heslo = "password123";
        User user = new User(jmeno, prijmeni, email, heslo);

        when(userService.getMaxId()).thenReturn(1);

        // Act
        String result = userController.processRegister(jmeno, prijmeni, email, heslo, redirectAttributes);

        // Assert
        verify(userService).createUser(user);
        verify(redirectAttributes).addAttribute("userId", 1);
        verifyNoMoreInteractions(userService, redirectAttributes);
        assertEquals("redirect:/index", result);
    }*/

    @Test
    void showRegisterPage_ShouldReturnRegisterView() {
        // Act
        String result = userController.showRegisterPage();

        // Assert
        assertEquals("register", result);
    }
}
