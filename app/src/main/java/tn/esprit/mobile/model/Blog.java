package tn.esprit.mobile.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "blog_table")
public class Blog implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int bid;
    @ColumnInfo(name = "blog_title")
    private String blogTitle;
    @ColumnInfo(name = "blog_description")

    private String blogDescription;
    @ColumnInfo(name = "blog_username")

    private String blogUserName;
    @ColumnInfo(name = "blog_blog_image")

    private String blogImage;
    @ColumnInfo(name = "blog_userimage")

    private String blogUserImg;
    public Blog() {
    }
    @Ignore

    public Blog(int bid,String blogTitle, String blogDescription, String blogUserName, String blogImage, String blogUserImg) {
        this.bid = bid;
        this.blogTitle = blogTitle;
        this.blogDescription = blogDescription;
        this.blogUserName = blogUserName;
        this.blogImage = blogImage;
        this.blogUserImg = blogUserImg;
    }
    // Getters
    public int getBid() {
        return bid;
    }
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
    public void setBid(int bid) {
        this.bid = bid;
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
