package com.example.stockin.dao;

import androidx.room.Dao;
import androidx.room.*;

import com.example.stockin.model.Blog;

import java.util.List;

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
