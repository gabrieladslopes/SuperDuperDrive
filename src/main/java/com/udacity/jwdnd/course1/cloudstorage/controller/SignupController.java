package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping
    public String signUpUser(@ModelAttribute User user, Model model) {
        String signupError = null;

        if(!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "Username already being used.";
        } else {
            int numbersOfUsersAdded = userService.createUser(user);
            if(numbersOfUsersAdded < 1) {
                signupError = "Error signing up. Try again.";
            }
        }

        if(signupError != null) {
            model.addAttribute("signupError", signupError);
        } else {
            model.addAttribute("signupSuccess", true);
        }

        return "signup";
    }

}
