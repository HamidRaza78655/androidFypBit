package com.example.finalproject.Api;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.finalproject.Model.Allocation;
import com.example.finalproject.Model.Course;
import com.example.finalproject.Model.Course_PLOs_Mapping;
import com.example.finalproject.Model.PLOMapping;
import com.example.finalproject.Model.PLOs;
import com.example.finalproject.Model.Program;
import com.example.finalproject.Model.Users;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface Api {
    public static String
            BASE_URL="http://192.168.43.6/DemoAPI/api/";
    public static String
            IMAGE_BASE_URL="http://192.168.43.6/FYPApi/";

    @GET("Auth/login")
    public Call<Users> LoginUser(@Query( "username" ) String user, @Query( "password" ) String pass);

    @Multipart
    @POST("Allocation/AddCoursesAllocation")
    Call<ResponseBody> AddCoursesAllocation(
            @Part("programId") RequestBody programId,
            @Part MultipartBody.Part file
    );
//
//    @GET("math/getuser")
//    public Call<User> getUser();
//
   // @GET("math/getusers")
    //public Call<ArrayList<Users>> getUsers();
//
//    @GET("math/divide")
//    public Call<Integer> divide(@Query("n1") int n1, @Query("n2") int n2);
//
  @GET("Program/GetAllPrograms")
  public Call<ArrayList<Program>> getAllPrograms();



    @POST("Program/InsertProgram")
    public Call<Program> InsertProgram(
            @Body Program obj
    );
    @POST("CourseMapingPLOs/Mapping")
    public Call<String> Mapping(
            @Body List<PLOMapping> objlist
    );
    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);

    @GET("Course/GetAllCourses")
    public Call<ArrayList<Course>> getAllCourses(@Query("id") int Id);

    @GET("Course/GetAllCourses")
    public Call<ArrayList<Course>> getAllCourse(@Query("id") int Id);
    @POST("PLO/InsertPLO")
    public Call<PLOs> InsertPlos(@Body PLOs obj);
    @GET(" PLO/GetAllPLOs")
    public Call<ArrayList<PLOs>> getAllPlos(@Query("Id") int Id);
    @Multipart
    @POST("api/add-timetable")
    public Call<String> Uploadfile(
            @Part MultipartBody.Part file);
    @GET("Allocation/getAllocation")
    public Call<ArrayList<Allocation>>getAllocation(@Query("Id") int Id);
//    @GET("Program/InsertListProgram")
//    Call<List<Program>> getData();

//
//    @Multipart
//    @POST("Customer/uploadImage")
//    public Call<String> saveUserProfile
//            (
//                    @Part ArrayList<MultipartBody.Part> images,
//                    @Part("id") RequestBody id,
//                    @Part("lname") RequestBody lname,
//                    @Part("fname") RequestBody fname,
//                    @Part("address") RequestBody address,
//                    @Part("gender") RequestBody g
//            );
    @NonNull
    public default MultipartBody.Part prepareFilePart(String partName, Uri fileUri, Context context) throws IOException {
        File file = FileUtil.from(context, fileUri);
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(context.getContentResolver().getType(fileUri)),
                        file
                );
        return MultipartBody.Part.createFormData(partName,
                file.getName(),
                requestFile);
    }
    public default RequestBody createPartFromString(String descriptionString){
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);
        return  description;
    }
}
