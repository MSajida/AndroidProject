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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder>{
    private ArrayList<AddProjectmodel> listdata;
    FoodRelatedProject context;
    int picture;
    ArrayList<String> testss;
    String searcheddatassss;
    String isDatasss;

    public ProjectAdapter(FoodRelatedProject studentEnrolledCoursesActivity, ArrayList<AddProjectmodel> course, int image, String isData, String searcheddata, ArrayList<String> test) {
        this.listdata=course;
        this.context=studentEnrolledCoursesActivity;
        this.picture=image;
        this.testss=test;
        this.searcheddatassss=searcheddata;
        this.isDatasss=isData;
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
        int image=getmage(listdata.get(position).getCategory());
        holder.detailImage.setImageResource(image);
        holder.layout_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Details.class);
                intent.putExtra("PROJECTDETAILS", addProjectmodel);
                context.startActivity(intent);
            }
        });

        holder.edit_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // edit project
                Intent intent=new Intent(context, Login.class);
                intent.putExtra("DELETE", "FALSE");
                intent.putExtra("FRAGMENT", "FALSE");
                intent.putExtra("PROJECTDETAILS", addProjectmodel);
                intent.putStringArrayListExtra("SEARCHEDARRAY", testss);
                intent.putExtra("SEARCHEDDATA", searcheddatassss);
                intent.putExtra("BOOLEAN", isDatasss);

                intent.putExtra("EDIT", "TRUE");
                context.startActivity(intent);
            }
        });
        holder.delete_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // delete project

                Intent intent=new Intent(context, Login.class);
                intent.putExtra("PROJECTDETAILS", addProjectmodel);
                intent.putExtra("DELETE", "TRUE");
                intent.putExtra("FRAGMENT", "FALSE");
                intent.putExtra("EDIT", "FALSE");
                intent.putExtra("PROJECTDETAILS", addProjectmodel);
                intent.putExtra("CATEGORY", addProjectmodel.getCategory());
                intent.putExtra("KEY", addProjectmodel.getKey());
                intent.putExtra("POSITION", position);
                intent.putStringArrayListExtra("SEARCHEDARRAY", testss);
                intent.putExtra("SEARCHEDDATA", searcheddatassss);
                intent.putExtra("BOOLEAN", isDatasss);

                context.startActivity(intent);

      /*          FirebaseDatabase database = FirebaseDatabase.getInstance();
                String key = database.getReference().push().getKey();
                DatabaseReference myRefstudent = database.getReference("Projects").child(addProjectmodel.getCategory());
                myRefstudent.child(addProjectmodel.getKey()).removeValue();
                removeCount(addProjectmodel.getCategory().toString().toLowerCase(),position);*/
            }
        });

    }

    private int getmage(String category) {
        int image=0;;
        String food="Food";
        String travel="Travel";
        String medical="Medical";
        String environment="Evironment";
        String miscellaneous="Miscellaneous";
        String transportTourism="TransportTourism";
        String housing="HousingAccomodation";
        String chatmedia="ChatMedia";
        String marketPlaceEcommerce="MarketPlaceEcommerce";
        String eventManagementEventTracking="EventManagementEventTracking";
        String educationTechnology="EducationTechnology";

        if (category.toLowerCase().equalsIgnoreCase(food.toLowerCase())){
            image=R.drawable.food;
        }else if(category.toLowerCase().equalsIgnoreCase(travel.toLowerCase())){
            image=R.drawable.travel;
        }else if(category.toLowerCase().equalsIgnoreCase(medical.toLowerCase())){
            image=R.drawable.medical;
        }else if(category.toLowerCase().equalsIgnoreCase(environment.toLowerCase())){
            image=R.drawable.environment;
        }else if(category.toLowerCase().equalsIgnoreCase(miscellaneous.toLowerCase())){
            image=R.drawable.miscellaneous;
        }else if(category.toLowerCase().equalsIgnoreCase(housing.toLowerCase())){
            image=R.drawable.housing;
        }else if(category.toLowerCase().equalsIgnoreCase(transportTourism.toLowerCase())){
            image=R.drawable.tourisim;
        }
        else if(category.toLowerCase().equalsIgnoreCase(educationTechnology.toLowerCase())){
            image=R.drawable.educationtechnology;
        }
        else if(category.toLowerCase().equalsIgnoreCase(eventManagementEventTracking.toLowerCase())){
            image=R.drawable.eventmanagemtn;
        }
        else if(category.toLowerCase().equalsIgnoreCase(marketPlaceEcommerce.toLowerCase())){
            image=R.drawable.marketplace;
        }        else if(category.toLowerCase().equalsIgnoreCase(chatmedia.toLowerCase())){
            image=R.drawable.chatmedia;
        }
        return image;
    }

    private void removeCount(String ssss, int position) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefstudent = database.getReference("CountOfProjects").child("Projects").child(ssss);
        myRefstudent.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String count=snapshot.getValue().toString();
                int num=Integer.parseInt(count);
                num=num-1;
                myRefstudent.setValue(String.valueOf(num));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listdata.remove(position);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView setBatch,setYear,setTitle;
        ConstraintLayout layout_click;
        ImageView detailImage,edit_project,delete_project;
        public ViewHolder(View itemView) {
            super(itemView);
            this.setBatch = (TextView) itemView.findViewById(R.id.setBatch);
            this.detailImage = (ImageView) itemView.findViewById(R.id.detailImage);
            this.delete_project = (ImageView) itemView.findViewById(R.id.delete_project);
            this.edit_project = (ImageView) itemView.findViewById(R.id.edit_project);
            this.setYear = (TextView) itemView.findViewById(R.id.setYear);
            this.setTitle = (TextView) itemView.findViewById(R.id.setTitle);
            this.layout_click = (ConstraintLayout) itemView.findViewById(R.id.layout_click);
        }
    }
}

