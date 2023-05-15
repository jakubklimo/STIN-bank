package cz.tul.klimo.bank.controllers;

import cz.tul.klimo.bank.database.AccountDatabase;
import cz.tul.klimo.bank.database.TransactionDatabase;
import cz.tul.klimo.bank.entity.Account;
import cz.tul.klimo.bank.entity.Transaction;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TransactionController {
    @Autowired
    AccountDatabase accountDatabase;
    @Autowired
    TransactionDatabase transactionDatabase;

    @GetMapping("/deposit")
    public String showDepositPage(){
        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("castka") double castka, HttpSession session){
        if(session.getAttribute("idUcet") == null){
            return "redirect:/index";
        }
        int id = Integer.parseInt((String) session.getAttribute("idUcet"));
        Account account = accountDatabase.findById(id);
        Transaction transaction = new Transaction("Vklad", castka, account);
        account.vklad(castka);
        account.setTransaction(transaction);
        transactionDatabase.save(transaction);
        accountDatabase.save(account);
        session.removeAttribute("idUcet");
        return "redirect:/home";
    }

    @GetMapping("/pay")
    public String showPayPage(){
        return "pay";
    }

    @PostMapping("/pay")
    public String pay(@RequestParam("castka") double castka, @RequestParam("prijemce") String prijemce,HttpSession session, Model model){
        if(session.getAttribute("idUcet") == null){
            return "redirect:/index";
        }
        int id = Integer.parseInt((String) session.getAttribute("idUcet"));
        Account account = accountDatabase.findById(id);
        Transaction transaction = new Transaction("Platba", castka, Integer.parseInt(prijemce),account);
        if(!account.platba(castka)){
            model.addAttribute("money", "Na účtu není dostatek peněz.");
            return "pay";
        }else {
            account.setTransaction(transaction);
            transactionDatabase.save(transaction);
            accountDatabase.save(account);
            session.removeAttribute("idUcet");
            return "redirect:/home";
        }
    }

    @GetMapping("/transactionLog")
    public String showLogPage(HttpSession session, Model model){
        if(session.getAttribute("idUcet") == null){
            return "redirect:/index";
        }
        int id = Integer.parseInt((String) session.getAttribute("idUcet"));
        Account account = accountDatabase.findById(id);
        model.addAttribute("cisloUctu", id);
        model.addAttribute("transactions", account.getTransactions());
        return "transactionLog";
    }

    @GetMapping("/zavrit")
    public String close(HttpSession session){
        session.removeAttribute("idUcet");
        return "redirect:/home";
    }
}
