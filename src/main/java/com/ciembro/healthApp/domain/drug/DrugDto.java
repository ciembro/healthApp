package com.ciembro.healthApp.domain.drug;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugDto {

    private long id;
    private int uniqueDrugId;
    private String internationalName;
    private String tradeName;
    private String dosage;
    private String brand;
    private String activeSubstance;
    private String leafletUrl;

    public static DrugDtoBuilder builder(){
        return new DrugDtoBuilder();
    }

    public static class DrugDtoBuilder {
        private long id;
        private int uniqueDrugId;
        private String internationalName;
        private String tradeName;
        private String dosage;
        private String brand;
        private String activeSubstance;
        private String leafletUrl;

        public DrugDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public DrugDtoBuilder uniqueDrugId(int uniqueDrugId) {
            this.uniqueDrugId = uniqueDrugId;
            return this;
        }

        public DrugDtoBuilder internationalName(String internationalName) {
            this.internationalName = internationalName;
            return this;
        }

        public DrugDtoBuilder tradeName(String tradeName) {
            this.tradeName = tradeName;
            return this;
        }

        public DrugDtoBuilder dosage(String dose) {
            this.dosage = dose;
            return this;
        }

        public DrugDtoBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public DrugDtoBuilder activeSubstance(String activeSubstance) {
            this.activeSubstance = activeSubstance;
            return this;
        }

        public DrugDtoBuilder leafletUrl(String leafletUrl) {
            this.leafletUrl = leafletUrl;
            return this;
        }

        public DrugDto build(){
            return new DrugDto(id, uniqueDrugId, internationalName,
                    tradeName, dosage, brand, activeSubstance, leafletUrl);
        }
    }

}
