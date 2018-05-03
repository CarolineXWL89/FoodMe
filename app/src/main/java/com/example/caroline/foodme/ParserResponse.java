package com.example.caroline.foodme;

import java.util.ArrayList;

/**
 * Created by princ on 09/04/2018.
 */

public class ParserResponse {
    public String text;
    public ArrayList<ParserResponseFood> parsed;
    public ArrayList<ParserResponseHint> hints;
    public int page;
    public int numPages;

    public ParserResponse() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<ParserResponseFood> getParsed() {
        return parsed;
    }

    public void setParsed(ArrayList<ParserResponseFood> parsed) {
        this.parsed = parsed;
    }

    public ArrayList<ParserResponseHint> getHints() {
        return hints;
    }

    public void setHints(ArrayList<ParserResponseHint> hints) {
        this.hints = hints;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }
}
