package ru.trollkuro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PersonalData {
    private String name;
    private int age;
    private List<String> cars;
    private FamilyModel family;

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public List<String> getCars() {
        return cars;
    }
    public FamilyModel getFamily() {
        return family;
    }


    public static class FamilyModel {
        private String pet;
        private Boolean hasSiblings;
        private Boolean isMarried;

        public String getPet() {
            return pet;
        }
        @JsonProperty("has_siblings")
        public Boolean getHasSiblings() {
            return hasSiblings;
        }
        @JsonProperty("is_married")
        public Boolean getMarried() {
            return isMarried;
        }
    }
}
