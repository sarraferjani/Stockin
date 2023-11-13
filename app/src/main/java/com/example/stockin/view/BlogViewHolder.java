package com.example.stockin.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockin.R;
import com.example.stockin.model.Blog;

public  class BlogViewHolder extends RecyclerView.ViewHolder {

    private TextView blogTitle;
    private TextView blogDescription;
    private TextView blogUserName;
    private ImageView blogImage;
    private ImageView blogUserImg;


    public BlogViewHolder(@NonNull View itemView) {
        super(itemView);
        blogTitle=itemView.findViewById(R.id.blogTitle);
        blogDescription =itemView.findViewById(R.id.blogDecription);
        blogUserName =itemView.findViewById(R.id.blogUserName);
        blogImage=itemView.findViewById(R.id.blogImage);
        blogUserImg=itemView.findViewById(R.id.blogUserImg);
    }
    public void bind(Blog blog) {
        blogTitle.setText(blog.getBlogTitle());
        blogDescription.setText(blog.getBlogDescription());
        blogUserName.setText(blog.getBlogUserName());
        blogImage.setImageResource(R.drawable.blog_img);
        blogUserImg.setImageResource(R.drawable.user_img);
        // Associez d'autres données aux vues correspondantes
    }


}
