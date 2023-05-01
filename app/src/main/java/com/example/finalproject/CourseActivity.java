package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.finalproject.Adapter.CourseAdapter;
import com.example.finalproject.Api.Api;
import com.example.finalproject.Api.RetrofitClient;
import com.example.finalproject.Model.Course;
import com.example.finalproject.databinding.ActivityCourseBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {
ActivityCourseBinding binding;
    RecyclerView recyclerView;
    ArrayList<Course> allCourse=new ArrayList<>();
    CourseAdapter courseAdapter;
    public static String ProgramText = "";
    public static String ProgramDesc = "";
    public static int ProgramId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding= ActivityCourseBinding .inflate(getLayoutInflater());
       // BottomNavigationView BNV=findViewById( R.id.navigation ) ;
        //bottom navigation code
        binding.navigation.setSelectedItemId( R.id.course );
        binding.navigation.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch ( item.getItemId()){
                    case R.id.course:
                    case R.id.allocation:
                        startActivity( new Intent(getApplicationContext(),AllocationActivity.class) );
                        finish();
                        overridePendingTransition( 0,0 );
                        return true;
                    case R.id.plos:
                        startActivity( new Intent(getApplicationContext(),PlosActivity.class) );
                        finish();
                        overridePendingTransition( 0,0 );
                        return true;
                    default:
                        return false;
                }
            }
        } );

//ye neacye two lines wo jo peachye sye data is activity me la rhye tye
        binding.ProgramTxt.setText( ProgramText );
        binding.ProgramDesText.setText( ProgramDesc );

        setContentView(binding.getRoot());
        RetrofitClient p1=RetrofitClient.getInstance();
        Api api=p1.getMyApi();
        courseAdapter = new CourseAdapter( allCourse, this );
        binding.RecyclerviewCourse.setLayoutManager( new LinearLayoutManager( CourseActivity.this) );
        binding.RecyclerviewCourse.setAdapter( courseAdapter );

        api.getAllCourses(ProgramId).enqueue( new Callback<ArrayList<Course>>() {
            @Override
            public void onResponse(Call<ArrayList<Course>> call, Response<ArrayList<Course>> response) {
                Toast.makeText(getApplicationContext(),response.code()+":Get", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()) {
                    allCourse.clear();
                    Toast.makeText( getApplicationContext(), response.code() + ":get", Toast.LENGTH_SHORT ).show();
                    allCourse.addAll( response.body() );
                    courseAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Course>> call, Throwable t) {
                Toast.makeText(CourseActivity.this,"ERROR:"+ t.toString(), Toast.LENGTH_SHORT).show();


            }
        } );


    }



    public void recyclerViewCellClick(Course obj) {
        Intent i=new Intent(CourseActivity.this, CourseActivity.class);
        startActivity( i );
    }

    public void recyclerView(Course obj) {
        Intent i=new Intent(CourseActivity.this,CourseActivity.class);
        startActivity( i );
    }

    public void recyclerViewedit(Course obj) {
        Intent i=new Intent(CourseActivity.this,CourseActivity.class);
        startActivity( i );
    }

}