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

    public static DrugBuilder builder(){
        return new DrugBuilder();
    }

    public static class DrugBuilder {
        private long id;
        private int uniqueDrugId;
        private String internationalName;
        private String tradeName;
        private String dosage;
        private String brand;
        private String activeSubstance;
        private String leafletUrl;

        public DrugBuilder id(long id) {
            this.id = id;
            return this;
        }

        public DrugBuilder uniqueDrugId(int uniqueDrugId) {
            this.uniqueDrugId = uniqueDrugId;
            return this;
        }

        public DrugBuilder internationalName(String internationalName) {
            this.internationalName = internationalName;
            return this;
        }

        public DrugBuilder tradeName(String tradeName) {
            this.tradeName = tradeName;
            return this;
        }

        public DrugBuilder dosage(String dose) {
            this.dosage = dose;
            return this;
        }

        public DrugBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public DrugBuilder activeSubstance(String activeSubstance) {
            this.activeSubstance = activeSubstance;
            return this;
        }

        public DrugBuilder leafletUrl(String leafletUrl) {
            this.leafletUrl = leafletUrl;
            return this;
        }

        public Drug build(){
            return new Drug(id,uniqueDrugId,tradeName,
                    internationalName, dosage, brand, activeSubstance, leafletUrl);
        }
    }

    private Drug(Long id, int uniqueDrugId, String tradeName,
                 String internationalName, String dosage, String brand,
                 String activeSubstance, String leafletUrl) {
        this.id = id;
        this.uniqueDrugId = uniqueDrugId;
        this.tradeName = tradeName;
        this.internationalName = internationalName;
        this.dosage = dosage;
        this.brand = brand;
        this.activeSubstance = activeSubstance;
        this.leafletUrl = leafletUrl;
    }
}
