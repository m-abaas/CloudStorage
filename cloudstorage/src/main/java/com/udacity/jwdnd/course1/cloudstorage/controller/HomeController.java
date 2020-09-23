package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private CredentialsService credentialsService;
    private NotesService notesService;
    private List<Credentials> credentials;

    public HomeController(CredentialsService credentialsService, NotesService notesService)
    {
        this.credentials = new ArrayList<>();
        this.credentialsService = credentialsService;
        this.notesService = notesService;
    }


    @GetMapping()
    public String homeView(@ModelAttribute("newCredential") CredentialsForm credentialsForm, @ModelAttribute("newNote") NotesForm notesForm, Model model){
        //this.credentials.add(new Credentials(12, "tepo", "tempos", "tempa", "tompo", 12));
        //this.credentials.add(new Credentials(12, "tepo", "tempos", "tempa", "tompo", 12));
        //this.credentials.add(new Credentials(12, "tepo", "tempos", "tempa", "tompo", 12));
        //this.credentials.add(new Credentials(12, "tepo", "tempos", "tempa", "tompo", 12));
        //this.credentials.add(new Credentials(12, "tepo", "tempos", "tempa", "tompo", 12));

         model.addAttribute("credentials", this.credentialsService.getCredentials());
        //System.out.println(credentials[1].url);
        //model.addAttribute("credentials", credentials);
        return "home";
    }
}
