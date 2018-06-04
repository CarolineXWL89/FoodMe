package com.example.caroline.foodme.EdamamObjects.EnumClasses;

/**
 * Created by princ on 22/05/2018.
 */

public enum Health {

    ALCOHOL_FREE("Alcohol-Free"),
    CELERY_FREE("Celery-Free"),
    CRUSTACEAN_FREE("Crustacean-Free"),
    DAIRY("Dairy"),
    EGG("Eggs"),
    FISH("Fish"),
    GLUTEN("Gluten"),
    KIDNEY_FRIENDLY("Kidney friendly"),
    KOSHER("Kosher"),
    LOW_POTASSIUM("Low potassium"),
    LUPINE_FREE("Lupine-free"),
    MUSTARD_FREE("Mustard-free"),
    SANS_OIL("No oil added"),
    NO_SUGAR("No-sugar"),
    PALEO("Paleo"),
    PEANUTS("Peanut-free"),
    PESCATARIAN("Pork-free"),
    PORK_FREE("Pork-free"),
    RED_MEAT_FREE("Red-meat-free"),
    SESAME_FREE("Sesame-free"),
    SHELLFISH("Shellfish-free"),
    SOY("Soy-free"),
    SUGAR_CONSCIOUS("Sugar-conscious"),
    TREE_NUTS("Tree-nut-free"),
    VEGAN("Vegan"),
    VEGETARIAN("Vegetarian"),
    WHEAT_FREE("Wheat-free");

    private String healthLabel;
    private Health(String healthLabel){
        this.healthLabel = healthLabel;
    }
}
