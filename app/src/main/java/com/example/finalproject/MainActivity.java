package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.Api.Api;
import com.example.finalproject.Api.RetrofitClient;
import com.example.finalproject.Model.Users;
import com.example.finalproject.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //btn login code
        binding.BtnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitClient client= RetrofitClient.getInstance();
                Api api= client.getMyApi();

                String uid=binding.UserName.getText().toString();
                String pass=binding.UserPassword.getText().toString();
                api.LoginUser( uid,pass ).enqueue( new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        Toast.makeText(getApplicationContext(),"uuuu"+ response.code()+"code", Toast.LENGTH_SHORT).show();
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"mmm"+ response.code()+"code", Toast.LENGTH_SHORT).show();
                            Users u=response.body();
                            //System.out.println("data"+u.getRole());
                         String role =  u.getRole();
                         if(role=="teacher") {

                             System.out.println("login");
                             Intent myIntent = new Intent(getApplicationContext(), CourseActivity.class);
                             //  myIntent.putExtra("myObjects", response);
                             startActivity(myIntent);
                         }
                            if(role.contains( "admin" )) {
                                System.out.println("admin "+u.getRole());
                                Intent myIntent = new Intent(getApplicationContext(), ProgramActivity.class);
//                                myIntent.putExtra("myObjects", response);
                                    startActivity(myIntent);
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

                    }
                } );
            }
        });


    }


//    void parseJson(String response){
//        try {
//            JSONArray jsonArray = new JSONArray(response);
//            JSONObject jobj=jsonArray.getJSONObject(0);
//            String role;
//            role= jobj.getString("Role");
//            if(role.equalsIgnoreCase("teacher")){
//                Intent myIntent = new Intent(getApplicationContext(), CourseActivity.class);
//                myIntent.putExtra("myObjects", response);
//                startActivity(myIntent);
//            }
//            else if(role.equalsIgnoreCase("hod")){
//
//                Intent myIntent = new Intent(getApplicationContext(), ProgramActivity.class);
//                myIntent.putExtra("myObjects", response);
//                startActivity(myIntent);
//
//            }
//        }
//        catch (Exception ex)
//        {
//
//        }
//    }
}

