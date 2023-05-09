package cz.tul.klimo.bank.controllers;

import cz.tul.klimo.bank.database.UserDatabase;
import cz.tul.klimo.bank.database.UserDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import cz.tul.klimo.bank.entity.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserDatabaseService userService;

    @GetMapping("/register")
    public String showRegisterPage(){
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam("jmeno") String jmeno, @RequestParam("prijmeni") String prijmeni, @RequestParam("email") String email, @RequestParam("heslo") String heslo, RedirectAttributes redirectAttributes){
        User user = new User(jmeno, prijmeni, email, heslo);
        userService.createUser(user);
        redirectAttributes.addAttribute("userId", userService.getMaxId());
        return "redirect:/index";
    }
}
