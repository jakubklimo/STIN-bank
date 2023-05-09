package cz.tul.klimo.bank.controllers;

import cz.tul.klimo.bank.database.AccountDatabase;
import cz.tul.klimo.bank.database.AccountDatabaseService;
import cz.tul.klimo.bank.database.UserDatabaseService;
import cz.tul.klimo.bank.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import cz.tul.klimo.bank.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    private UserDatabaseService userService;
    @Autowired
    private AccountDatabase accountDatabase;

    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model){
        User user = userService.getById(Integer.parseInt((String)session.getAttribute("klientNum")));
        session.setAttribute("user", user);
        model.addAttribute("jmeno", user.getJmeno());
        model.addAttribute("klientNum", user.getKlientNum());
        model.addAttribute("ucty", user.getAccounts());
        return "home";
    }

    @GetMapping("/vytvorUcet")
    public String createAcc(){
        return "redirect:/createAcc";
    }

    @PostMapping("/vklad")
    public String deposit(@RequestParam("idUcet") String id, HttpSession session){
        session.setAttribute("idUcet", id);
        return "redirect:/deposit";
    }

    @PostMapping("/platba")
    public String pay(@RequestParam("idUcet") String id, HttpSession session){
        session.setAttribute("idUcet", id);
        return "redirect:/pay";
    }

    @PostMapping("/vypis")
    public String log(@RequestParam("idUcet") String id, HttpSession session){
        session.setAttribute("idUcet", id);
        return "redirect:/transactionLog";
    }

    @PostMapping("/smazat")
    public String delete(@RequestParam("idUcet") String id){
        accountDatabase.deleteById(Integer.parseInt(id));
        return "redirect:/home";
    }

    @GetMapping("/odhlasit")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }
}
