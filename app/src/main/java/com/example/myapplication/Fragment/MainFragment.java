package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.AdapterLevel;
import com.example.myapplication.Model.GamePlayUtil;
import com.example.myapplication.R;
import com.example.myapplication.onClickListenerItemView;

public class MainFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rvMain);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        AdapterLevel adapterLevel = new AdapterLevel(GamePlayUtil.createLevels(), new onClickListenerItemView() {
            @Override
            public void itemClick(Object item, int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(new GameFragment().KEY, (Parcelable) item);
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_gameFragment, bundle);
            }
        });
        recyclerView.setAdapter(adapterLevel);

    }


}









