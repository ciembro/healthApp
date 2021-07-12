package com.ciembro.healthApp.domain.sideeffect;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
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
                                "where user_id = :userId"
//                        resultClass = Long.class
                ),
                @NamedNativeQuery(
                        name = "SideEffect.removeDrugFromUserList",
                        query = "delete from side_effects " +
                                "where user_id = :userId and drug_id = :drugId"
                ),
                @NamedNativeQuery(
                        name = "SideEffect.getSideEffectsByDrugId",
                        query = "select * from side_effects " +
                                "where user_id = :userId and drug_id = :drugId " +
                                "and details LIKE NOT NULL",
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

    public SideEffect(long id, User user, Drug drug, String details) {
        this.id = id;
        this.user = user;
        this.drug = drug;
        this.details = details;
    }
}
