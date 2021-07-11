package com.ciembro.healthApp.domain.sideeffect;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "SideEffect.getUserDrugs",
                        query = "select distinct drug_id from side_effects " +
                                "where user_id = :userId",
                        resultClass = Drug.class
                ),
                @NamedNativeQuery(
                        name = "SideEffect.removeDrugFromUserList",
                        query = "delete from side_effects " +
                                "where user_id = :userId and drug_id = :drugId"
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "drug_id", referencedColumnName = "id")
    private Drug drug;

    @Column
    private String details;
}
