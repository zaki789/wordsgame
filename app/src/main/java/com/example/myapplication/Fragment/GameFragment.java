package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.AdapterCharacter;
import com.example.myapplication.Model.GamePlayUtil;
import com.example.myapplication.Model.Level;
import com.example.myapplication.Model.PlaceHolder;
import com.example.myapplication.R;
import com.example.myapplication.onClickListenerItemView;

import java.util.ArrayList;

public class GameFragment extends Fragment {
    public static final String KEY = "level";
    private Level level;
    private View guessActionsContainer;
    private View acceptBtn;
    private View cancelBtn;
    private AdapterCharacter characterGuss;
    private AdapterCharacter wordAdapter ;
    private static final String TAG = "Cannot invoke method length() on null object";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        level = getArguments().getParcelable(KEY);
//        Log.i(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        guessActionsContainer = view.findViewById(R.id.frame_game_guessActionsContainer);
        acceptBtn = view.findViewById(R.id.btn_game_acceptAction);
        cancelBtn = view.findViewById(R.id.btn_game_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessActionsContainer.setVisibility(View.GONE);
                characterGuss.clear();
            }
        });
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = characterGuss.getWords();
                for (int i = 0; i < level.getWords().size(); i++) {
                    if (word.equalsIgnoreCase(level.getWords().get(i))) {
                        wordAdapter.makeWordVisible(word);
                        Toast.makeText(getContext(), "words entered" + word, Toast.LENGTH_SHORT).show();
                        cancelBtn.performClick();
                        return;
                    }

                }
                cancelBtn.performClick();
                Toast.makeText(getContext(), "words doesn't match ", Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.rv_game_characters);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        ArrayList<Character> characters = GamePlayUtil.extractUniqueChar(level.getWords());
        ArrayList<PlaceHolder> placeHolderArrayList = new ArrayList<>();
        for (int i = 0; i < characters.size(); i++) {
            PlaceHolder placeHolder = new PlaceHolder();
            placeHolder.setVisible(true);
            placeHolder.setCharacter(characters.get(i));
            placeHolderArrayList.add(placeHolder);
        }
        AdapterCharacter adapterCharacter = new AdapterCharacter(placeHolderArrayList);
        recyclerView.setAdapter(adapterCharacter);
        adapterCharacter.setOnClickListenerItemView(new onClickListenerItemView() {
            @Override
            public void itemClick(Object item, int position) {
                guessActionsContainer.setVisibility(View.VISIBLE);
                characterGuss.add((PlaceHolder) item);
            }
        });
        RecyclerView recyclerView1 = view.findViewById(R.id.rv_game_guess);
        characterGuss = new AdapterCharacter();
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView1.setAdapter(characterGuss);

        int maxLength = 0;
        for (int i = 0; i < level.getWords().size(); i++) {
            if (i < level.getWords().get(i).length()) {
                maxLength = level.getWords().get(i).length();
            }

        }


        RecyclerView rvWordsGuess = view.findViewById(R.id.rv_game_words);
        rvWordsGuess.setLayoutManager(new GridLayoutManager(getContext(), maxLength));
//        ArrayList<Character> getChar = new ArrayList<>();
        ArrayList<PlaceHolder> getChar = new ArrayList<>();
        for (int i = 0; i < level.getWords().size(); i++) {
            for (int j = 0; j < level.getWords().get(i).length(); j++) {
                PlaceHolder placeHolder = new PlaceHolder();
                placeHolder.setCharacter(level.getWords().get(i).charAt(j));
                placeHolder.setVisible(false);
                placeHolder.setNull(false);
                placeHolder.setTag(level.getWords().get(i));
                getChar.add(placeHolder);
            }
        }
      wordAdapter = new AdapterCharacter(getChar);
        rvWordsGuess.setAdapter(wordAdapter);

    }


}
