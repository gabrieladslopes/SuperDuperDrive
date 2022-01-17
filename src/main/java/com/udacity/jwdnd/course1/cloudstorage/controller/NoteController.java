package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private UserService userService;
    private NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping
    public String addEditNote(Authentication authentication, Note note, Model model) {

        String username = authentication.getName();
        User user = userService.getUser(username);
        note.setUserId(user.getUserId());
        Integer noteId = note.getNoteId();

        if(noteId == null) {
            noteService.addNote(note);
            model.addAttribute("notes", noteService.getNotesByUser(user.getUserId()));
            model.addAttribute("isNotesActive", true);
        } else {
            noteService.updateNote(note);
        }

        return "redirect:/home";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable("noteId") Integer noteId, Model model) {
        noteService.deleteNote(noteId);
        return "redirect:/home";
    }
}
