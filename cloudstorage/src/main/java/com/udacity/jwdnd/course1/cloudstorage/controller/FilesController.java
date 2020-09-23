package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/files")
public class FilesController {


    private FilesService filesService;
    private int checkSuccess;

    public FilesController(FilesService filesService)
    {
        this.filesService = filesService;
    }

    @GetMapping
    public String getFiles(@RequestParam("fileUpload") MultipartFile fileUpload, Model model)
    {
        return "home";
    }

    @PostMapping
    public String postCredentials(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {
        this.checkSuccess = filesService.addFile(fileUpload);
        if (checkSuccess >= 1 ) {
            model.addAttribute("result", "success");

        } else {
            model.addAttribute("result", "failure");
        }

        model.addAttribute("result", "success");
        //System.out.println(credentialsForm.getUrl());
        //System.out.println(credentialsForm.getPassword());
        //System.out.println(credentialsForm.getUserName());
        return "result";
    }

}

