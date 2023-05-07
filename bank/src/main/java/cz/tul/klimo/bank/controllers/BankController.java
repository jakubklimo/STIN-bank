package cz.tul.klimo.bank.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import cz.tul.klimo.bank.entity.User;
import java.util.ArrayList;
import jakarta.servlet.http.HttpSession;

@Controller
public class BankController {

    private ArrayList<User> users = new ArrayList<>();

    @GetMapping("/index")
    public String showLoginPage(){
        return "index";
    }

    @PostMapping("/index")
    public String processLogin(@RequestParam("klientNum") String klientNum, @RequestParam("heslo") String heslo, Model model, HttpSession session){
        users.add(new User(1, "admin", "admin", "admin@admin", "buch"));
        if(klientNum.equals("") || heslo.equals("")){
            model.addAttribute("warningLogin", "Zadejete všechny hodnoty.");
            return "index";
        }
        User userIn = null;
        for(User user:users){
            if(user.getKlientNum() == Integer.parseInt(klientNum)){
                userIn = user;
            }
        }
        if(userIn != null && userIn.getKlientNum() == Integer.parseInt(klientNum) && userIn.getHeslo().equals(heslo)){
            session.setAttribute("user", userIn);
            return "redirect:/home";
        }else{
            model.addAttribute("wrongLogin", "Zadali jste špatné klientské číslo nebo heslo.");
            return "index";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage(){
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam("jmeno") String jmeno, @RequestParam("prijmeni") String prijmeni, @RequestParam("email") String email, @RequestParam("heslo") String heslo, Model model){
        if(jmeno.equals("") || prijmeni.equals("") || email.equals("") || heslo.equals("")){
            model.addAttribute("warningRegister", "Zadejete všechny hodnoty.");
            return "register";
        }
        users.add(new User(123, jmeno, prijmeni, email, heslo));
        return "redirect:/index";
    }
}
