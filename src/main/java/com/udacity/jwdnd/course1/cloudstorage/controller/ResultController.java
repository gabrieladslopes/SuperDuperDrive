package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.ErrorMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/result")
public class ResultController {

    @GetMapping
    public String getResultPage(Model model,
                                @RequestParam(required = false, name = "isSuccess") Boolean isSuccess,
                                @RequestParam(required = false, name = "error") ErrorMessage error) {

        if(error == null) error = ErrorMessage.UNKNOWN_ERROR;

        model.addAttribute("errorType", error.getType());
        model.addAttribute("errorMessage", error.getMessage());
        model.addAttribute("isSuccess", isSuccess);

        return "result";
    }
}
