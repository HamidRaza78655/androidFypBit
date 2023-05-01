package com.example.finalproject.Model;

public class Course {
    public int  C_Id;
    public String        C_Code;
    public String       Name;
    public String  Credit_Hour;
    public int Program_Id;

    public int getC_Id() {
        return C_Id;
    }

    public int getProgram_Id() {
        return Program_Id;
    }

    public void setProgram_Id(int program_Id) {
        Program_Id = program_Id;
    }

    public String getC_Code() {
        return C_Code;
    }

    public String getCredit_Hour() {
        return Credit_Hour;
    }

    public void setCredit_Hour(String credit_Hour) {
        Credit_Hour = credit_Hour;
    }

    public void setC_Code(String c_Code) {
        C_Code = c_Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setC_Id(int c_Id) {
        C_Id = c_Id;
    }
}
