package com.example.java_dop_4;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class DetailFragment extends Fragment {

    private ImageView ivFullScreen;
    private BusinessModel model;
    private MaterialButton buttonRedactor;
    private EditText namePerson, agePerson, imageRedactor;

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
        agePerson = view.findViewById(R.id.tv_age_person);
        buttonRedactor = view.findViewById(R.id.btn_redactor);
        imageRedactor = view.findViewById(R.id.image_redactor);
        getData();
        onClick();
    }

    private void onClick() {
        buttonRedactor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriRedactor = imageRedactor.getText().toString().trim();
                String nameRedactcor = namePerson.getText().toString().trim();
                String ageRedactcor = agePerson.getText().toString().trim();
                if (uriRedactor.isEmpty() && nameRedactcor.isEmpty() && ageRedactcor.isEmpty()) {
                    imageRedactor.setError("Пусто");
                    namePerson.setError("Изменений нету");
                    agePerson.setError("Изменений нету");
                } else if (uriRedactor.isEmpty()) {
                    imageRedactor.setError("Пусто");
                } else if (nameRedactcor.isEmpty()) {
                    namePerson.setError("Изменений нету");
                } else if (ageRedactcor.isEmpty()) {
                    agePerson.setError("Изменений нету");
                } else {
                    Toast.makeText(getContext(), "ВЫ УСПЕШНО ИЗМЕНИЛИ...", Toast.LENGTH_SHORT).show();
                    model.setImageUrl(uriRedactor);
                    model.setName(nameRedactcor);
                    model.setAge(Integer.parseInt(ageRedactcor));
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, RecyclerFragment.class, null)
                            .addToBackStack("RecyclerFragment").commit();
                    getParentFragmentManager().popBackStack();
                }
            }
        });
    }

    private void getData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            model = (BusinessModel) arguments.getSerializable("character");
            Glide.with(ivFullScreen.getContext()).load(model.getImageUrl()).into(ivFullScreen);
            namePerson.setText(model.getName());
            agePerson.setText(String.valueOf(model.getAge()));
        }
    }
}