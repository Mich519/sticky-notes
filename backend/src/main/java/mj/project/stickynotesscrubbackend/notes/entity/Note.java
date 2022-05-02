package mj.project.stickynotesscrubbackend.notes.entity;

import mj.project.stickynotesscrubbackend.app_user.entity.AppUser;

import javax.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    @ManyToOne
    private AppUser owner;
}
