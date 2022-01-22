package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.PlaceHolder;
import com.example.myapplication.R;
import com.example.myapplication.onClickListenerItemView;

import java.util.ArrayList;

public class AdapterCharacter extends RecyclerView.Adapter<AdapterCharacter.charViewHolder> {

    ArrayList<PlaceHolder> placeHolderArrayList = new ArrayList<>();
    onClickListenerItemView onClickListenerItemView;

    public void setOnClickListenerItemView(com.example.myapplication.onClickListenerItemView onClickListenerItemView) {
        this.onClickListenerItemView = onClickListenerItemView;
    }

    public AdapterCharacter() {
    }

    public void add(PlaceHolder characterPlaceHolder) {
        this.placeHolderArrayList.add(characterPlaceHolder);
        notifyItemInserted(placeHolderArrayList.size() - 1);
    }


    public AdapterCharacter(ArrayList<PlaceHolder> placeHolderArrayList) {
        this.placeHolderArrayList = placeHolderArrayList;
    }

    @NonNull
    @Override
    public charViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new charViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull charViewHolder holder, int position) {
        holder.bindChar(placeHolderArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return placeHolderArrayList.size();
    }

    public void clear() {
        this.placeHolderArrayList.clear();
        notifyDataSetChanged();
    }

    public String getWords() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < placeHolderArrayList.size(); i++) {
            stringBuilder.append(placeHolderArrayList.get(i).getCharacter());
        }
        return stringBuilder.toString();

    }

    public class charViewHolder extends RecyclerView.ViewHolder {
        TextView tvChar;

        public charViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChar = itemView.findViewById(R.id.tv_char);
        }

        public void bindChar(PlaceHolder placeHolder) {
            if (placeHolder.isVisible()) {
                tvChar.setText(placeHolder.getCharacter().toString());
                tvChar.setVisibility(View.VISIBLE);
            } else {
                tvChar.setVisibility(View.INVISIBLE);
            }
            if (placeHolder.isNull()) {
                itemView.setBackground(null);
            } else {
                itemView.setBackgroundResource(R.drawable.background_rv_item);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListenerItemView.itemClick(placeHolder, getAdapterPosition());

                }
            });
        }
    }

    public void makeWordVisible(String word) {
        for (int i = 0; i < placeHolderArrayList.size(); i++) {
            if (placeHolderArrayList.get(i).getTag() != null && placeHolderArrayList.get(i).getTag().equalsIgnoreCase(word)) {
                placeHolderArrayList.get(i).setVisible(true);
                notifyItemChanged(i);
            }
        }
    }


}
