package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credentials")
public class CredentialsController {

    private CredentialsService credentialsService;
    private int checkSuccess;

    public CredentialsController(CredentialsService credentialsService)
    {
        this.credentialsService = credentialsService;
    }

    @GetMapping
    public String getCredentials(@ModelAttribute("newCredential") CredentialsForm credentialsForm, Model model)
    {
        return "home";
    }

    @PostMapping
    public String postCredentials(Authentication authentication, @ModelAttribute("newCredential") CredentialsForm credentialsForm, Model model)
    {
        this.checkSuccess = credentialsService.addCredential(credentialsForm, authentication.getName());
        if (checkSuccess >= 1 ) {
            model.addAttribute("result", "success");

        } else {
            model.addAttribute("result", "failure");
        }

        //System.out.println(credentialsForm.getUrl());
        //System.out.println(credentialsForm.getPassword());
        //System.out.println(credentialsForm.getUserName());
        return "result";
    }

    @GetMapping("/delete_credential")
    public String delete_credential(Model model, @RequestParam(name="credentialId") Integer credentialId)
    {
        this.checkSuccess = credentialsService.deleteCredential(credentialId);
        if (checkSuccess >= 1 ) {
            model.addAttribute("result", "success");

        } else {
            model.addAttribute("result", "failure");
        }
        model.addAttribute("result", "success");

        return "result";
    }

}
