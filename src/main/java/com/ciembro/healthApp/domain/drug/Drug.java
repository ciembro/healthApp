package com.ciembro.healthApp.domain.drug;

import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Drug.findByActiveSubstanceFrag",
                query = "select * from drugs where active_substance like concat('%',:frag,'%')",
                resultClass = Drug.class
        ),
        @NamedNativeQuery(
                name = "Drug.findByCommonNameFrag",
                query = "select * from drugs where common_name like concat('%',:frag,'%')",
                resultClass = Drug.class
        ),
        @NamedNativeQuery(
                name = "Drug.findByTradeNameFrag",
                query = "select * from drugs where drugs.trade_name like concat('%',:frag,'%')",
                resultClass = Drug.class
        )
})

@Data
@NoArgsConstructor
@Entity
@Table(name = "drugs")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    @Column
    private String tradeName;

    @Column
    private String commonName;

    @Column
    private String dose;

    @Column
    private String brand;

    @Column
    private String activeSubstance;

    @Column
    private String leafletUrl;


    public Drug( String tradeName, String commonName,
                String dose, String brand, String activeSubstance, String leafletUrl) {
        this.tradeName = tradeName;
        this.commonName = commonName;
        this.dose = dose;
        this.brand = brand;
        this.activeSubstance = activeSubstance;
        this.leafletUrl = leafletUrl;
    }

    @OneToMany(mappedBy = "drug")
    List<SideEffect> sideEffects = new ArrayList<>();
}