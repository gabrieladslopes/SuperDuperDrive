package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Controller
@RequestMapping("/files")
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String addFile(@RequestParam("fileUpload") MultipartFile multipartFile, Authentication authentication, Model model) {

        System.out.println("add file");

        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();

        try {
            InputStream fis = multipartFile.getInputStream();
            byte[] fileData = new byte[fis.available()];

            System.out.println(multipartFile.getOriginalFilename());
            System.out.println(multipartFile.getContentType());
            System.out.println(multipartFile.getSize());
            System.out.println(fileData);

            File file = new File(null, multipartFile.getOriginalFilename(), multipartFile.getContentType(), String.valueOf(multipartFile.getSize()), userId, fileData);
            int rowsInserted = fileService.addFile(file);
            System.out.println("Rows inserted: " + rowsInserted);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return "redirect:/home";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") Integer fileId) {
        fileService.deleteFile(fileId);
        return "redirect:/home";
    }
}
