package com.ciembro.healthApp.domain;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "treatments")
public class UserTreatment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @Column(nullable = false)
    private LocalDate startedAt;

    @Column
    private LocalDate finishedAt;


}