package com.example.studentportal;

public class PortalObject {
    private String title;
    private String url;

    public PortalObject(String title, String url){
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }


    public String getUrl() {
        return url;
    }
}
