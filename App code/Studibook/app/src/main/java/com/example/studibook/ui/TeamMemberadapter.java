package com.example.studibook.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studibook.AddProjectmodel;
import com.example.studibook.AddmemberModel;
import com.example.studibook.R;

import java.util.ArrayList;


public class TeamMemberadapter extends RecyclerView.Adapter<TeamMemberadapter.ViewHolder> {
    private ArrayList<AddmemberModel> listdata;
    Details context;

    public TeamMemberadapter(Details studentEnrolledCoursesActivity, ArrayList<AddmemberModel> course) {
        this.listdata = course;
        this.context = studentEnrolledCoursesActivity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.teammember_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.teamName.setText(listdata.get(position).getName());
        holder.layout_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Profile.class);
                intent.putExtra("MEMBERNAME", listdata.get(position).getName());
                intent.putExtra("MEMBERMAIL", listdata.get(position).getMail());
                intent.putExtra("MEMBERSID", listdata.get(position).getSid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView teamName;
        ConstraintLayout layout_click;

        public ViewHolder(View itemView) {
            super(itemView);
            this.teamName = (TextView) itemView.findViewById(R.id.teamName);
            this.layout_click = (ConstraintLayout) itemView.findViewById(R.id.layout_click);
        }
    }
}

