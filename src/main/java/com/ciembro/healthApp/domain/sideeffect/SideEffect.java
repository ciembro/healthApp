package com.ciembro.healthApp.domain.sideeffect;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "SideEffect.getUserDrugs",
                        query = "select distinct drug_id from side_effects " +
                                "where user_id = :userId and is_removed = false"
                ),
                @NamedNativeQuery(
                        name = "SideEffect.removeDrugFromUserList",
                        query = "update side_effects set is_removed = true " +
                                "where id = :id"
                ),
                @NamedNativeQuery(
                        name = "SideEffect.getSideEffectsByDrugId",
                        query = "select * from side_effects " +
                                "where user_id = :userId and drug_id = :drugId " +
                                "and details IS NOT NULL and is_removed = false",
                        resultClass = SideEffect.class
                )
        }
)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "side_effects")
public class SideEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @Column
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column
    private String details;

    @Column
    boolean isRemoved;

    public SideEffect(long id, User user, Drug drug, String details) {
        this.id = id;
        this.user = user;
        this.drug = drug;
        this.details = details;
        this.isRemoved = false;
    }
}
