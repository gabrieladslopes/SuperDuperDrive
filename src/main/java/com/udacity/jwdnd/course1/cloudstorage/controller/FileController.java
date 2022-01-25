package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.ErrorMessage;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.SizeLimitExceededException;
import java.io.ByteArrayInputStream;
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
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, Authentication authentication, Model model) {

        System.out.println("upload file");
        System.out.println(multipartFile.getSize());

        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();

        String fileName = multipartFile.getOriginalFilename();
        File fileWithUsedName = fileService.getFileByFilename(fileName);

        if(fileWithUsedName != null) {
            return "redirect:/result?isSuccess=" + false + "&error=" + ErrorMessage.REPEATED_FILENAME;
        }

        try {
            byte[] fileData = multipartFile.getBytes();

            File file = new File(null, multipartFile.getOriginalFilename(), multipartFile.getContentType(), String.valueOf(multipartFile.getSize()), userId, fileData);
            int rowsInserted = fileService.addFile(file);

            if(rowsInserted < 1) {
                return "redirect:/result?isSuccess=" + false;
            }

        } catch(Exception e) {
            return "redirect:/result?isSuccess="+false;
        }

        return "redirect:/result?isSuccess="+true;
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("fileId") Integer fileId) {
        File file = fileService.getFileByFileId(fileId);
        String fileName = file.getFilename();
        InputStream inputStream = new ByteArrayInputStream(file.getFileData());
        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(resource);
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") Integer fileId) {
        fileService.deleteFile(fileId);
        return "redirect:/home";
    }
}
