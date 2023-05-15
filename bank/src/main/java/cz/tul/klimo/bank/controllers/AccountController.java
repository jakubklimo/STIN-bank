package cz.tul.klimo.bank.controllers;

import cz.tul.klimo.bank.database.AccountDatabase;
import cz.tul.klimo.bank.database.UserDatabase;
import cz.tul.klimo.bank.entity.Account;
import cz.tul.klimo.bank.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @Autowired
    private AccountDatabase accountDatabase;
    @Autowired
    private UserDatabase userDatabase;

    @GetMapping("/createAcc")
    public String showCreateAccPage(){
        return "createAcc";
    }

    @PostMapping("/createAcc")
    public String processCreateAcc(HttpServletRequest request, HttpSession session){
        String mena = request.getParameter("mena");
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/index";
        }
        Account account = new Account(mena, user);
        user.setAccount(account);
        accountDatabase.save(account);
        return "redirect:/home";
    }

    @GetMapping("/zavrit1")
    public String close(){
        return "redirect:/home";
    }
}
