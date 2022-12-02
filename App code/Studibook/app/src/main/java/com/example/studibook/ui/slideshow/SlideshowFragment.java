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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    ArrayList<ProjectsListModel> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.nodataLayout.setVisibility(View.VISIBLE);
        binding.piechartLayout.setVisibility(View.GONE);

        return root;
    }

    private void getData() {

        list = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefstudent = database.getReference("CountOfProjects");
        myRefstudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ProjectsListModel projectsListModel = postSnapshot.getValue(ProjectsListModel.class);
                    list.add(projectsListModel);
                }
                if (list.size() > 0) {
                    int envi = Integer.parseInt(list.get(0).getEvironment());
                    int medi = Integer.parseInt(list.get(0).getMedical());
                    int food = Integer.parseInt(list.get(0).getFood());
                    int trav = Integer.parseInt(list.get(0).getTravel());
                    int misc = Integer.parseInt(list.get(0).getMiscellaneous());
                    int trans = Integer.parseInt(list.get(0).getTransportTourism());
                    int chat = Integer.parseInt(list.get(0).getChatMedia());
                    int market = Integer.parseInt(list.get(0).getMarketPlaceEcommerce());
                    int event = Integer.parseInt(list.get(0).getEventManagementEventTracking());
                    int edu = Integer.parseInt(list.get(0).getEducationTechnology());
                    int hous = Integer.parseInt(list.get(0).getHousing());

                    int count = envi + medi + food + trav + misc + trans + chat + market + event + edu + hous;
                    if (count > 0) {
                        binding.nodataLayout.setVisibility(View.GONE);
                        binding.piechartLayout.setVisibility(View.VISIBLE);

                        String envPer = "0";
                        String envMed = "0";
                        String envFood = "0";
                        String envTrav = "0";
                        String envMisc = "0";

                        String envtrans = "0";
                        String envchat = "0";
                        String envmarke = "0";
                        String envevent = "0";
                        String envedu = "0";
                        String envhouse = "0";

                        DecimalFormat dfrmt = new DecimalFormat();
                        dfrmt.setMaximumFractionDigits(2);
                        // chat
                        if (chat > 0) {
                            float ch= Float.intBitsToFloat(chat);
                            float co= Float.intBitsToFloat(count);
                            float en=(ch/co)*100 ;
                            envchat = String.valueOf(dfrmt.format(en)) + "%";
                            binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Environment", chat,
                                            Color.parseColor("#05D83A")));
                        }
                        if (hous > 0) {
                            float ch= Float.intBitsToFloat(hous);
                            float co= Float.intBitsToFloat(count);
                            float en=(ch/co) * 100 ;
                            envhouse = String.valueOf(dfrmt.format(en)) + "%";
                            binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Housing", hous,
                                            Color.parseColor("#AACCDC")));
                        }

                        // market
                        if (market > 0) {
                            float ch= Float.intBitsToFloat(market);
                            float co= Float.intBitsToFloat(count);
                            float en=(ch/co) * 100 ;
                            envmarke = String.valueOf(dfrmt.format(en)) + "%";
                            binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Environment", market,
                                            Color.parseColor("#E384A5")));
                        }

                        // event
                        if (event > 0) {
                            float ch= Float.intBitsToFloat(event);
                            float co= Float.intBitsToFloat(count);
                            float en=(ch/co) * 100 ;
                            envevent = String.valueOf(dfrmt.format(en)) + "%";
                            binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Environment", event,
                                            Color.parseColor("#044867")));
                        }

                        // education
                        if (edu > 0) {
                            float ch= Float.intBitsToFloat(edu);
                            float co= Float.intBitsToFloat(count);
                            float en=(ch/co) * 100 ;
                            envedu = String.valueOf(dfrmt.format(en)) + "%";
                            binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Environment", edu,
                                            Color.parseColor("#24CCB8")));
                        }

                        // transport
                        if (trans > 0) {
                            float ch= Float.intBitsToFloat(trans);
                            float co= Float.intBitsToFloat(count);
                            float en=(ch/co) * 100 ;
                            envtrans = String.valueOf(dfrmt.format(en)) + "%";
                            binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Environment", trans,
                                            Color.parseColor("#AAAAA5")));
                        }

                        if (envi > 0) {
                            float ch= Float.intBitsToFloat(envi);
                            float co= Float.intBitsToFloat(count);
                            float en=(ch/co) * 100 ;
                            envPer = String.valueOf(dfrmt.format(en)) + "%";
                            binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Environment", envi,
                                            Color.parseColor("#EF5350")));
                        } else {
                          /*  binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Environment",0,
                                            Color.parseColor("#EF5350")));*/
                        }

                        if (medi > 0) {
                            float ch= Float.intBitsToFloat(medi);
                            float co= Float.intBitsToFloat(count);
                            float en=(ch/co) * 100 ;
                            envMed = String.valueOf(dfrmt.format(en)) + "%";
                            binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Medical", medi,
                                            Color.parseColor("#29B6F6")));
                        } else {
                       /*     binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Medical",0,
                                            Color.parseColor("#29B6F6")));*/
                        }

                        if (food > 0) {
                            float ch= Float.intBitsToFloat(food);
                            float co= Float.intBitsToFloat(count);
                            float en=(ch/co) * 100 ;
                            envFood = String.valueOf(dfrmt.format(en)) + "%";
                            binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Food", food,
                                            Color.parseColor("#FFA726")));
                        } else {
                         /*   binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Food",0,
                                            Color.parseColor("#FFA726")));*/
                        }

                        if (trav > 0) {
                            float ch= Float.intBitsToFloat(trav);
                            float co= Float.intBitsToFloat(count);
                            float en=(ch/co) * 100 ;
                            envTrav = String.valueOf(dfrmt.format(en)) + "%";
                            binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Travel", trav,
                                            Color.parseColor("#66BB6A")));
                        } else {
                           /* binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Travel",0,
                                            Color.parseColor("#66BB6A")));*/
                        }

                        if (misc > 0) {
                            float ch= Float.intBitsToFloat(misc);
                            float co= Float.intBitsToFloat(count);
                            float en=(ch/co) * 100 ;
                            envMisc = String.valueOf(dfrmt.format(en)) + "%";
                            binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Miscellaneius", misc,
                                            Color.parseColor("#F6E43C")));
                        } else {
                          /*  binding.piechart.addPieSlice(
                                    new PieModel(
                                            "Miscellaneius",0,
                                            Color.parseColor("#F6E43C")));*/
                        }

                        binding.idEnvironment.setText(envPer);
                        binding.idMedical.setText(envMed);
                        binding.idFood.setText(envFood);
                        binding.idMiscellaneous.setText(envMisc);
                        binding.idEventtracking.setText(envevent);
                        binding.idChatmedia.setText(envchat);
                        binding.idEducationtechnoloy.setText(envedu);
                        binding.idTransport.setText(envtrans);
                        binding.idMarketplace.setText(envmarke);
                        binding.idHousing.setText(envhouse);
                        binding.piechart.startAnimation();
                    } else {
                        binding.nodataLayout.setVisibility(View.VISIBLE);
                        binding.piechartLayout.setVisibility(View.GONE);

                    }

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