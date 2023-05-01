package com.example.finalproject.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Model.Course;
import com.example.finalproject.R;
import com.example.finalproject.databinding.RecyclerviewCourseItemCellBinding;
import com.example.finalproject.databinding.RecyclerviewMappingCoursesCellBinding;

import java.util.ArrayList;

public class CourceMappingAdapter extends RecyclerView.Adapter<CourceMappingAdapter.CourceMappingViewHolder> {
    @NonNull
    private ArrayList<Course> list;
    private Context context;

    public CourceMappingAdapter(@NonNull ArrayList<Course> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public CourceMappingAdapter.CourceMappingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewMappingCoursesCellBinding b = RecyclerviewMappingCoursesCellBinding.inflate( LayoutInflater.from( parent.getContext() ), parent, false );
        CourceMappingViewHolder vh = new CourceMappingViewHolder( b );
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CourceMappingAdapter.CourceMappingViewHolder holder, int position) {
        Course obj = list.get( position );
        String courseName = obj.Name;
        //ye nechye ki lines subject k name ki list ko starting kye fist first word ko merge krwa kr show krwaye ga

        String[] words = courseName.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0)));
        }
        String result = sb.toString();
        if(position==0)
            result = "R %";
        else if(position==1)
            result = "C/P";
        holder.binding.txtmapcources.setText( result );
        holder.bind( position );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CourceMappingViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewMappingCoursesCellBinding binding;
        LinearLayout linearLayout;

        public CourceMappingViewHolder(@NonNull RecyclerviewMappingCoursesCellBinding itemView) {
            super( itemView.getRoot() );
            binding = itemView;
            linearLayout=binding.RecyclerviewCellCreate;
        }

        public void bind(int position) {
//            linearLayout.removeAllViews();
//            for (int i = 1; i <= 5 + 1; i++) {
//                EditText editText = new EditText( itemView.getContext() );
//                editText.setLayoutParams( new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT ) );
//                editText.setHint( "EditText " + i );
//                editText.addTextChangedListener( new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        Toast.makeText( itemView.getContext(), "EditText " + charSequence, Toast.LENGTH_SHORT ).show();
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable editable) {
//                        //  Toast.makeText(itemView.getContext(), "EditText "  +editable, Toast.LENGTH_SHORT).show();
//                    }
//                } );
////                editText.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        Toast.makeText(itemView.getContext(), "EditText "  +, Toast.LENGTH_SHORT).show();
////                    }
////                });
//                linearLayout.addView( editText );
//            }
        }
    }
}

