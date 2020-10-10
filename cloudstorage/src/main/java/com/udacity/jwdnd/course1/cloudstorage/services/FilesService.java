package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class FilesService {

    private FilesMapper filesMapper;
    private UserService userService;

    public FilesService(FilesMapper filesMapper, UserService userService) {
        this.filesMapper = filesMapper;
        this.userService = userService;
    }

    public int addFile(MultipartFile fileUpload, String userName) throws IOException {

        return filesMapper.insert(new Files(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(),
                fileUpload.getSize(), userService.getUser(userName).getUserId(),
                fileUpload.getInputStream()));
    }

    public List<Files> getFiles(String userName) {
        return this.filesMapper.getFiles(userService.getUser(userName).getUserId());
    }

}
