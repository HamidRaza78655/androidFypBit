package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finalproject.Api.Api;
import com.example.finalproject.Model.Program;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddplospreviousDegree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_addplosprevious_degree );
        Spinner spinner = findViewById(R.id.spinner_plo);


        spinner.setVisibility(View.INVISIBLE);

//      Api.getData().enqueue( new Callback<List<Program>>() {
//            @Override
//            public void onResponse(Call<List<Program>> call, Response<List<Program>> response) {
//                List<Program> dataList = response.body();
//                ArrayAdapter<Program> adapter = new ArrayAdapter<Program>(context, android.R.layout.simple_spinner_item, dataList);
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spinner.setAdapter(adapter);
//                spinner.setVisibility(View.VISIBLE);
//            }

//
//            @Override
//            public void onFailure(Call<List<Program>> call, Throwable t) {
//                Toast.makeText(context, "Error retrieving data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//
//                spinner.setVisibility(View.VISIBLE);
//            }
//        });
    }
}