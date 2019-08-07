package com.jtm.notesapp.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="note_title", nullable = false)
    @NotBlank(message="Note title is required.")
    @Size(min=1)
    private String noteTitle;

    @Column(name="note_category")
    @NotBlank(message="Note category is required.")
    @Size(min=1, max=15)
    private String noteCategory;

    @Column(name = "note_content", length=1024)
    @NotBlank(message="Note content is required.")
    @Size(max = 1024)
    private String noteContent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp userApp;

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }
}
