package com.example.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Model.Program;
import com.example.finalproject.ProgramActivity;
import com.example.finalproject.databinding.RecyclerviewProgramItemCellBinding;

import java.util.ArrayList;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder> {
private ArrayList<Program> list;
private Context context;
    public ProgramAdapter(ArrayList<Program> list, Context context) {
        this.list=list;
        this.context=context;
    }

    @NonNull

    @Override
    public ProgramAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        RecyclerviewProgramItemCellBinding b= RecyclerviewProgramItemCellBinding.inflate
                ( LayoutInflater.from( parent.getContext() ),parent,false );
        ProgramViewHolder vh=new ProgramViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramAdapter.ProgramViewHolder holder, int position) {
        Program obj= list.get(position);
        holder.binding.txtViewTitle.setText(obj.Title+"   "+obj.Description );

        holder.binding.btnCourse.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramActivity activity=(ProgramActivity) context;
                //neachye wali line sye functn bnta ha us main progra wali activity me jis ki wajha sye wo next move kr jata ha
                activity.recyclerViewCellClick(obj);
            }
        } );
        holder.binding.btnPlos.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramActivity activity=(ProgramActivity) context;
                activity.recyclerview(obj);
            }
        } );
        holder.binding.btnMapping.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramActivity activity=(ProgramActivity) context;
               activity.recyclerViewCellClick1(obj);
            }
        } );


    }

    @Override
    public int getItemCount() {
   return list.size();

    }

    public class ProgramViewHolder  extends RecyclerView.ViewHolder{
        RecyclerviewProgramItemCellBinding binding;
        public ProgramViewHolder(@NonNull RecyclerviewProgramItemCellBinding itemView) {
            super( itemView.getRoot() );
            binding=itemView;
        }
    }
}
