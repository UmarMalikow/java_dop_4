package com.example.java_dop_4;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CharacterViewHolder> implements View.OnClickListener {

    private List<MyModel> listCharacters;
    private final OnItemClickListener onItemClickListener;

    public MyAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<MyModel> listCharacters) {
        this.listCharacters = listCharacters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.CharacterViewHolder holder, int position) {
        holder.itemView.setOnClickListener(this);
        holder.onBind(listCharacters.get(position));
    }

    @Override
    public int getItemCount() {
        return listCharacters.size();
    }

    @Override
    public void onClick(View view) {
        onItemClickListener.onClick((MyModel) view.getTag());
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder {
        private MaterialCardView mainContainer;
        private ImageView ivImage;
        private TextView tvName;
        private TextView tvAge;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
            mainContainer = itemView.findViewById(R.id.main_container);

        }

        public void onBind(MyModel character) {
            Glide.with(ivImage.getContext()).load(character.getImageUrl()).into(ivImage);
            tvName.setText(character.getName());
            tvAge.setText(String.valueOf(character.getAge()));
            mainContainer.setCardBackgroundColor(Color.parseColor(character.getColor()));
            itemView.setTag(character);
        }
    }
}
