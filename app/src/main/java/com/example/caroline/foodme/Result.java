package com.example.caroline.foodme;

/**
 * Created by maylisw on 3/23/18.
 */

public class Result {
    private String description, link;

    public Result(String description, String link) {
        this.description = description;
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
