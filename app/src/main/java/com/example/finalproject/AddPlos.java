package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.Api.Api;
import com.example.finalproject.Api.RetrofitClient;
import com.example.finalproject.Model.PLOs;
import com.example.finalproject.Model.Program;
import com.example.finalproject.databinding.ActivityAddPlosBinding;
import com.example.finalproject.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPlos extends AppCompatActivity {
ActivityAddPlosBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding= ActivityAddPlosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLOs P=new PLOs();
                P.Title=binding.editTextPloTitle.getText().toString();
                P.Name=binding.editTextPloName.getText().toString();
                P.Description=binding.editTextPloDes.getText().toString();
                RetrofitClient p1=RetrofitClient.getInstance();
                Api api=p1.getMyApi();
api.InsertPlos( P ).enqueue( new Callback<PLOs>() {
    @Override
    public void onResponse(Call<PLOs> call, Response<PLOs> response) {
        Toast.makeText(getApplicationContext(),response.code()+":Added", Toast.LENGTH_SHORT).show();
        if(response.isSuccessful()){
            Toast.makeText(getApplicationContext(),response.code()+":Added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<PLOs> call, Throwable t) {
        Toast.makeText(AddPlos.this, t.toString(), Toast.LENGTH_SHORT).show();

    }
} );
            }
        } );
    }
}