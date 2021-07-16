package com.ciembro.healthApp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "emotional_states")
public class EmotionalState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String engText;

    @Column
    private String plText;

    @ManyToMany(mappedBy = "emotions")
    List<Insights> insights = new ArrayList<>();

}
