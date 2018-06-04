package com.example.caroline.foodme.EdamamObjects.EnumClasses;

/**
 * Created by princ on 22/05/2018.
 */

public enum Diet {
    BALANCED("Balanced"),
    HIGH_FIBER("High-Fiber"),
    HIGH_PROTEIN("High-Protein"),
    LOW_CARB("Low-Carb"),
    LOW_FAT("Low-Fat"),
    LOW_SODIUM("Low-Sodium");

    private String dietLabel;

    private Diet(String dietLabel){
        this.dietLabel = dietLabel;
    }


}
