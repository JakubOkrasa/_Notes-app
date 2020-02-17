package com.jtm.notesapp.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @Column(name = "note_content", length=1024)
    @Size(max = 1024)
    private String noteContent;

    @Column
    @NotNull
    private java.sql.Timestamp noteModificationTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserApp userApp;

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }
}
