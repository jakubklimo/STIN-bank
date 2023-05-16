package cz.tul.klimo.bank.controllers;

import cz.tul.klimo.bank.database.AccountDatabase;
import cz.tul.klimo.bank.database.TransactionDatabase;
import cz.tul.klimo.bank.entity.Account;
import cz.tul.klimo.bank.entity.Currency;
import cz.tul.klimo.bank.entity.Transaction;
import cz.tul.klimo.bank.service.AccountDatabaseService;
import cz.tul.klimo.bank.service.CurrencyService;
import cz.tul.klimo.bank.service.TransactionDatabaseService;
import jakarta.servlet.http.HttpServletRequest;
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
    AccountDatabaseService accountDatabase;
    @Autowired
    TransactionDatabaseService transactionDatabase;
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/deposit")
    public String showDepositPage(){
        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("castka") double castka, HttpSession session, HttpServletRequest request){
        if(session.getAttribute("idUcet") == null){
            return "redirect:/index";
        }
        String currency = request.getParameter("currency");
        int id = Integer.parseInt((String) session.getAttribute("idUcet"));
        Account account = accountDatabase.getAccountById(id);
        Transaction transaction = new Transaction("Vklad", castka, account);
        transaction.setCurrency(currency);

        if(account.getMena().equalsIgnoreCase(currency)){
            account.vklad(castka);
        } else if (account.getMena().equalsIgnoreCase("CZK") && !currency.equalsIgnoreCase("CZK")) {
            Currency currencyKurz = currencyService.getKurz(currency);
            double castkaNew = (currencyKurz.getKurz() / currencyKurz.getMnozstvi()) * castka;
            account.vklad(castkaNew);
        } else if (currency.equalsIgnoreCase("CZK") && !account.getMena().equalsIgnoreCase("CZK")) {
            Currency currencyKurz = currencyService.getKurz(account.getMena());
            double castkaNew = castka / (currencyKurz.getKurz() / currencyKurz.getMnozstvi());
            account.vklad(castkaNew);
        } else {
            Currency currencyVklad = currencyService.getKurz(currency);
            Currency currencyUcet = currencyService.getKurz(account.getMena());

            double castkaCzk = (currencyVklad.getKurz() / currencyVklad.getMnozstvi()) * castka;
            double castkaNew = castkaCzk / (currencyUcet.getKurz() / currencyUcet.getMnozstvi());

            account.vklad(castkaNew);
        }
        account.setTransaction(transaction);
        transactionDatabase.createTransaction(transaction);
        accountDatabase.createAccount(account);

        session.removeAttribute("idUcet");
        return "redirect:/home";
    }

    @GetMapping("/pay")
    public String showPayPage(){
        return "pay";
    }

    @PostMapping("/pay")
    public String pay(@RequestParam("castka") double castka, @RequestParam("prijemce") String prijemce,HttpSession session, Model model, HttpServletRequest request){
        if(session.getAttribute("idUcet") == null){
            return "redirect:/index";
        }
        String currency = request.getParameter("currency");
        int id = Integer.parseInt((String) session.getAttribute("idUcet"));
        Account account = accountDatabase.getAccountById(id);
        Transaction transaction = new Transaction("Platba", castka, Integer.parseInt(prijemce),account);
        transaction.setCurrency(currency);


        if(account.getMena().equalsIgnoreCase(currency)){
            castka = castka;
        } else if (account.getMena().equalsIgnoreCase("CZK") && !currency.equalsIgnoreCase("CZK")) {
            Currency currencyKurz = currencyService.getKurz(currency);
            castka = (currencyKurz.getKurz() / currencyKurz.getMnozstvi()) * castka;

        } else if (currency.equalsIgnoreCase("CZK") && !account.getMena().equalsIgnoreCase("CZK")) {
            Currency currencyKurz = currencyService.getKurz(account.getMena());
            castka = castka / (currencyKurz.getKurz() / currencyKurz.getMnozstvi());
        } else {
            Currency currencyVklad = currencyService.getKurz(currency);
            Currency currencyUcet = currencyService.getKurz(account.getMena());

            double castkaCzk = (currencyVklad.getKurz() / currencyVklad.getMnozstvi()) * castka;
            castka = castkaCzk / (currencyUcet.getKurz() / currencyUcet.getMnozstvi());
        }

        if(!account.platba(castka)){
            model.addAttribute("money", "Na účtu není dostatek peněz.");
            return "pay";
        }else {
            account.setTransaction(transaction);
            transactionDatabase.createTransaction(transaction);
            accountDatabase.createAccount(account);
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
        Account account = accountDatabase.getAccountById(id);
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
