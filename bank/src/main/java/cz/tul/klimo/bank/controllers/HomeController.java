package cz.tul.klimo.bank.controllers;

import cz.tul.klimo.bank.database.AccountDatabase;
import cz.tul.klimo.bank.entity.Currency;
import cz.tul.klimo.bank.service.CurrencyService;
import cz.tul.klimo.bank.service.UserDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import cz.tul.klimo.bank.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;

@Controller
public class HomeController {

    @Autowired
    private UserDatabaseService userService;
    @Autowired
    private AccountDatabase accountDatabase;
    @Autowired
    private CurrencyService currencyService;


    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) throws IOException, ParseException {
        User user = userService.getById(Integer.parseInt((String)session.getAttribute("klientNum")));
        if(user == null){
            return "redirect:/index";
        }
        session.setAttribute("user", user);
        model.addAttribute("jmeno", user.getJmeno());
        model.addAttribute("klientNum", user.getKlientNum());
        model.addAttribute("ucty", user.getAccounts());
        return "home";
    }

    @GetMapping("/vytvorUcet")
    public String createAcc(HttpSession session){
        if(session.getAttribute("user") == null){
            return "redirect:/index";
        }
        return "redirect:/createAcc";
    }

    @PostMapping("/vklad")
    public String deposit(@RequestParam("idUcet") String id, HttpSession session){
        if(session.getAttribute("user") == null){
            return "redirect:/index";
        }
        session.setAttribute("idUcet", id);
        return "redirect:/deposit";
    }

    @PostMapping("/platba")
    public String pay(@RequestParam("idUcet") String id, HttpSession session){
        if(session.getAttribute("user") == null){
            return "redirect:/index";
        }
        session.setAttribute("idUcet", id);
        return "redirect:/pay";
    }

    @PostMapping("/vypis")
    public String log(@RequestParam("idUcet") String id, HttpSession session){
        if(session.getAttribute("user") == null){
            return "redirect:/index";
        }
        session.setAttribute("idUcet", id);
        return "redirect:/transactionLog";
    }

    @GetMapping("/odhlasit")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }
}
