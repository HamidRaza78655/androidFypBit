package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Adapter.CourceMappingAdapter;
import com.example.finalproject.Adapter.MappingAdapter;
import com.example.finalproject.Api.Api;
import com.example.finalproject.Api.RetrofitClient;
import com.example.finalproject.Model.Course;
import com.example.finalproject.Model.PLOMapping;
import com.example.finalproject.Model.PLOs;
import com.example.finalproject.databinding.ActivityMappingBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MappingActivity extends AppCompatActivity {
    ActivityMappingBinding binding;
    ArrayList<PLOMapping> mappingScore = new ArrayList<>();
    //neachye ak plo k adapter ki list create ki ha dosra plomapping k adaptr ki list create ki ha
    ArrayList<PLOs> allplos = new ArrayList<>();
    ArrayList<PLOMapping> mappings = new ArrayList<>();
    //adapter k obj create
    MappingAdapter plOsAdapter;
    CourceMappingAdapter courceMappingAdapter;
    //list adapter course ki
    ArrayList<Course> allCourse = new ArrayList<>();

    ArrayList<TextView> percentLabels = new ArrayList<>();
    int percent[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityMappingBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );
        binding.buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLOMapping p = new PLOMapping();
                RetrofitClient p1 = RetrofitClient.getInstance();
                Api api = p1.getMyApi();
                api.Mapping( mappingScore ).enqueue( new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText( getApplicationContext(), response.code() + ":Save", Toast.LENGTH_SHORT ).show();
                        if (response.isSuccessful()) {
                            Toast.makeText( getApplicationContext(), response.code() + ":save", Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText( MappingActivity.this, t.toString(), Toast.LENGTH_SHORT ).show();

                    }
                } );
            }
        } );

        //  binding.txtProgramTitle.setText( CourseActivity.ProgramText );

        RetrofitClient p1 = RetrofitClient.getInstance();
        Api api = p1.getMyApi();


        plOsAdapter = new MappingAdapter( mappings, this );
        // binding.RecyclerviewMappingPlos.setLayoutManager( new LinearLayoutManager( getApplicationContext()) );
        // binding.RecyclerviewMappingPlos.setAdapter(plOsAdapter );


        ///end plos
        RetrofitClient p2 = RetrofitClient.getInstance();
        Api api2 = p2.getMyApi();
//        allCourse.add( new Course() );
//        allCourse.get( 0 ).setName( "DS" );
//        allCourse.add( new Course() );
//        allCourse.get( 0 ).setName( "TBW" );
//        allCourse.add( new Course() );
//        allCourse.get( 1 ).setName( "DAM" );
//        allCourse.add( new Course() );
//        allCourse.get( 2 ).setName( "PDC" );

