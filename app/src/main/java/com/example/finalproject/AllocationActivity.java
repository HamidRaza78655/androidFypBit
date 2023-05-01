package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Adapter.AllocationAdapter;
import com.example.finalproject.Adapter.ProgramAdapter;
import com.example.finalproject.Api.Api;
import com.example.finalproject.Api.RetrofitClient;
import com.example.finalproject.Model.Allocation;
import com.example.finalproject.Model.Course;
import com.example.finalproject.Model.Program;
import com.example.finalproject.databinding.ActivityAllocationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AllocationActivity extends AppCompatActivity {
    ActivityAllocationBinding binding;
    private int createRequestCode = 1;
    private int openRequestCode = 2;
    private Uri sourceUri;
    private Uri targetUri;


    ArrayList<Allocation> allallocations=new ArrayList<>();
    AllocationAdapter AllocationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding= ActivityAllocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RetrofitClient p1=RetrofitClient.getInstance();
        Api api=p1.getMyApi();

        api.getAllocation( 1 ).enqueue( new Callback<ArrayList<Allocation>>() {
            @Override
            public void onResponse(Call<ArrayList<Allocation>> call, Response<ArrayList<Allocation>> response) {
                Toast.makeText(getApplicationContext(),response.code()+":Get", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){
                    allallocations.clear();
                    allallocations.addAll( response.body());
                    AllocationAdapter = new AllocationAdapter ( allallocations,getApplicationContext());
                    binding.RecyclerviewAllocation.setLayoutManager( new LinearLayoutManager( AllocationActivity.this) );
                    binding.RecyclerviewAllocation.setAdapter( AllocationAdapter );

//                    Toast.makeText(getApplicationContext(),response.code()+":get", Toast.LENGTH_SHORT).show();

//                   AllocationAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Allocation>> call, Throwable t) {
                Toast.makeText(AllocationActivity.this,"ERROR:"+ t.toString(), Toast.LENGTH_SHORT).show();


            }
        } );

        binding.BtnBrowse.setOnClickListener( new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           openFile();

       }
   } );
   binding.BtnUpload.setOnClickListener( new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           RetrofitClient client= RetrofitClient.getInstance();
           Api api= client.getMyApi();
           MultipartBody.Part file = null;
           try {
               file = api.prepareFilePart("file", sourceUri, getApplicationContext());
           } catch (IOException e) {
               e.printStackTrace();
           }
            api.Uploadfile(file).enqueue( new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText( AllocationActivity.this, ""+response.body(), Toast.LENGTH_SHORT ).show();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            } );
       }
   } );





        BottomNavigationView BNV = (BottomNavigationView) findViewById( R.id.navigation );
        BNV.setSelectedItemId( R.id.allocation );
        BNV.setOnNavigationItemReselectedListener( new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.course:
                        startActivity( new Intent( getApplicationContext(), CourseBottomActivity.class ) );
                        finish();
                        overridePendingTransition( 0, 0 );
                        return;
                    case R.id.allocation:

                    case R.id.plos:
                        startActivity( new Intent( getApplicationContext(), PlosActivity.class ) );
                        finish();
                        overridePendingTransition( 0, 0 );
                        return;

                }
            }
        } );
    }
    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, openRequestCode);
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("/");
//                int REQUEST_CODE = 0;
//                startActivityForResult(intent, GALLERY_REQUEST_CODE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == openRequestCode && resultCode == RESULT_OK) {
            sourceUri = resultData.getData();
            Toast.makeText(this, "Selected", Toast.LENGTH_SHORT).show();

        }

    }

            }


