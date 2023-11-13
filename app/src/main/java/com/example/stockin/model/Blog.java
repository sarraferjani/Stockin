package com.example.stockin.model;

public class Blog {
    private String blogTitle;
    private String blogDescription;
    private String blogUserName;
    private String blogImage;
    private String blogUserImg;
    public Blog(String blogTitle, String blogDescription, String blogUserName, String blogImage, String blogUserImg) {
        this.blogTitle = blogTitle;
        this.blogDescription = blogDescription;
        this.blogUserName = blogUserName;
        this.blogImage = blogImage;
        this.blogUserImg = blogUserImg;
    }
    // Getters
    public String getBlogTitle() {
        return blogTitle;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public String getBlogUserName() {
        return blogUserName;
    }

    public String getBlogImage() {
        return blogImage;
    }

    public String getBlogUserImg() {
        return blogUserImg;
    }

    // Setters
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    public void setBlogUserName(String blogUserName) {
        this.blogUserName = blogUserName;
    }

    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    public void setBlogUserImg(String blogUserImg) {
        this.blogUserImg = blogUserImg;
    }
}
