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

    @GetMapping("/index")
    public String showLoginPage(@RequestParam(value = "userId", required = false) Integer userId, Model model){
        if(userId != null){
            model.addAttribute("userId", userId);
        }
        return "index";
    }

    @PostMapping("/index")
    public String processLogin(@RequestParam("klientNum") String klientNum, @RequestParam("heslo") String heslo, Model model, HttpSession session){
        User user = userService.getById(Integer.parseInt(klientNum));
        if (user == null || !user.getHeslo().equals(heslo)) {
            model.addAttribute("wrongLogin","Zadali jste špatné klientské číslo nebo heslo.");
            return "index";
        }else{
            session.setAttribute("user", user);
            return "redirect:/home";
        }
    }

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
