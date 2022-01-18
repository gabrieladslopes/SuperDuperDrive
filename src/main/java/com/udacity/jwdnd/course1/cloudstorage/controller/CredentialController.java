package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    private UserService userService;
    private CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping
    public String addEditCredential(Authentication authentication, Credential credential, Model model) {

        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();

        System.out.println("Credential id: " + credential.getCredentialId());

        if(credential.getCredentialId() == null) {
            credential.setUserId(userId);
            credentialService.addCredential(credential);
        } else {
            credentialService.updateCredential(credential);
        }

        System.out.println("Credential: " + credential.toString());

        return "redirect:/home";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId) {
        credentialService.deleteCredential(credentialId);
        return "redirect:/home";
    }
}
