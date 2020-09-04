package com.openclassrooms.libraryclient.controller;

import com.openclassrooms.libraryclient.model.RegisterForm;
import com.openclassrooms.libraryclient.proxy.AuthProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private AuthProxy authProxy;

    @GetMapping
    public String getLoginPage(Model model) {
        RegisterForm registerForm = new RegisterForm();
        model.addAttribute("registerForm", registerForm);
        return "register";
    }

    @PostMapping
    public ModelAndView registerUser(@ModelAttribute RegisterForm registerForm) {
        authProxy.registerUser(registerForm);
        return new ModelAndView("redirect:/login");
    }
}
