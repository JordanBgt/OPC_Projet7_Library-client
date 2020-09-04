package com.openclassrooms.libraryclient.controller;

import com.openclassrooms.libraryclient.model.Loan;
import com.openclassrooms.libraryclient.model.User;
import com.openclassrooms.libraryclient.proxy.LoanProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/profil")
public class ProfilController {

    private final String USER = "user"; // TODO : ppt
    private final String TOKEN_KEY = "auth-token"; // TODO : ppt

    @Autowired
    private LoanProxy loanProxy;

    @GetMapping
    public String getProfil(HttpSession session, Model model) {
        User user = (User) session.getAttribute(USER);
        String bearerToken = (String) session.getAttribute(TOKEN_KEY);
        List<Loan> userLoans = loanProxy.getAllByUser(user.getId(), "Bearer " + bearerToken);
        model.addAttribute("loans", userLoans);
        model.addAttribute("user", user);

        return "profil";
    }

    @GetMapping("/{id}/renewal")
    public ModelAndView renewLoan(@PathVariable Long id, HttpSession session) {
        String bearerToken = (String) session.getAttribute(TOKEN_KEY);
        loanProxy.renewLoan(id, "Bearer " + bearerToken);
        return new ModelAndView("redirect:/profil");
    }
}
