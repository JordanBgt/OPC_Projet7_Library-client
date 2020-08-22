package com.openclassrooms.libraryclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new ModelAndView("redirect:/");
    }
}
