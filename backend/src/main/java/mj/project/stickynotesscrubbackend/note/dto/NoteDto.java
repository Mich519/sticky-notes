package mj.project.stickynotesscrubbackend.note.dto;

import lombok.Builder;
import lombok.Getter;
import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import mj.project.stickynotesscrubbackend.note.entity.Note;

@Getter
@Builder
public class NoteDto {
    private long id;
    private String title;
    private String content;

    public Note toEntity(AppUser owner) {
        return Note.builder()
                .title(title)
                .content(content)
                .owner(owner)
                .build();
    }

    public static NoteDto fromEntity(Note note) {
        return NoteDto.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .build();
    }
}
