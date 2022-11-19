package com.example.studibook.ui;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studibook.R;

import java.util.ArrayList;

public class MainHome extends Fragment {

    private MainHomeViewModel mViewModel;
    private ImageView searchView;
    EditText searchText;
    ArrayList<String> food_projects= null;
    ArrayList<String> travel_projects= null;
    ArrayList<String> environment_projects= null;
    ArrayList<String> medical_projects= null;
    ArrayList<String> miscelleanous_projects= null;




    public static MainHome newInstance() {
        return new MainHome();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main_home, container, false);
        searchView=view.findViewById(R.id.searchIcons);
        searchText= view.findViewById(R.id.searchET);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // Your piece of code on keyboard search click
                    checkData();

                    return true;
                }
                return false;            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view_id) {

                checkData();
            }
        });
    }

    private void checkData() {
        String domain_search= "";
        String text_search= searchText.getText().toString().trim();
        food_projects= new ArrayList<>();
        travel_projects= new ArrayList<>();
        environment_projects= new ArrayList<>();
        medical_projects= new ArrayList<>();
        food_projects.add("food");
        food_projects.add("menu");
        food_projects.add("cuisines");
        food_projects.add("drinks");
        food_projects.add("eat");
        food_projects.add("Food");
        food_projects.add("rice");
        food_projects.add("Veg");
        food_projects.add("veg");
        food_projects.add("non-veg");
        food_projects.add("hotels");
        food_projects.add("chinese food");
        food_projects.add("Asian");
        food_projects.add("Non-veg");
        food_projects.add("diet");
        food_projects.add("vegetables");
        food_projects.add("fruits");
        food_projects.add("Rice");
        food_projects.add("restaurant");
        food_projects.add("Restaurant");
        travel_projects.add("journey");
        travel_projects.add("trip");
        travel_projects.add("tour");
        travel_projects.add("peregrinate");
        travel_projects.add("trek");
        travel_projects.add("voyage");
        travel_projects.add("travel");
        travel_projects.add("Travel");
        travel_projects.add("journey");
        medical_projects.add("Health");
        medical_projects.add("Heart"); medical_projects.add("blood"); medical_projects.add("medicines");
        medical_projects.add("Medical"); medical_projects.add("Hospitals"); medical_projects.add("hospital");
        medical_projects.add("blood pressure");  medical_projects.add("heartrate");  medical_projects.add("body");
        medical_projects.add("health");
        medical_projects.add("Medicines"); medical_projects.add("doctor"); medical_projects.add("Doctor");
        environment_projects.add("atmosphere"); environment_projects.add("biodiversity");  environment_projects.add("farming"); environment_projects.add("chemicals");
        environment_projects.add("climate"); environment_projects.add("weather");  environment_projects.add("fertilizers");
        environment_projects.add("farmers"); environment_projects.add("globalwarming"); environment_projects.add("nature");
        environment_projects.add("plants"); environment_projects.add("habitat"); environment_projects.add("water");
        environment_projects.add("water");environment_projects.add("animals");environment_projects.add("energy");
        environment_projects.add("ecology");

        int image=0;;
        if(food_projects.contains(text_search)){
            domain_search= "Food";
            image=R.drawable.food;
        }
        else if(travel_projects.contains(text_search)){
            domain_search= "Travel";
            image=R.drawable.travel;
        }
        else if(medical_projects.contains(text_search)){
            domain_search= "Medical";
            image=R.drawable.medical;
        }

        else if(environment_projects.contains(text_search))
        {
            domain_search= "Environment";
            image=R.drawable.environment;
        }
        else{
            domain_search= "Miscellaneous";
            image=R.drawable.miscellaneous;
        }
        Log.d("domain search", domain_search);

        Intent intent=new Intent(getActivity(),FoodRelatedProject.class);
        intent.putExtra("DomainSearch", domain_search);
        intent.putExtra("IMAGE", image);
        startActivity(intent);


    }


}