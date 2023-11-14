package tn.esprit.mobile.dao;

import androidx.room.*;
import androidx.room.Dao;


import java.util.List;

import tn.esprit.mobile.model.Blog;

@Dao
public interface BlogDao {
    @Insert
    void insertOne(Blog blog);
    @Update
    void updateOne(Blog blog);
    @Delete
    void delete(Blog blog);
    @Query("SELECT * FROM blog_table")
    List<Blog> getAll();
}
