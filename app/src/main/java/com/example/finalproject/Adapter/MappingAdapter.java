package com.example.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Model.Allocation;
import com.example.finalproject.Model.PLOMapping;
import com.example.finalproject.Model.PLOs;
import com.example.finalproject.databinding.RecyclerviewAllocationCelllayoutBinding;
import com.example.finalproject.databinding.RecyclerviewMappingPlosCellLayoutBinding;
import com.example.finalproject.databinding.RecyclerviewMappingPlosCellLayoutBinding;

import java.util.ArrayList;

public class MappingAdapter extends RecyclerView.Adapter<MappingAdapter.MappingViewHolder> {

    @NonNull
    //plomapping class me plo kye arraylist raki ho ghi
    private ArrayList<PLOMapping> list;
    private Context context;
    public MappingAdapter (ArrayList<PLOMapping> list, Context context) {
        this.list=list;
        this.context=context;
    }
    @Override
    public MappingAdapter.MappingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewMappingPlosCellLayoutBinding b= RecyclerviewMappingPlosCellLayoutBinding.inflate(LayoutInflater.from( parent.getContext()),parent,false );
        MappingAdapter.MappingViewHolder vh=new  MappingAdapter.MappingViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MappingViewHolder holder, int position) {
        //plomapping k  obj create kiya ha
        PLOMapping    obj= list.get(position);
       //holder.binding.txtplo1.setText( obj.getName() );
        //use for loop and for loop me plo ki list k obj pass kiya ha
        for (PLOs p:obj.ploList
             ) {
            //agr postion 0 ho ghi tu plo k name show krwaye ga
        if(position==0) {
            TextView tv = new TextView( context );

            tv.setText( p.Name );
            holder.binding.llcontents.addView( tv );
        }
        //else me edittext hn ghy
        else{
            EditText et = new EditText( context );
            et.setWidth(30 );
            et.setText("");
            holder.binding.llcontents.addView( et );
        }

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MappingViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewMappingPlosCellLayoutBinding binding;
        public MappingViewHolder(@NonNull RecyclerviewMappingPlosCellLayoutBinding itemView) {
            super( itemView.getRoot() );
            binding=itemView;
        }
    }
}
