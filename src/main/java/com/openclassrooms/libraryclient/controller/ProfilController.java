package com.openclassrooms.libraryclient.controller;

import com.openclassrooms.libraryclient.model.Loan;
import com.openclassrooms.libraryclient.model.User;
import com.openclassrooms.libraryclient.proxy.LoanProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/profil")
public class ProfilController {

    Logger log = LoggerFactory.getLogger(ProfilController.class);

    private final String USER = "user"; // TODO : ppt
    private final String TOKEN_KEY = "auth-token"; // TODO : ppt

    @Autowired
    private LoanProxy loanProxy;

    @GetMapping
    public String getProfil(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        String bearerToken = (String) session.getAttribute(TOKEN_KEY);
        List<Loan> userLoans = loanProxy.getAllByUser(user.getId(), "Bearer " + bearerToken);
        model.addAttribute("loans", userLoans);
        model.addAttribute("user", user);

        return "profil";
    }
}
