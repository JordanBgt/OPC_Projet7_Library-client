package com.openclassrooms.libraryclient.controller;

import com.openclassrooms.libraryclient.model.JwtResponse;
import com.openclassrooms.libraryclient.model.LoginForm;
import com.openclassrooms.libraryclient.proxy.AuthProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthProxy authProxy;

    private final String TOKEN_KEY = "auth-token";

    @GetMapping
    public String getLoginPage(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @PostMapping
    public ModelAndView authenticateUser(@ModelAttribute LoginForm loginForm, HttpServletRequest request) {
        ResponseEntity<JwtResponse> response = authProxy.authenticateUser(loginForm);
        HttpSession session = request.getSession();
        session.setAttribute(TOKEN_KEY, response.getBody().getToken());
        return new ModelAndView("redirect:/");
    }
}
