package com.example.java_dop_4;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class DetailFragment extends Fragment {

    private ImageView ivFullScreen;
    private MyModel model;
    private TextView namePerson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivFullScreen = view.findViewById(R.id.iv_fullscreen);
        namePerson = view.findViewById(R.id.tv_name_person);
        getData();
    }

    private void getData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            model = (MyModel) arguments.getSerializable("character");
            Glide.with(ivFullScreen.getContext()).load(model.getImageUrl()).into(ivFullScreen);
            namePerson.setText(model.getName());
        }
    }
}