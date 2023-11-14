package tn.esprit.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

   private  Context context;
   Activity activity;
   private ArrayList id,productid,startdate,enddate,discount;


    public CustomAdapter(Activity activity,Context context, ArrayList id, ArrayList productid, ArrayList startdate, ArrayList enddate, ArrayList discount) {
        this.activity=activity;
        this.context = context;
        this.id = id;
        this.productid = productid;
        this.startdate = startdate;
        this.enddate = enddate;
        this.discount = discount;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
     holder.productid_txt.setText(String.valueOf(productid.get(position)));
     holder.discount_txt.setText(String.valueOf(discount.get(position)));
     holder.startdate_txt.setText(String.valueOf(startdate.get(position)));
     holder.enddate_txt.setText(String.valueOf(enddate.get(position)));
     holder.mainLayout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent=new Intent(context,UpdateActivity.class);
             intent.putExtra("id",String.valueOf(id.get(position)));
             intent.putExtra("product",String.valueOf(productid.get(position)));
             intent.putExtra("discount",String.valueOf(discount.get(position)));
             intent.putExtra("start_date",String.valueOf(startdate.get(position)));
             intent.putExtra("end_date",String.valueOf(enddate.get(position)));
             activity.startActivityForResult(intent,1);
         }
     });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt,productid_txt,startdate_txt,enddate_txt,discount_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt=itemView.findViewById(R.id.id_txt);
            productid_txt=itemView.findViewById(R.id.productid_txt);
            startdate_txt=itemView.findViewById(R.id.startdate_txt);
            enddate_txt=itemView.findViewById(R.id.enddate_txt);
            discount_txt=itemView.findViewById(R.id.discount_txt);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
