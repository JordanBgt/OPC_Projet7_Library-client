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

/**
 * Controller to display the profile page
 *
 * @see LoanProxy
 */
@Controller
@RequestMapping("/profil")
public class ProfilController {

    @Autowired
    private LoanProxy loanProxy;

    /**
     * Method to display profil page
     *
     * @param session the session
     * @param model model which supply attributes used for rendering views
     *
     * @return name of the requested jsp
     * @see LoanProxy#getAllByUser(Long, String)
     */
    @GetMapping
    public String getProfil(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        String bearerToken = (String) session.getAttribute("auth-token");
        List<Loan> userLoans = loanProxy.getAllByUser(user.getId(), "Bearer " + bearerToken);
        model.addAttribute("loans", userLoans);
        model.addAttribute("user", user);

        return "profil";
    }

    /**
     * Method to extend a loan
     *
     * @param id lid of the loan to extend
     * @param session the session
     *
     * @return redirect to the profile page
     * @see LoanProxy#renewLoan(Long, String)
     */
    @GetMapping("/{id}/renewal")
    public ModelAndView renewLoan(@PathVariable Long id, HttpSession session) {
        String bearerToken = (String) session.getAttribute("auth-token");
        loanProxy.renewLoan(id, "Bearer " + bearerToken);
        return new ModelAndView("redirect:/profil");
    }
}
