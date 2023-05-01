package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.Api.Api;
import com.example.finalproject.Api.RetrofitClient;
import com.example.finalproject.Model.Program;
import com.example.finalproject.Model.Users;
import com.example.finalproject.databinding.ActivityAddNewProgramBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewProgramActivity extends AppCompatActivity {
ActivityAddNewProgramBinding bin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        bin=ActivityAddNewProgramBinding.inflate( getLayoutInflater() );
        setContentView( bin.getRoot());
        bin.buttonAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Program P=new Program();
                P.Title=bin.editTextProgramTittle.getText().toString();
                P.Description=bin.editTextDescription.getText().toString();
                RetrofitClient p1=RetrofitClient.getInstance();
                Api api=p1.getMyApi();

                api.InsertProgram( P ).enqueue( new Callback<Program>() {
                    @Override
                    public void onResponse(Call<Program> call, Response<Program> response) {
                        Toast.makeText(getApplicationContext(),response.code()+":Added", Toast.LENGTH_SHORT).show();
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),response.code()+":Added", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Program> call, Throwable t) {
                        Toast.makeText(AddNewProgramActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                } );


            }
        } );
    }
}