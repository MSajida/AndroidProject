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
    ArrayList<ProjectsListModel> list = new ArrayList<>();

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
        list=new ArrayList<>();

        binding.idEnvironment.setText("2");
        binding.idMedical.setText("2");
        binding.idFood.setText("3");
        binding.idTravel.setText("2");
        binding.idMiscellaneous.setText("4");



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
                        Integer.parseInt( binding.idEnvironment.getText().toString()),
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}