package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.Adapter.ProgramAdapter;
import com.example.finalproject.Api.Api;
import com.example.finalproject.Api.RetrofitClient;
import com.example.finalproject.Model.Program;
import com.example.finalproject.databinding.ActivityProgramBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramActivity extends AppCompatActivity {
    Toolbar toolbar;
    ActivityProgramBinding binding;

    RecyclerView recyclerView;

    ArrayList<Program> allPrograms=new ArrayList<>();
    ProgramAdapter programAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_program );
        toolbar = findViewById( R.id.tool_bar );
        //step 1 toolbar code
        setSupportActionBar( toolbar );
        recyclerView = findViewById( R.id.Recyclerview_program);

        binding = ActivityProgramBinding.inflate( getLayoutInflater() );
        //step 2(back button)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        }
        toolbar.setTitle( "Program" );

//program adapter code
        programAdapter = new ProgramAdapter( allPrograms,this );
        RetrofitClient p1=RetrofitClient.getInstance();
        Api api=p1.getMyApi();



        recyclerView.setLayoutManager( new LinearLayoutManager( ProgramActivity.this) );
        recyclerView.setAdapter( programAdapter );

        api.getAllPrograms().enqueue( new Callback<ArrayList<Program>>() {
            @Override
            public void onResponse(Call<ArrayList<Program>> call, Response<ArrayList<Program>> response) {
                Toast.makeText(getApplicationContext(),response.code()+":Get", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){
                    allPrograms.clear();
                    Toast.makeText(getApplicationContext(),response.code()+":get", Toast.LENGTH_SHORT).show();
                    allPrograms.addAll( response.body());
                    programAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Program>> call, Throwable t) {
                Toast.makeText(ProgramActivity.this,"ERROR:"+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        } );



    }
    //step 3 menu k click

//toolbar code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater( this ).inflate( R.menu.toolbar_opt,menu );
        return super.onCreateOptionsMenu( menu );
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid= item.getItemId();
        if(itemid==R.id.opt_meassage){
            Toast.makeText(this, "open message box", Toast.LENGTH_SHORT).show();
        }
        // backpressed option k liye
        else if(itemid==R.id.optlogout){
            Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();

        }else{
            super.onBackPressed();
        }
        return super.onOptionsItemSelected( item );

    }
    //move to next activity
    public void BtnNext(View view) {
        Intent intent=new Intent(this,AddNewProgramActivity.class);
        startActivity( intent );

    }
//ye functn bananye sye ab is activity k specific kuch data jo hm chatye hn agye jaye wo next hamri batati hoi activity me move ho jata ha
    public void recyclerViewCellClick(Program obj) {
        Intent i=new Intent(ProgramActivity.this,CourseActivity.class);
        CourseActivity.ProgramText = obj.Title;
        CourseActivity.ProgramDesc = obj.Description;
        CourseActivity.ProgramId = obj.P_Id;
        startActivity( i );
    }

    public void recyclerview(Program obj) {
        Intent I=new Intent(ProgramActivity.this,PlosActivity.class);
        PlosActivity.ProgramTitle = obj.Title;
      //  PlosActivity.ProgramDesc = obj.Description;
        PlosActivity.ProgramId = obj.P_Id;

        startActivity( I );
    }

    public void recyclerViewCellClick1(Program obj) {
        Intent I=new Intent(ProgramActivity.this,MappingActivity.class);
        PlosActivity.ProgramTitle = obj.Title;
        //  PlosActivity.ProgramDesc = obj.Description;
        PlosActivity.ProgramId = obj.P_Id;

        startActivity( I );

    }
}