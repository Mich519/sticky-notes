package mj.project.stickynotesscrubbackend.note.dto;

import lombok.Getter;
import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import mj.project.stickynotesscrubbackend.note.entity.Note;

@Getter
public class NoteDto {
    private String title;
    private String content;

    public Note toEntity(AppUser owner) {
        return Note.builder()
                .title(title)
                .content(content)
                .owner(owner)
                .build();
    }
}
