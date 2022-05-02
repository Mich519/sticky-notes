package mj.project.stickynotesscrubbackend.app_user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;
import mj.project.stickynotesscrubbackend.notes.entity.Note;
import org.apache.tomcat.jni.User;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class AppUserDto {
    private String username;
    private String password;
    @JsonIgnore
    private List<Note> notes;

    public AppUser toEntity() {
        return AppUser.builder()
                .username(username)
                .password(password)
                .notes(notes)
                .build();
    }

    public static AppUserDto createFrom(AppUser appUser) {
        return AppUserDto.builder()
                .username(appUser.getUsername())
                .notes(appUser.getNotes())
                .build();
    }
}
