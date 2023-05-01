package com.example.finalproject.Model;

import java.util.ArrayList;

public class PLOMapping {
    public int Course_Id;
    public int Plo_Id;
    public int Weightage;
    public ArrayList<PLOs> ploList;
    public PLOMapping(){
        ploList=new ArrayList<>();
    }
}
