package com.ciembro.healthApp.domain;

import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.weather.WeatherConditions;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "Insights.getAllInsightsByUserId",
                        query = "select * from insights where user_id = :userId",
                        resultClass = Insights.class
                )
        }
)

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "insights")
public class Insights {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "weather_id", nullable = false)
    private WeatherConditions weather;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @CreationTimestamp
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

    public static InsightsBuilder builder(){
        return new InsightsBuilder();
    }

    public static class InsightsBuilder {
        private long id;
        private WeatherConditions weather;
        private User user;
        private LocalDate creationDate;
        private List<EmotionalState> emotions = new ArrayList<>();
        private List<SideEffect> sideEffects = new ArrayList<>();
        private String comment;


        public InsightsBuilder id(long id) {
            this.id = id;
            return this;
        }
        public InsightsBuilder weather(WeatherConditions weather) {
            this.weather = weather;
            return this;
        }

        public InsightsBuilder user(User user) {
            this.user = user;
            return this;
        }

        public InsightsBuilder creationDate(LocalDate creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public InsightsBuilder emotions(List<EmotionalState> emotions) {
            this.emotions.addAll(emotions);
            return this;
        }

        public InsightsBuilder sideEffects(List<SideEffect> sideEffects) {
            this.sideEffects.addAll(sideEffects);
            return this;
        }

        public InsightsBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Insights build(){
            return new Insights(id, weather,user,creationDate,emotions,sideEffects,comment);
        }
    }
}
