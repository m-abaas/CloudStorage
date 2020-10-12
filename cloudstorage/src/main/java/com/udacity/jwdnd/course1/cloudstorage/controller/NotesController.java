package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.SystemEventListener;

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
    public String postCredentials(Authentication authentication, @ModelAttribute("newNote") NotesForm notesForm, Model model)
    {

        // Check if the record already exists!
        this.checkSuccess = notesService.addNote(notesForm, authentication.getName());
       // System.out.println(checkSuccess);
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

    @GetMapping("/delete_note")
    public String delete_note(Model model, @RequestParam(name="noteId") Integer noteId)
    {
        this.checkSuccess = notesService.deleteNote(noteId);
        if (checkSuccess >= 1 ) {
            model.addAttribute("result", "success");

        } else {
            model.addAttribute("result", "failure");
        }
        model.addAttribute("result", "success");

        return "result";
    }


}
