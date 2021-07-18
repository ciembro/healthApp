package com.ciembro.healthApp.domain.drug;

import com.ciembro.healthApp.domain.UserTreatment;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Drug.findByActiveSubstanceFrag",
                query = "select * from drugs where active_substance like concat('%',:frag,'%')",
                resultClass = Drug.class
        ),
        @NamedNativeQuery(
                name = "Drug.findByInternationalNameFrag",
                query = "select * from drugs where international_name like concat('%',:frag,'%')",
                resultClass = Drug.class
        ),
        @NamedNativeQuery(
                name = "Drug.findByTradeNameFrag",
                query = "select * from drugs where drugs.trade_name like concat('%',:frag,'%')",
                resultClass = Drug.class
        )
})

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "drugs")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    @Column(nullable = false)
    private int uniqueDrugId;

    @Column
    private String tradeName;

    @Column
    private String internationalName;

    @Column(length = 1000)
    private String dosage;

    @Column
    private String brand;

    @Column(length = 1000)
    private String activeSubstance;

    @Column
    private String leafletUrl;

    @Column
    private LocalDate lastSynchAt;


    public Drug(int uniqueDrugId, String tradeName, String internationalName,
                String dosage, String brand, String activeSubstance, String leafletUrl) {
        this.uniqueDrugId = uniqueDrugId;
        this.tradeName = tradeName;
        this.internationalName = internationalName;
        this.dosage = dosage;
        this.brand = brand;
        this.activeSubstance = activeSubstance;
        this.leafletUrl = leafletUrl;
    }

    @OneToMany(targetEntity = UserTreatment.class, mappedBy = "drug")
    List<UserTreatment> treatments = new ArrayList<>();

}
