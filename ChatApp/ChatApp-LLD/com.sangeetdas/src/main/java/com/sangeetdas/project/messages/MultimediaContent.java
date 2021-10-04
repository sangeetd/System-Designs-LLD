package com.sangeetdas.project.messages;

public class MultimediaContent {

    private final String multimediaUrl;
    private final MultimediaType multiMediaType;
    private Double contentSize;

    public MultimediaContent(String multimediaUrl, Double contentSize, MultimediaType multiMediaType) {
        this.multimediaUrl = multimediaUrl;
        this.contentSize = contentSize;
        this.multiMediaType = multiMediaType;
    }

    public String getMultimediaUrl() {
        return multimediaUrl;
    }

    public MultimediaType getMultiMediaType() {
        return multiMediaType;
    }

    public Double getContentSize() {
        return contentSize;
    }
}
