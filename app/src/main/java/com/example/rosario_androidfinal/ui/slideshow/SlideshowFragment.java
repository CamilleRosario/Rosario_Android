package com.example.rosario_androidfinal.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosario_androidfinal.databinding.FragmentSlideshowBinding;

import java.util.ArrayList;
import java.util.Random;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    private Button btnGuess;
    private EditText AnsGuess;
    private TextView res;

    int NumRandom;
    Random random= new Random();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        NumRandom = createNumber();
       btnGuess = binding.guessbtn;
        AnsGuess = binding.guess;
        res = binding.lowhigh;

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnGuess.getText().toString().equals("Guess")){
                    int GuessAnswer= Integer.parseInt(AnsGuess.getText().toString());
                    if(NumRandom== GuessAnswer){
                        res.setText("You got it!");
                        btnGuess.setText("Guess Again");
                    }
                    else if(GuessAnswer > NumRandom){
                        res.setText("Try Lower");
                    }
                    else if(GuessAnswer < NumRandom){
                        res.setText("Try Higher");
                    }
                    AnsGuess.setText("");
                }
                else{
                    NumRandom = createNumber();
                    AnsGuess.setText("");
                    res.setText("Start Guessing!");
                    btnGuess.setText("Guess");
                }
            }
        });
        Log.d("GameFragment", "onCreateView called");
        return root;
    }


    private int createNumber(){
        // Generate a random integer

        int randomNumber = random.nextInt(101);
        return randomNumber;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

