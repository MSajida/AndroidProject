package com.example.studibook.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studibook.AddProjectmodel;
import com.example.studibook.R;

import java.util.ArrayList;


public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder>{
    private ArrayList<AddProjectmodel> listdata;
    FoodRelatedProject context;
    int picture;

    public ProjectAdapter(FoodRelatedProject studentEnrolledCoursesActivity, ArrayList<AddProjectmodel> course, int image) {
        this.listdata=course;
        this.context=studentEnrolledCoursesActivity;
        this.picture=image;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AddProjectmodel addProjectmodel=listdata.get(position);
        holder.setBatch.setText(listdata.get(position).getBatch());
        holder.setYear.setText(listdata.get(position).getYear());
        holder.setTitle.setText(listdata.get(position).getTitle());
        holder.detailImage.setImageResource(picture);
        holder.layout_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Details.class);
                intent.putExtra("PROJECTDETAILS", addProjectmodel);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView setBatch,setYear,setTitle;
        ConstraintLayout layout_click;
        ImageView detailImage;
        public ViewHolder(View itemView) {
            super(itemView);
            this.setBatch = (TextView) itemView.findViewById(R.id.setBatch);
            this.detailImage = (ImageView) itemView.findViewById(R.id.detailImage);
            this.setYear = (TextView) itemView.findViewById(R.id.setYear);
            this.setTitle = (TextView) itemView.findViewById(R.id.setTitle);
            this.layout_click = (ConstraintLayout) itemView.findViewById(R.id.layout_click);
        }
    }
}

