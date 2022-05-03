package mj.project.stickynotesscrubbackend.note.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String content;

    @ManyToOne
    @JsonIgnore
    @Setter
    private AppUser owner;
}
