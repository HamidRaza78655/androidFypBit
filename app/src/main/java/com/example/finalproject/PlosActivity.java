package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.Adapter.CourseAdapter;
import com.example.finalproject.Adapter.MappingAdapter;
import com.example.finalproject.Adapter.PLOsAdapter;
import com.example.finalproject.Api.Api;
import com.example.finalproject.Api.RetrofitClient;
import com.example.finalproject.Model.PLOs;
import com.example.finalproject.databinding.ActivityMainBinding;
import com.example.finalproject.databinding.ActivityPlosBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlosActivity extends AppCompatActivity {
ActivityPlosBinding binding;
    RecyclerView recyclerView;
    ArrayList<PLOs> allplos=new ArrayList<>();
    PLOsAdapter plOsAdapter;
    public static int ProgramId = 0;
    public static String ProgramTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding= ActivityPlosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtProgramTitle.setText( CourseActivity.ProgramText );
        RetrofitClient p1=RetrofitClient.getInstance();
        Api api=p1.getMyApi();
        plOsAdapter = new PLOsAdapter(allplos , this );
        binding.RecyclerviewPlo.setLayoutManager( new LinearLayoutManager( PlosActivity.this) );
        binding.RecyclerviewPlo.setAdapter(plOsAdapter );
        api.getAllPlos(CourseActivity.ProgramId).enqueue( new Callback<ArrayList<PLOs>>() {
            @Override
            public void onResponse(Call<ArrayList<PLOs>> call, Response<ArrayList<PLOs>> response) {
                Toast.makeText(getApplicationContext(),response.code()+":Get", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()) {
                    allplos.clear();
                    Toast.makeText( getApplicationContext(), response.code() + ":get", Toast.LENGTH_SHORT ).show();
                    allplos.addAll( response.body() );
                    plOsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PLOs>> call, Throwable t) {
                Toast.makeText(PlosActivity.this,"ERROR:"+ t.toString(), Toast.LENGTH_SHORT).show();

            }
        } );
binding.btnPlo.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {

                // Create an Intent object to start the next activity
                Intent intent = new Intent(PlosActivity.this, AddPlos.class);

                // Start the next activity
                startActivity(intent);

    }
} );
        BottomNavigationView BNV=(BottomNavigationView)findViewById( R.id.navigation ) ;
        BNV.setSelectedItemId( R.id.plos );
        BNV.setOnNavigationItemReselectedListener( new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.course:
                        startActivity( new Intent(getApplicationContext(),CourseBottomActivity.class) );
                        finish();
                        overridePendingTransition( 0,0 );
                        return;
                    case R.id.allocation:
                        startActivity( new Intent(getApplicationContext(),AllocationActivity.class) );
                        finish();
                        overridePendingTransition( 0,0 );
                        return;
                    case R.id.plos:

                }
            }
        } );

    }

    public void recyclerViewCellClick(PLOs obj) {
      //  Intent I=new Intent(PlosActivity.this,PlosActivity.class);
     //   startActivity( I );
    }

    public void recyclerView(PLOs obj) {
        //  Intent I=new Intent(PlosActivity.this,PlosActivity.class);
        //   startActivity( I );
    }
}