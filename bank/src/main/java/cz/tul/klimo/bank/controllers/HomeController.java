package cz.tul.klimo.bank.controllers;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import cz.tul.klimo.bank.entity.User;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("jmeno", user.getJmeno());
        model.addAttribute("klientNum", user.getKlientNum());
        return "home";
    }

    @GetMapping("/vytvorUcet")
    public String createAcc(){
        return "redirect:/createAcc";
    }

    @GetMapping("/odhlasit")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }
}
