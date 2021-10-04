package com.sangeet.project.model;

public class ExpenseMetadata {

    private String imageDownloadableURL;
    private String notes;
    private String name;

    public ExpenseMetadata(String name) {
        this.name = name;
    }

    public String getImageDownloadableURL() {
        return imageDownloadableURL;
    }

    public void setImageDownloadableURL(String imageDownloadableURL) {
        this.imageDownloadableURL = imageDownloadableURL;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
