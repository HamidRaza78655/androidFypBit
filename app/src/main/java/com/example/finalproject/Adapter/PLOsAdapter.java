package com.example.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Model.Course;
import com.example.finalproject.Model.PLOs;
import com.example.finalproject.Model.Program;
import com.example.finalproject.PlosActivity;
import com.example.finalproject.ProgramActivity;
import com.example.finalproject.databinding.RecyclerviewPlosItemsBinding;

import java.util.ArrayList;

public class PLOsAdapter extends RecyclerView.Adapter<PLOsAdapter.PLOsViewHolder> {
    private ArrayList<PLOs> list;
    private Context context;
    public PLOsAdapter( ArrayList<PLOs> list,Context context) {
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public PLOsAdapter.PLOsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewPlosItemsBinding b=RecyclerviewPlosItemsBinding.inflate( LayoutInflater.from( parent.getContext() ),parent,false  );
        PLOsViewHolder vh=new PLOsViewHolder( b );
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PLOsAdapter.PLOsViewHolder holder, int position) {
        PLOs obj= list.get(position);
        holder.binding.txtploname.setText( obj.Name );
        holder.binding.txtplodescription.setText( obj.Description );
        holder.binding.btndel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlosActivity activity=(PlosActivity) context;
                activity.recyclerViewCellClick(obj);
            }
        } );
        holder.binding.btnedit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlosActivity activity=(PlosActivity) context;
                activity.recyclerView(obj);

            }
        } );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PLOsViewHolder extends RecyclerView.ViewHolder{
        RecyclerviewPlosItemsBinding binding;
        public PLOsViewHolder(@NonNull RecyclerviewPlosItemsBinding itemView) {
            super(itemView.getRoot() );
            binding=itemView;
        }
    }


}

