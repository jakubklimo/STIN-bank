package cz.tul.klimo.bank.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import cz.tul.klimo.bank.entity.User;
import java.util.ArrayList;

@Controller
public class BankController {

    private ArrayList<User> users = new ArrayList<>();

    @GetMapping("/index")
    public String showLoginPage(){
        return "index";
    }

    @PostMapping("/index")
    public String processLogin(@RequestParam("klientNum") int klientNum, @RequestParam("heslo") String heslo){
        
        return "redirect:/index?error=true";
    }

    @GetMapping("/register")
    public String showRegisterPage(){
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam("jmeno") String jmeno, @RequestParam("prijmeni") String prijmeni, @RequestParam("email") String email, @RequestParam("heslo") String heslo){
        if(!(jmeno.equals("") && prijmeni.equals("") && email.equals("") && heslo.equals(""))){
            users.add(new User(321, jmeno, prijmeni, email, heslo));
            return "redirect:/index";
        }else {
            return null;
        }
    }

    @GetMapping("/home")
    public String showHomePage(){
        return "home";
    }
}
