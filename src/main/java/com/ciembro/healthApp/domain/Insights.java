package com.ciembro.healthApp.domain;

import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.weather.WeatherConditions;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "insights")
public class Insights {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "condition_id")
    private WeatherConditions conditions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LocalDate creationDate;

    @ManyToMany
    @JoinTable(
            name = "join_insights_emotions",
            joinColumns = {
                    @JoinColumn(name = "insights_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "emotion_id", referencedColumnName = "id")
            }
    )
    List<EmotionalState> emotions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "join_insights_effects",
            joinColumns = {
                 @JoinColumn(name = "insights_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "effect_id", referencedColumnName = "id")
            }
    )
    private List<SideEffect> sideEffects;

    @Column
    private String comment;
}
