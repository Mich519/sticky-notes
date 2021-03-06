package mj.project.stickynotesscrubbackend.note.service;

import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import mj.project.stickynotesscrubbackend.note.entity.Note;
import mj.project.stickynotesscrubbackend.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Optional<Note> findNoteByIdAndOwner(long id, AppUser owner) {
        return noteRepository.findByIdAndOwner(id, owner);
    }

    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> findAllNotesByUser(AppUser appUser) {
        return noteRepository.findAllByOwner(appUser);
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> saveAllNotes(List<Note> notes) {
        return noteRepository.saveAll(notes);
    }

    public void deleteNote(Note note) {
        noteRepository.delete(note);
    }
}
