package com.openclassrooms.libraryclient.controller;

import com.openclassrooms.libraryclient.model.JwtResponse;
import com.openclassrooms.libraryclient.model.LoginForm;
import com.openclassrooms.libraryclient.model.User;
import com.openclassrooms.libraryclient.proxy.AuthProxy;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public String getLoginPage(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @PostMapping
    public ModelAndView authenticateUser(@ModelAttribute LoginForm loginForm, HttpServletRequest request) {
        JwtResponse response = authProxy.authenticateUser(loginForm).getBody();
        HttpSession session = request.getSession();
        session.setAttribute("auth-token", response.getToken());
        User user = new User(response.getId(), response.getUsername(), response.getEmail());
        session.setAttribute("user", user);
        return new ModelAndView("redirect:/");
    }
}
