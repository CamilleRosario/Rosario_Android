package com.example.rosario_androidfinal.ui.gallery;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import com.example.rosario_androidfinal.R;
import com.example.rosario_androidfinal.VisMisTile;

import java.util.ArrayList;


public class ForLoadCont extends RecyclerView.Adapter<ForLoadCont.ViewStorage>{
    private ArrayList<VisMisTile> details = new ArrayList<>();
    public ForLoadCont(ArrayList<VisMisTile> details){
        this.details = details;
    }
    public class ViewStorage extends RecyclerView.ViewHolder{
        public TextView Title;
        public TextView detail;
        public ViewStorage (final View view){
            super(view);
            Title = view.findViewById(R.id.Title);
            detail = view.findViewById(R.id.Content);

        }
    }
    @NonNull
    @Override
    public ForLoadCont.ViewStorage onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item =LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_l_u__vis__mis, parent, false
        );
        return  new ViewStorage(item);

    }
    public void onBindViewHolder(@NonNull ForLoadCont.ViewStorage parent, int viewType){

        String headline = details.get(viewType).getHeadline();
        String detail = details.get(viewType).getDetails();
        parent.Title.setText(headline);
        parent.detail.setText(detail);
    }
    @Override
    public int getItemCount(){
        return details.size();
    }

 }
