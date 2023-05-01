package com.example.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.CourseActivity;
import com.example.finalproject.Model.Course;
import com.example.finalproject.Model.Program;
import com.example.finalproject.ProgramActivity;
import com.example.finalproject.databinding.RecyclerviewCourseItemCellBinding;

import java.util.ArrayList;


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private ArrayList<Course> list;
    private Context context;
    public CourseAdapter(ArrayList<Course> list, Context context) {
        this.list=list;
        this.context=context;
    }
    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewCourseItemCellBinding b=RecyclerviewCourseItemCellBinding.inflate(LayoutInflater.from( parent.getContext() ),parent,false );
        CourseViewHolder vh =new CourseViewHolder( b );
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        Course obj= list.get(position);
holder.Bin.txtViewCourse.setText( obj.C_Id+"   "+obj.C_Code+""+obj.Credit_Hour+""+obj.Name );
holder.Bin.btnClos.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        CourseActivity activity=( CourseActivity) context;
        activity.recyclerViewCellClick(obj);
    }
} );
holder.Bin.btnDelete.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        CourseActivity activity=( CourseActivity) context;
        activity.recyclerView(obj);


    }
} );
        holder.Bin.btnEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseActivity activity=( CourseActivity) context;
                activity.recyclerViewedit(obj);

            }
        } );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{
        RecyclerviewCourseItemCellBinding Bin;
        public CourseViewHolder(@NonNull RecyclerviewCourseItemCellBinding  itemView) {
            super(itemView.getRoot() );
            Bin=itemView;
        }
    }
}
