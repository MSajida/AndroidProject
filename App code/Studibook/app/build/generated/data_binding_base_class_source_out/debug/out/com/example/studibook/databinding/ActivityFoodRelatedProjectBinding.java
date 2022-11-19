// Generated by view binder compiler. Do not edit!
package com.example.studibook.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.studibook.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFoodRelatedProjectBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView Project1;

  @NonNull
  public final TextView Project2;

  @NonNull
  public final TextView Project3;

  @NonNull
  public final TextView Project4;

  @NonNull
  public final TextView texttext;

  private ActivityFoodRelatedProjectBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView Project1, @NonNull TextView Project2, @NonNull TextView Project3,
      @NonNull TextView Project4, @NonNull TextView texttext) {
    this.rootView = rootView;
    this.Project1 = Project1;
    this.Project2 = Project2;
    this.Project3 = Project3;
    this.Project4 = Project4;
    this.texttext = texttext;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFoodRelatedProjectBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFoodRelatedProjectBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_food_related_project, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFoodRelatedProjectBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Project1;
      TextView Project1 = ViewBindings.findChildViewById(rootView, id);
      if (Project1 == null) {
        break missingId;
      }

      id = R.id.Project2;
      TextView Project2 = ViewBindings.findChildViewById(rootView, id);
      if (Project2 == null) {
        break missingId;
      }

      id = R.id.Project3;
      TextView Project3 = ViewBindings.findChildViewById(rootView, id);
      if (Project3 == null) {
        break missingId;
      }

      id = R.id.Project4;
      TextView Project4 = ViewBindings.findChildViewById(rootView, id);
      if (Project4 == null) {
        break missingId;
      }

      id = R.id.texttext;
      TextView texttext = ViewBindings.findChildViewById(rootView, id);
      if (texttext == null) {
        break missingId;
      }

      return new ActivityFoodRelatedProjectBinding((ConstraintLayout) rootView, Project1, Project2,
          Project3, Project4, texttext);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
