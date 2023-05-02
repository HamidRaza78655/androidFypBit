package com.example.finalproject.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Model.Allocation;
import com.example.finalproject.Model.Program;
import com.example.finalproject.R;
import com.example.finalproject.databinding.RecyclerviewAllocationCelllayoutBinding;
import com.example.finalproject.databinding.RecyclerviewProgramItemCellBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AllocationAdapter extends RecyclerView.Adapter<AllocationAdapter.AllocationViewHolder> {
    @NonNull
    private ArrayList<Allocation> list;
    private Context context;
    Map<String, List<Allocation>> groupedData = new HashMap<>();
    ArrayList<String> groupedDatakey= new ArrayList<>();
    List<Allocation> dataList = new ArrayList<>();
    List<RadioButton> rbList = new ArrayList<>();

    private Map<String, List<Allocation>> groupData(ArrayList<Allocation> dataList) {
        Map<String, List<Allocation>> groupedData = new HashMap<>();
        for (Allocation data : dataList) {
            if (!groupedData.containsKey(data.getName())) {
                groupedData.put(data.getName(), new ArrayList<>());
            }
            groupedData.get(data.getName()).add(data);
        }
        return groupedData;
    }

    public AllocationAdapter(ArrayList<Allocation> list, Context context) {
        this.list=list;
        this.context=context;
        this.groupedData = groupData(list);


//       Toast.makeText( context, ""+list.get( 0 ).getName(), Toast.LENGTH_SHORT ).show();
//        System.out.println("jjjj"+list.get( 0 ).Name);
//        for (Allocation data : list) {
//         //   Toast.makeText( context, ""+data.getName(), Toast.LENGTH_SHORT ).show();
//           System.out.println("jjjj"+data.Name);
//            if (!groupedData.containsKey(data.Name)) {
//                groupedData.put(data.getName(), new ArrayList<>());
//                System.out.println(""+data.getName());
//               // Toast.makeText( context, ""+data.getName(), Toast.LENGTH_SHORT ).show();
//            }
//            groupedData.get(data.Name).add(data);
            for (Map.Entry<String, List<Allocation>> entry : groupedData.entrySet()) {
                dataList.addAll(entry.getValue());
            }
//        }

//        for (String key : groupedData.keySet()) {
//            groupedDatakey.add( key );
//            // Process the list of CustomData objects for this key
//        }
//        Gson gson= new Gson();
//        Toast.makeText( context, "jjj"+groupedData.size(), Toast.LENGTH_SHORT ).show();
////        for (:
////             ) {
////
////        }
//        System.out.println("jjj"+groupedData.size());
    }

    @Override
    public AllocationAdapter.AllocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewAllocationCelllayoutBinding b= RecyclerviewAllocationCelllayoutBinding.inflate
                ( LayoutInflater.from( parent.getContext() ),parent,false );
        AllocationAdapter.AllocationViewHolder vh=new AllocationAdapter.AllocationViewHolder(b);
        return vh;
    }
int index=0;
    int tid=1;
    @Override
    public void onBindViewHolder(@NonNull AllocationAdapter.AllocationViewHolder holder, int position) {
        Allocation    data= dataList.get(position);
//        List<Allocation> dataWithSameName = groupedData.get(groupedDatakey.get( index ));
        holder.binding.txtCoursEname.setText(data.getName()+"");
        holder.binding.txtsection.setText( data.Section + "" );
        holder.binding.txtTeacherName.setText( data.getTeacher_Name()+"" );
        holder.binding.radioButtonClos.setChecked( Boolean.parseBoolean( data.Allocate ) );
        holder.binding.radioButtonClos.setTag( data.Course_Id );
        holder.binding.radioButtonClos.setTag( R.string.app_name,tid++ );
        rbList.add( holder.binding.radioButtonClos );
        holder.binding.radioButtonClos.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    for (RadioButton rb:rbList
                         ) {
                        int t1 = Integer.parseInt(buttonView.getTag().toString());
                        int t2 = Integer.parseInt(rb.getTag().toString());
                        if(t1==t2){
                            //same group if
                            int t11 = Integer.parseInt(buttonView.getTag(R.string.app_name).toString());
                            int t22 = Integer.parseInt(rb.getTag(R.string.app_name).toString());
                            if(t11!=t22){
                                //other than this one in same group
                                rb.setChecked( false );
                            }
                        }
                    }
                }
            }
        } );
        //        for (int i=0; i<dataWithSameName.size();i++){
//            Allocation data=dataWithSameName.get( i );
//
//            if (i == dataWithSameName.size()-1 && index < groupedDatakey.size() - 1) {
//                index++;
//            }
//        }
//        if (index >= groupedDatakey.size()) {
//            index = 0;
//        }
//        for (Allocation data:dataWithSameName) {
////            Allocation    obj2= list.get(position);
//
//        }
    }

    @Override
    public int getItemCount() {
//        int count = 0;
//        for (List<Allocation> group : groupedData.values()) {
//            count += group.size();
//        }
//        return count;
       return dataList.size();
    }

    public class AllocationViewHolder extends RecyclerView.ViewHolder{
        RecyclerviewAllocationCelllayoutBinding binding;
        public AllocationViewHolder(@NonNull  RecyclerviewAllocationCelllayoutBinding  itemView) {
            super( itemView.getRoot() );
            binding=itemView;

        }
    }
}
