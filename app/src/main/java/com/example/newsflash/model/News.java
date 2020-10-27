package com.example.newsflash.model;

public class News {
    String sourceName;
    String title;
    String url;
    String publishedDate;

    public News(String sourceName, String title, String url, String publishedDate) {
        this.sourceName = sourceName;
        this.title = title;
        this.url = url;
        this.publishedDate = publishedDate;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}
