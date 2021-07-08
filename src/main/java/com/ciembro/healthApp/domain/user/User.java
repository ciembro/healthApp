package com.ciembro.healthApp.domain.user;

import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private String roles;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime joiningDate;

    @OneToMany(mappedBy = "user")
    private List<SideEffect> sideEffects = new ArrayList<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = "ROLE_USER";
        this.active = true;
        this.joiningDate = LocalDateTime.now();
    }
}
