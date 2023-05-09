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
        int id = Integer.parseInt((String) session.getAttribute("idUcet"));
        Account account = accountDatabase.findById(id);
        Transaction transaction = new Transaction('V', castka, account);
        account.vklad(castka);
        account.setTransaction(transaction);
        transactionDatabase.save(transaction);
        accountDatabase.save(account);
        session.removeAttribute("idUcet");
        return "redirect:/home";
    }
}
