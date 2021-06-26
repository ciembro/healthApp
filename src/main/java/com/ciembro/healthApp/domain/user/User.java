package com.ciembro.healthApp.domain.user;

import com.ciembro.healthApp.domain.drug.Drug;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private LocalDateTime joiningDate = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "join_users_drugs",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
                },
            inverseJoinColumns =  {
                    @JoinColumn(name = "drug_id", referencedColumnName = "id")
            }
    )
    private List<Drug> drugs = new ArrayList<>();




}
