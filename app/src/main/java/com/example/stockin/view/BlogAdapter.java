package com.example.stockin.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockin.R;
import com.example.stockin.model.Blog;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogViewHolder> {
    public interface BlogClickListener {
        void onBlogClicked(int position);
    }
    private List<Blog> blogPosts;
    private BlogClickListener clickListener;
    public BlogAdapter(List<Blog> blogPosts) {
        this.blogPosts = blogPosts;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.stockin.R.layout.blogs_single_item, parent, false);
        return new BlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        Blog blogPost = blogPosts.get(position);
        holder.bind(blogPost);
    }

    @Override
    public int getItemCount() {
        return blogPosts.size();
    }
}
