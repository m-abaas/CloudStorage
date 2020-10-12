package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
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
    private FilesService filesService;
    //private List<Credentials> credentials;

    public HomeController(CredentialsService credentialsService, NotesService notesService, FilesService filesService)
    {
        //this.credentials = new ArrayList<>();
        this.credentialsService = credentialsService;
        this.notesService = notesService;
        this.filesService = filesService;
    }


    @GetMapping()
    public String homeView(Authentication authentication, @ModelAttribute("newCredential") CredentialsForm credentialsForm, @ModelAttribute("newNote") NotesForm notesForm, Model model){

         model.addAttribute("credentials", this.credentialsService.getCredentials(authentication.getName()));
         model.addAttribute("notes", this.notesService.getNotes(authentication.getName()));
         model.addAttribute("files", this.filesService.getFiles(authentication.getName()));

        return "home";
    }
}
