package cz.tul.klimo.bank.controllers;

import cz.tul.klimo.bank.service.UserDatabaseService;
import cz.tul.klimo.bank.entity.User;
import cz.tul.klimo.bank.functions.GenerateCode;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserDatabaseService userService;
    @Autowired
    private JavaMailSender javaMailSender;

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
        }else if(klientNum.equals("8") && user.getHeslo().equals(heslo)){
            session.setAttribute("klientNum", klientNum);
            return "redirect:/home";
        }else{
            SimpleMailMessage email = new SimpleMailMessage();
            String code = GenerateCode.getRandomNumberString();
            email.setFrom("klimobanka@seznam.cz");
            email.setTo(user.getEmail());
            email.setSubject("KlimoBank - verifikace");
            email.setText(code);
            javaMailSender.send(email);
            session.setAttribute("kod", code);
            session.setAttribute("klientNum", klientNum);
            return "redirect:/verification";
        }
    }

    @GetMapping("/verification")
    public String showVeriPage(){
        return "verification";
    }

    @PostMapping("/verification")
    public String processVerification(@RequestParam("verifikace") String kod, Model model, HttpSession session){
        String code = (String)session.getAttribute("kod");
        if(kod.equals(code)){
            return "redirect:/home";
        }else{
            model.addAttribute("wrongCode", "Zadali jste špatný kód.");
            return "verification";
        }
    }
}
