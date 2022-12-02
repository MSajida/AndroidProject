package com.example.studibook.ui;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainHome extends Fragment {

    private MainHomeViewModel mViewModel;
    private ImageView searchView, imageView2;
    EditText searchText;
    ArrayList<String> food_projects = null;
    ArrayList<String> travel_projects = null;
    ArrayList<String> environment_projects = null;
    ArrayList<String> medical_projects = null;
    ArrayList<String> miscelleanous_projects = null;
    Map<String, ArrayList<String>> hash_map = new HashMap<String, ArrayList<String>>();
    ArrayList<String> titleList = new ArrayList<>();


    public static MainHome newInstance() {
        return new MainHome();
    }

    @Override
    public void onResume() {
        super.onResume();
        // saveKeywordsinfirebase();
        if (isOnline(getActivity())) {
            try {
                getProjectTitles();
                getKeywods();
            }catch(Exception e){
                e.printStackTrace();
            }
            //getKeywods();
        } else {
            Toast.makeText(getActivity(), "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void getProjectTitles() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        String cate = "ProjectTitles";
        titleList.clear();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Title").child(cate);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String keywords = snapshot.getValue(String.class);
                    if(keywords!=null){
                        String[] list = keywords.split(",");
                        titleList.addAll(Arrays.asList(list));                    }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        searchView = view.findViewById(R.id.searchIcons);
        imageView2 = view.findViewById(R.id.imageView2);
        searchText = view.findViewById(R.id.searchET);
        imageView2.setAlpha(120);


        return view;
    }

    public static boolean isOnline(FragmentActivity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    private void getKeywods() {
        hash_map.clear();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("keywords");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String value = snapshot1.getValue(String.class);
                    String key = snapshot1.getKey();
                    ArrayList<String> list = new ArrayList<>();
                    String[] strArray = value.split(",");
                    for (int i = 0; i < strArray.length; i++) {
                        list.add(strArray[i].toLowerCase());
                    }
                    hash_map.put(key, list);
                }
                System.out.println("Initial Mappings are: " + hash_map);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // Your piece of code on keyboard search click
                    //checkData();
                    String text_search = searchText.getText().toString().trim();

                    if(checkDatainArray(text_search,titleList)){
                        searchkey(text_search);
                    }else{
                        searchKeyWordFirebase(text_search.toLowerCase());
                    }
                    return true;
                }
                return false;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view_id) {
                String text_search = searchText.getText().toString().trim().toLowerCase();
                searchKeyWordFirebase(text_search);
                //     checkData();
            }
        });
    }

    private void searchkey(String text_search) {
        ArrayList<String> searchedarray = new ArrayList<>();
        Intent intent = new Intent(getActivity(), FoodRelatedProject.class);
        intent.putStringArrayListExtra("SEARCHEDARRAY", searchedarray);
        intent.putExtra("SEARCHEDDATA", text_search);
        intent.putExtra("BOOLEAN", "TRUE");
        startActivity(intent);
    }

    private boolean checkDatainArray(String text_search, ArrayList<String> titleList) {
        Boolean data=titleList.contains(text_search);
        if(data)
        {
            return true;
        }else{
            return false;
        }
    }

    private void searchKeyWordFirebase(String text_search) {
        ArrayList<String> searchedarray = new ArrayList<>();
        System.out.println("Searched Value" + hash_map.containsValue(text_search));
        for (Map.Entry<String, ArrayList<String>> entry : hash_map.entrySet()) {
            if (entry.getValue().contains(text_search))
                searchedarray.add(entry.getKey());
            System.out.println("Found Cat in " + entry.getKey());
        }

        Intent intent = new Intent(getActivity(), FoodRelatedProject.class);
        intent.putStringArrayListExtra("SEARCHEDARRAY", searchedarray);
        intent.putExtra("SEARCHEDDATA", "");
        intent.putExtra("BOOLEAN", "FALSE");
        startActivity(intent);

    }

    private void checkData() {
        String domain_search = "";
        String text_search = searchText.getText().toString().trim();
        food_projects = new ArrayList<>();
        travel_projects = new ArrayList<>();
        environment_projects = new ArrayList<>();
        medical_projects = new ArrayList<>();
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
        medical_projects.add("Heart");
        medical_projects.add("blood");
        medical_projects.add("medicines");
        medical_projects.add("Medical");
        medical_projects.add("Hospitals");
        medical_projects.add("hospital");
        medical_projects.add("blood pressure");
        medical_projects.add("heartrate");
        medical_projects.add("body");
        medical_projects.add("health");
        medical_projects.add("Medicines");
        medical_projects.add("doctor");
        medical_projects.add("Doctor");
        environment_projects.add("atmosphere");
        environment_projects.add("biodiversity");
        environment_projects.add("farming");
        environment_projects.add("chemicals");
        environment_projects.add("climate");
        environment_projects.add("weather");
        environment_projects.add("fertilizers");
        environment_projects.add("farmers");
        environment_projects.add("globalwarming");
        environment_projects.add("nature");
        environment_projects.add("plants");
        environment_projects.add("habitat");
        environment_projects.add("water");
        environment_projects.add("water");
        environment_projects.add("animals");
        environment_projects.add("energy");
        environment_projects.add("ecology");

        int image = 0;
        ;
        if (food_projects.contains(text_search)) {
            domain_search = "Food";
            image = R.drawable.food;
        } else if (travel_projects.contains(text_search)) {
            domain_search = "Travel";
            image = R.drawable.travel;
        } else if (medical_projects.contains(text_search)) {
            domain_search = "Medical";
            image = R.drawable.medical;
        } else if (environment_projects.contains(text_search)) {
            domain_search = "Environment";
            image = R.drawable.environment;
        } else {
            domain_search = "Miscellaneous";
            image = R.drawable.miscellaneous;
        }
        Log.d("domain search", domain_search);

        Intent intent = new Intent(getActivity(), FoodRelatedProject.class);
        intent.putExtra("DomainSearch", domain_search);
        intent.putExtra("IMAGE", image);
        startActivity(intent);


    }


}