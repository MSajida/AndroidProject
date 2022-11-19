package com.example.studibook.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Here we provide details of  android projects on various domains. This acts as a reference for the students regarding the projects worked on different domain. This also includes a pie chart which provides the graphical representation of various domain related projects ");
    }

    public LiveData<String> getText() {
        return mText;
    }
}