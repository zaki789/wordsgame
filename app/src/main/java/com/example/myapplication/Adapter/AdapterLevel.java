package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Level;
import com.example.myapplication.R;
import com.example.myapplication.onClickListenerItemView;

import java.util.ArrayList;

public class AdapterLevel extends RecyclerView.Adapter<AdapterLevel.LevelViewHolder> {

    ArrayList<Level> levels;
    onClickListenerItemView onClickListenerItemView;

    public AdapterLevel(ArrayList<Level> levels, onClickListenerItemView onClickListenerItemView) {
        this.levels = levels;
        this.onClickListenerItemView = onClickListenerItemView;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LevelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_level, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder holder, int position) {
        holder.binLevel(levels.get(position));
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public class LevelViewHolder extends RecyclerView.ViewHolder {
        TextView tvLevel;

        public LevelViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLevel = itemView.findViewById(R.id.tv_level_num);

        }

        void binLevel(Level level) {
            tvLevel.setText("" + level.getId());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListenerItemView.itemClick(level, getAdapterPosition());
                }
            });
        }

    }
}
