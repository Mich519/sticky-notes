package mj.project.stickynotesscrubbackend.note.controller;

import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import mj.project.stickynotesscrubbackend.app_user.service.AppUserService;
import mj.project.stickynotesscrubbackend.note.dto.NoteDto;
import mj.project.stickynotesscrubbackend.note.entity.Note;
import mj.project.stickynotesscrubbackend.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class NoteController {

    private final NoteService noteService;
    private final AppUserService userService;

    @Autowired
    public NoteController(NoteService noteService, AppUserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/{username}/notes")
    public ResponseEntity<List<Note>> findAllNotes(@PathVariable String username, Principal principal) {
        if (principal.getName().equals(username)) {
            Optional<AppUser> appUser = userService.findUserByUsername(username);
            if (appUser.isPresent()) {
                List<Note> notes = noteService.findAllNotesByUser(appUser.get());
                return ResponseEntity.ok(notes);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/users/me/notes")
    public ResponseEntity<List<NoteDto>> findMyNotes(Principal principal) {
        Optional<AppUser> appUser = userService.findUserByUsername(principal.getName());
        if (appUser.isPresent()) {
            List<NoteDto> noteDtos = noteService.findAllNotesByUser(appUser.get()).stream()
                    .map(NoteDto::fromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(noteDtos);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users/me/notes")
    public ResponseEntity<HttpStatus> saveMyNotes(@RequestBody NoteDto noteDto, Principal principal) {
        Optional<AppUser> appUser = userService.findUserByUsername(principal.getName());
        if(appUser.isPresent()) {
            noteService.saveNote(noteDto.toEntity(appUser.get()));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE); //todo: changee
    }

    @DeleteMapping("/users/me/notes/{id}")
    public ResponseEntity<HttpStatus> deleteMyNote(@PathVariable long id, Principal principal) {
        Optional<AppUser> appUser = userService.findUserByUsername(principal.getName());
        if (appUser.isPresent()) {
            Optional<Note> note = noteService.findNoteByIdAndOwner(id, appUser.get());
            if(note.isPresent()) {
                noteService.deleteNote(note.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
