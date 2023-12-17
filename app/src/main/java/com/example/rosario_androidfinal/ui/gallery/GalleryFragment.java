package com.example.rosario_androidfinal.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosario_androidfinal.LU_Vis_Mis;
import com.example.rosario_androidfinal.R;
import com.example.rosario_androidfinal.VisMisTile;
import com.example.rosario_androidfinal.databinding.FragmentHomeBinding;
import com.example.rosario_androidfinal.databinding.FragmentGalleryBinding;
import com.example.rosario_androidfinal.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    SharedPreferences sharedPreferences;
    ConstraintLayout clayout;
    Button btnChangeBackground;
    Button btnChangeTextColor;
    RecyclerView content;
    TextView txtName;
    TextView txtSection;
    int defaultcolor;
    ArrayList<VisMisTile> data;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel visionMissionViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        data = populateData();
        //final TextView textView = binding.txtName;
        content = binding.recyclerView;
        txtName = binding.myName;
        txtSection = binding.mySection;
        btnChangeBackground = binding.btnbackground;
        btnChangeTextColor= binding.btntextcolor;
        Drawable backgroundDrawable = root.getBackground();
        // Check if the background is a ColorDrawable (solid color)
        if (backgroundDrawable instanceof ColorDrawable) {
            // Cast to ColorDrawable
            ColorDrawable colorDrawable = (ColorDrawable) backgroundDrawable;

            // Get the color as an integer
            defaultcolor = colorDrawable.getColor();

            // Now 'backgroundColor' holds the integer representation of the background color
        }
        btnChangeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenColorPicker("BGColor");
            }
        });
        btnChangeTextColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenColorPicker("TextColor");
            }
        });
        clayout = binding.VisMisLayout;
        setAdapter();
        int textColor = txtName.getCurrentTextColor();
        sharedPreferences = getContext().getSharedPreferences("", Context.MODE_PRIVATE);
        if(loadColor() != defaultcolor){
            clayout.setBackgroundColor(loadColor());
        }
        if(loadTextColor() != textColor){
            txtName.setTextColor(loadTextColor());
            txtSection.setTextColor(loadTextColor());
        }
        //visionMissionViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    public void OpenColorPicker(String Ctgry){
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(getContext(), defaultcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultcolor = color;
                if(Ctgry.equals("BGColor")){
                    clayout.setBackgroundColor(defaultcolor);
                }
                else if(Ctgry.equals("TextColor")){
                    txtName.setTextColor(defaultcolor);
                    txtSection.setTextColor(defaultcolor);
                }
                storeColor(defaultcolor, Ctgry);
            }
        });
        ambilWarnaDialog.show();
    }
    private void storeColor(int color, String Category){
        sharedPreferences = requireContext().getSharedPreferences("CamilleRosarioAndroid", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Category, color);
        editor.commit();
    }
    private  int loadColor(){
        SharedPreferences preferences = requireContext().getSharedPreferences("CamilleRosarioAndroid",Context.MODE_PRIVATE);
        int selectedColor =  preferences.getInt("BGColor", R.color.white);
        return  selectedColor;
    }
    private  int loadTextColor(){
        SharedPreferences preferences = requireContext().getSharedPreferences("CamilleRosarioAndroid",Context.MODE_PRIVATE);
        int selectedColor =  preferences.getInt("TextColor", R.color.black);
        return  selectedColor;
    }
    private void setAdapter() {
        ForLoadCont adapter = new ForLoadCont(data);
        RecyclerView.LayoutManager mng = new LinearLayoutManager(content.getContext());
        content.setLayoutManager(mng);
        content.setItemAnimator(new DefaultItemAnimator());
        content.setAdapter(adapter);
    }
    private ArrayList<VisMisTile> populateData(){
        ArrayList<VisMisTile> datum = new ArrayList<>();
        datum.add(new VisMisTile(
                "Laguna University Vision", "Laguna University shall be a " +
                "socially responsive educational institution of choice providing holistically " +
                "developed individuals in the Asia-Pacific Region."));
        datum.add(new VisMisTile("Laguna University Mission", "Laguna University is committed" +
                " to produce academically prepared and technically skilled individuals who " +
                "are socially and morally upright."));
        return  datum;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
