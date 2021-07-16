package com.ciembro.healthApp.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsightsDto {

    private String username;
    private LocalDate creationDate;
    private Set<EmotionalStateDto> emotions;
    private Set<SideEffectDto> sideEffects;
    private String comment;


public static class InsightsDtoBuilder {

        private String username;
        private LocalDate creationDate;
        private Set<EmotionalStateDto> emotions = new HashSet<>();
        private Set<SideEffectDto> sideEffects = new HashSet<>();
        private String comment;

        public static InsightsDtoBuilder builder(){
            return new InsightsDtoBuilder();
        }

        public InsightsDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public InsightsDtoBuilder creationDate(LocalDate creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public InsightsDtoBuilder emotions(Set<EmotionalStateDto> emotions) {
            this.emotions.addAll(emotions);
            return this;
        }

        public InsightsDtoBuilder sideEffects(Set<SideEffectDto> sideEffects) {
            this.sideEffects.addAll(sideEffects);
            return this;
        }

        public InsightsDtoBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public InsightsDto build(){
            return new InsightsDto(username, creationDate, emotions, sideEffects, comment);
        }
    }
}
