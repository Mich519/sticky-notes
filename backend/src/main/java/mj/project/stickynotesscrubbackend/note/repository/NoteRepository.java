package mj.project.stickynotesscrubbackend.note.repository;

import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import mj.project.stickynotesscrubbackend.note.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByOwner(AppUser owner);
    Optional<Note> findByIdAndOwner(long id, AppUser owner);
}
