package com.anurag.notesapp.controller;

import com.anurag.notesapp.model.Note;
import com.anurag.notesapp.repository.NoteRepository;
import com.anurag.notesapp.security.JwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository noteRepository;

    @PostMapping("/add")
    public Note addNote(@RequestBody Note note, @RequestHeader("Authorization") String token) {
        String jwt = token.substring(7); // remove "Bearer "
        String userId = JwtUtil.extractEmail(jwt); // extract from token
        note.setUserId(userId);
        return noteRepository.save(note);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteNote(@PathVariable String id) {
        noteRepository.deleteById(id);
        return "Note deleted";
    }

    @GetMapping("/all/{userId}")
    public List<Note> getNotes(@PathVariable String userId) {
        return noteRepository.findByUserId(userId);
    }
}
