package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    private NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public int addNote(NotesForm notesForm) {
        return notesMapper.insert(new Notes(null, notesForm.getTitle(), notesForm.getDescription(), 1));
    }

    public List<Notes> getNotes() {
        return this.notesMapper.getNotes();
    }

}


