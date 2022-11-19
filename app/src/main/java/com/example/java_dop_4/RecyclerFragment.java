package com.example.java_dop_4;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerFragment extends Fragment implements OnItemClickListener {

    private MyRepository repository = new MyRepository();
    private Button buttonAdd;
    private List<MyModel> businessList;
    private MyModel model;
    private final MyAdapter adapter = new MyAdapter(this);
    private RecyclerView rvListOfName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonAdd = view.findViewById(R.id.button_add);
        rvListOfName = view.findViewById(R.id.rv_list_of_name);
        businessList = repository.getListOfCharacters();
        initialize();
        adapter.setData(businessList);
        onAdds();
        onClick();
    }

    public void onClick() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddFragment fragment = new AddFragment();
                getParentFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .addToBackStack("RecyclerFragment")
                        .commit();
            }
        });
    }

    private void initialize() {
        rvListOfName.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvListOfName.setAdapter(adapter);
    }

    @Override
    public void onClick(MyModel model) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("character", model);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, DetailFragment.class, bundle)
                .addToBackStack("RecyclerFragment")
                .commit();
    }

    private void onAdds() {
        getParentFragmentManager().setFragmentResultListener("OK", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (requestKey.equals("OK")) {
                    model = (MyModel) result.getSerializable("put");
                    businessList.add(model);
                    adapter.setData(businessList);
                }
            }
        });
    }
}