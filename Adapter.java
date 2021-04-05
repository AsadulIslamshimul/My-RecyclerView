package com.sixhack.troom_troom.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.sixhack.troom_troom.Activites_Troom.Details_Activity_Troom;
import com.sixhack.troom_troom.R;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private int[] images;
    private String[] title;
    private  String[] views;
    private  String[] times;
    private  String[] links;

    public Adapter(Context context, int[] images, String[] title, String[] views, String[] times, String[] links) {
        this.context = context;
        this.images = images;
        this.title = title;
        this.views = views;
        this.times = times;
        this.links = links;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sample_layout,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(images[position]);
        holder.titletext.setText(title[position]);
        holder.linktext.setText(links[position]);
        holder.timetext.setText(times[position]);
        holder.viewtext.setText(views[position]);
    }

    @Override
    public int getItemCount() {

        return title.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titletext, linktext,timetext,viewtext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewIdId);
            titletext = itemView.findViewById(R.id.titletextId);
            linktext = itemView.findViewById(R.id.linktextId);
            timetext = itemView.findViewById(R.id.timetextId);
            viewtext = itemView.findViewById(R.id.viewtextId);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                            try {
                                Intent intent = new Intent(context, Details_Activity_Troom.class);
                                intent.putExtra("images_key", images[getAdapterPosition()]);
                                intent.putExtra("titles_key", title[getAdapterPosition()]);
                                intent.putExtra("view_key", views[getAdapterPosition()]);
                                intent.putExtra("times_key", times[getAdapterPosition()]);
                                intent.putExtra("links_key", links[getAdapterPosition()]);
                                context.startActivity(intent);

                            }catch (Exception e){
                                Toast.makeText(context, "Some Error try again", Toast.LENGTH_SHORT).show();
                            }
                }
            });

        }
    }
}
