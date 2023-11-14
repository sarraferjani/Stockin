package tn.esprit.mobile.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tn.esprit.mobile.R;
import tn.esprit.mobile.model.Blog;


public class BlogViewHolder extends RecyclerView.ViewHolder {
    private TextView blogTitle;
    private TextView blogDescription;
    private TextView blogUserName;
    private ImageView blogImage;
    private ImageView blogUserImg;
    private LinearLayout blogDetail;
    //private BlogClickListener clickListener;
    private Button detailsBtn;
    private Blog blog;
    public BlogViewHolder(@NonNull View itemView) {
        super(itemView);

        blogTitle = itemView.findViewById(tn.esprit.mobile.R.id.blogTitle);
        blogDescription = itemView.findViewById(R.id.blogDecription);
        blogUserName = itemView.findViewById(R.id.blogUserName);
        blogImage = itemView.findViewById(R.id.blogImage);
        blogUserImg = itemView.findViewById(R.id.blogUserImg);
        blogDetail = itemView.findViewById(R.id.blogDetails);


        blogDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), BlogDetailActivity.class);
                intent.putExtra("blog",blog);

                itemView.getContext().startActivity(intent);
            }
        });


    }

    public void bind(Blog blog) {
        this.blog = blog;
        blogTitle.setText(blog.getBlogTitle());
        blogDescription.setText(blog.getBlogDescription());
        blogUserName.setText(blog.getBlogUserName());
        blogImage.setImageResource(R.drawable.blog_img);
        blogUserImg.setImageResource(R.drawable.user_img);
    }


}
