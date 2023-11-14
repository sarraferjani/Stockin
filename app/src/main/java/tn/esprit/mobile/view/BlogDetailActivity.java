package tn.esprit.mobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.mobile.R;
import tn.esprit.mobile.database.AppDatabase;
import tn.esprit.mobile.model.Blog;


public class BlogDetailActivity extends AppCompatActivity {

    private ImageView blogImageView;
    private ImageView blogUserImageView;
    private TextView blogTitleTv;
    private TextView blogDescTv;
    private TextView blogUserTv;
    private Button deleteBtn;
    private Button editBtn;
    private Blog blog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(tn.esprit.mobile.R.layout.activity_blog_detail);

        blogTitleTv = findViewById(R.id.blogT);
        blogDescTv = findViewById(R.id.blogDesc);
        blogUserTv = findViewById(R.id.blogUser);
        blogImageView = findViewById(R.id.blogImg);
        blogUserImageView = findViewById(R.id.blogUserImage);
        deleteBtn=findViewById(R.id.deleteBtn);
        editBtn=findViewById(R.id.editBtn);
        Intent intent = getIntent();

        if(intent != null) {
            if(intent.hasExtra("blog")) {
                 blog = (Blog) intent.getSerializableExtra("blog");
                Log.d("database", "ID: " + blog.getBid());
                Log.d("database", "Title: " + blog.getBlogTitle());
                Log.d("database", "Description: " + blog.getBlogDescription());

                blogTitleTv.setText(blog.getBlogTitle());
                blogDescTv.setText(blog.getBlogDescription());
                blogUserTv.setText(blog.getBlogUserName());
                blogImageView.setImageResource(R.drawable.blog_img);
                blogUserImageView.setImageResource(R.drawable.user_img);
            }
        }
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase.getAppDatabase(v.getContext()).blogDao().delete(blog);
                Intent intent = new Intent(v.getContext(), BlogsActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("database", "ID: mki" );

               Intent intent = new Intent(v.getContext(), UpdateBlogActivity.class);
                intent.putExtra("blog",blog);
                v.getContext().startActivity(intent);
            }
        });




    }
}