//neachye line me coursemappingadapter me jo allcourse lehka ha ye upr obj bania ha is ki list me
        courceMappingAdapter = new CourceMappingAdapter( allCourse, this );
        //setlayoutmanager b orientation k liye used hota ha
        binding.RecyclerviewMappingCources.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
        //ye nechye next line adaptr set krnye k liye used hoti ha
        binding.RecyclerviewMappingCources.setAdapter( courceMappingAdapter );
        //next get couces from api

        api2.getAllCourse( 1 ).enqueue( new Callback<ArrayList<Course>>() {
            @Override
            public void onResponse(Call<ArrayList<Course>> call, Response<ArrayList<Course>> response) {
                Toast.makeText( getApplicationContext(), response.code() + ":Get", Toast.LENGTH_SHORT ).show();
                if (response.isSuccessful()) {
                    allCourse.clear();
                    Course c = new Course();
                    c.Name = "R%";
                    allCourse.add( c );
                    c = new Course();
                    c.Name = "C/P";
                    allCourse.add( c );
                    Toast.makeText( getApplicationContext(), response.body().get( 0 ).Program_Id + ":get", Toast.LENGTH_SHORT ).show();
                    allCourse.addAll( response.body() );
//
//                  //  Toast.makeText( MappingActivity.this, ""+allCourse.get( 0 ).Name, Toast.LENGTH_SHORT ).show();
                    binding.RecyclerviewMappingCources.getAdapter().notifyDataSetChanged();


                    showPLOs();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Course>> call, Throwable t) {
                Toast.makeText( getApplicationContext(), "ERROR:" + t.toString(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    //ArrayList<Course_PLOs_Mapping> plOsWeight = new ArrayList<>();
    private void showPLOs() {
        RetrofitClient p2 = RetrofitClient.getInstance();
        Api api = p2.getMyApi();
        api.getAllPlos( 1 ).enqueue( new Callback<ArrayList<PLOs>>() {
            @Override
            public void onResponse(Call<ArrayList<PLOs>> call, Response<ArrayList<PLOs>> response) {
                //    Toast.makeText(getApplicationContext(),response.code()+":Get", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    allplos.clear();
                    //      Toast.makeText( getApplicationContext(), response.code() + ":get", Toast.LENGTH_SHORT ).show();
                    allplos.addAll( response.body() );


                    LinearLayout llPercent = findViewById( R.id.llPercent );

//horizontal scroll view used
                    HorizontalScrollView hsv = findViewById( R.id.hsv );
                    hsv.removeAllViews();
//linear layout used for setting plos in orizontally
                    LinearLayout ll = new LinearLayout( MappingActivity.this );
                    ll.setOrientation( LinearLayout.HORIZONTAL );

                    percent = new int[allCourse.size()][allplos.size()];

                    mappingScore.clear();

                    percentLabels = new ArrayList<>();
                    int row = -1, col = -1;
                    //for each loop is used and same in java plos are class p is obj and allpos are obj of class ploslist that are given in abouve
                    for (PLOs p : allplos
                    ) {
                        row++;
                        //plo k col k ak colm k liye llcontents used kiya ha
                        LinearLayout llcontents = new LinearLayout( MappingActivity.this );
                        llcontents.setOrientation( LinearLayout.VERTICAL );
                        int size = 0;
                        int ccount = 0;
                        col = -1;
                        for (Course c : allCourse
                        ) {
                            ccount++;
                            if (ccount <= 2)
                                continue;

                            if (llcontents.getChildCount() == 0) {
                                //on top of each, add Plo Name
                                TextView tv = new TextView( MappingActivity.this );
                                tv.setWidth( 80 );
                                tv.setText( "0%" );
                                llcontents.addView( tv );

                                percentLabels.add( tv );

                                tv = new TextView( MappingActivity.this );
                                tv.setText( p.Name );
                                tv.setWidth( 80 );
                                llcontents.addView( tv );
                                size = tv.getWidth();
                            }

                            col++;
                            //entry
                            EditText et = new EditText( MappingActivity.this );
                            et.setTag( col + "," + row );
//                                et.setTag( 0,row );
//                                et.setTag( 1,col );
                            if (size == 0)
                                size = 80;
                            et.setTextAlignment( View.TEXT_ALIGNMENT_TEXT_END );
                            et.addTextChangedListener( new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                    if (s.length() > 0) {
                                        int pval = Integer.parseInt( s.toString() );
                                        String tags = et.getTag().toString();
                                        int c1 = Integer.parseInt( tags.split( "," )[0] );
                                        int r = Integer.parseInt( tags.split( "," )[1] );
                                        percent[c1][r] = pval;

                                        int cid = allCourse.get( c1 ).C_Id;
                                        int pid = allplos.get( r ).Plo_Id;

                                        boolean found = false;
                                        for (PLOMapping pm : mappingScore
                                        ) {
                                            if (pm.Course_Id == cid && pm.Plo_Id == pid) {
                                                found = true;
                                                pm.Weightage = pval;
                                            }
                                        }
                                        if (!found) {
                                            PLOMapping pm = new PLOMapping();
                                            pm.Course_Id = cid;
                                            pm.Plo_Id = pid;
                                            pm.Weightage = pval;
                                            mappingScore.add( pm );
                                        }


                                        String data = "";
                                        for (int y = 0; y < allplos.size(); y++) {
                                            int total = 0;
                                            for (int x = 0; x < allCourse.size(); x++) {
                                                data = data + "[" + x + "," + y + "]:" + String.valueOf( percent[x][y] );
                                                total += percent[x][y];
                                            }
                                            percentLabels.get( y ).setText( total + "" );
                                            Log.i( "Total:[" + c.Name + "]", total + "" );
                                        }
                                        //Log.i("PERCENTS::",data);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
//formula for %age.
                                }
                            } );
                            et.setWidth( size );
                            et.setText( "" );
                            llcontents.addView( et );

                        }
                        ll.addView( llcontents );

                    }
                    hsv.addView( ll );
//                    plOsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PLOs>> call, Throwable t) {
                Toast.makeText( getApplicationContext(), "ERROR:" + t.toString(), Toast.LENGTH_SHORT ).show();

            }
        } );
    }
}