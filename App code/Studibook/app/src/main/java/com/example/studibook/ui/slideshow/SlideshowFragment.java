package com.example.studibook.ui.slideshow;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.studibook.AddProjectmodel;
import com.example.studibook.databinding.FragmentSlideshowBinding;
import com.example.studibook.ui.ProjectsListModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    ArrayList<ProjectsListModel> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        list = new ArrayList<>();

        getData(list);

        binding.idEnvironment.setText("0");
        binding.idMedical.setText("0");
        binding.idFood.setText("0");
        binding.idTravel.setText("0");
        binding.idMiscellaneous.setText("0");

        binding.piechart.addPieSlice(
                new PieModel(
                        "Food",
                        Integer.parseInt(binding.idFood.getText().toString()),
                        Color.parseColor("#FFA726")));
        binding.piechart.addPieSlice(
                new PieModel(
                        "Travel",
                        Integer.parseInt(binding.idTravel.getText().toString()),
                        Color.parseColor("#66BB6A")));
        binding.piechart.addPieSlice(
                new PieModel(
                        "Environment",
                        Integer.parseInt(binding.idEnvironment.getText().toString()),
                        Color.parseColor("#EF5350")));
        binding.piechart.addPieSlice(
                new PieModel(
                        "Medical",
                        Integer.parseInt(binding.idMedical.getText().toString()),
                        Color.parseColor("#29B6F6")));
        binding.piechart.addPieSlice(
                new PieModel(
                        "Miscellaneius",
                        Integer.parseInt(binding.idMiscellaneous.getText().toString()),
                        Color.parseColor("#F6E43C")));
        binding.piechart.startAnimation();

        return root;
    }

    private void getData(ArrayList<ProjectsListModel> list) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefstudent = database.getReference("CountOfProjects");
        myRefstudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ProjectsListModel projectsListModel = postSnapshot.getValue(ProjectsListModel.class);
                    list.add(projectsListModel);
                }
                if (list.size() > 0) {
                    int envi=Integer.parseInt(list.get(0).getEnvironment());
                    int medi=Integer.parseInt(list.get(0).getMedical());
                    int food=Integer.parseInt(list.get(0).getFood());
                    int trav=Integer.parseInt(list.get(0).getTravel());
                    int misc=Integer.parseInt(list.get(0).getMiscellaneous());

                    int count=envi+medi+food+trav+misc;
                    String envPer="0";
                    String envMed="0";
                    String envFood="0";
                    String envTrav="0";
                    String envMisc="0";
                    if(envi>0){
                        envPer=String.valueOf(envi*100/count)+"%";
                    }
                    if(medi>0){
                        envMed=String.valueOf(medi*100/count)+"%";
                    }

                    if(food>0){
                        envFood=String.valueOf(food*100/count)+"%";
                    }

                    if(trav>0){
                        envTrav=String.valueOf(trav*100/count)+"%";
                    }

                    if(misc>0){
                        envMisc=String.valueOf(misc*100/count)+"%";
                    }




                    binding.idEnvironment.setText(envPer);
                    binding.idMedical.setText(envMed);
                    binding.idFood.setText(envFood);
                    binding.idTravel.setText(envTrav);
                    binding.idMiscellaneous.setText(envMisc);

                    binding.piechart.addPieSlice(
                            new PieModel(
                                    "Food",food,
                                    Color.parseColor("#FFA726")));
                    binding.piechart.addPieSlice(
                            new PieModel(
                                    "Travel",trav,
                                    Color.parseColor("#66BB6A")));
                    binding.piechart.addPieSlice(
                            new PieModel(
                                    "Environment",envi,
                                    Color.parseColor("#EF5350")));
                    binding.piechart.addPieSlice(
                            new PieModel(
                                    "Medical",medi,
                                    Color.parseColor("#29B6F6")));
                    binding.piechart.addPieSlice(
                            new PieModel(
                                    "Miscellaneius",misc,
                                    Color.parseColor("#F6E43C")));
                    binding.piechart.startAnimation();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}