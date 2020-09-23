package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private NotesService notesService;
    private int checkSuccess;

    public NotesController(NotesService notesService)
    {
        this.notesService = notesService;
    }

    @GetMapping
    public String getCredentials(@ModelAttribute("newNote") NotesForm notesForm , Model model)
    {
        return "home";
    }

    @PostMapping
    public String postCredentials(@ModelAttribute("newNote") NotesForm notesForm, Model model)
    {
        this.checkSuccess = notesService.addNote(notesForm);
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


}
