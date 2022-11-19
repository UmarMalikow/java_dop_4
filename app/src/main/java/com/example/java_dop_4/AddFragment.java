package com.example.java_dop_4;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddFragment extends Fragment {

    private EditText imageViewCreate, editNameCreate, editAgeCreate;
    private Button buttonCreate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageViewCreate = view.findViewById(R.id.image_create);
        editNameCreate = view.findViewById(R.id.et_name_create);
        editAgeCreate = view.findViewById(R.id.et_age_create);
        buttonCreate = view.findViewById(R.id.btn_create);
        onClick();
    }

    private void onClick() {
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageCreateView = imageViewCreate.getText().toString().trim();
                String nameCreateText = editNameCreate.getText().toString().trim();
                String ageCreateText = editAgeCreate.getText().toString().trim();
                if (imageCreateView.isEmpty() && nameCreateText.isEmpty() && ageCreateText.isEmpty()) {
                    editNameCreate.setError("Введите имя");
                    imageViewCreate.setError("Введите ссылку");
                    editAgeCreate.setError("Введите возраст");
                } else if (imageCreateView.isEmpty()) {
                    imageViewCreate.setError("Введите ссылку");
                } else if (nameCreateText.isEmpty()) {
                    editNameCreate.setError("Введите имя");
                } else if (ageCreateText.isEmpty()) {
                    editAgeCreate.setError("Введите возраст");
                } else {
                    Bundle bundle = new Bundle();
                    BusinessModel model  = new BusinessModel(imageCreateView,nameCreateText,Integer.parseInt(ageCreateText),"#36D375");
                    bundle.putSerializable("editBusinesmens", model);
                    getParentFragmentManager().setFragmentResult("OK",bundle);
                    getParentFragmentManager().popBackStack();
                }
            }
        });
    }
}
