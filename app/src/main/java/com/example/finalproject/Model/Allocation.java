package com.example.finalproject.Model;

public class Allocation {
    public int   Ac_Id;
    public int Course_Id;

    public String getTeacher_Name() {
        return Teacher_Name;
    }

    public int getProgram_Id() {
        return Program_Id;
    }

    public void setProgram_Id(int program_Id) {
        Program_Id = program_Id;
    }

    public void setTeacher_Name(String teacher_Name) {
        Teacher_Name = teacher_Name;
    }

    public String getAllocate() {
        return Allocate;
    }

    public void setAllocate(String allocate) {
        Allocate = allocate;
    }

    public String getName() {
        return Name;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getCredit_Hour() {
        return Credit_Hour;
    }

    public void setCredit_Hour(String credit_Hour) {
        Credit_Hour = credit_Hour;
    }

    public void setName(String name) {
        Name = name;
    }

    public String C_Code;

    public int getAc_Id() {
        return Ac_Id;
    }

    public String getC_Code() {
        return C_Code;
    }

    public void setC_Code(String c_Code) {
        C_Code = c_Code;
    }

    public void setAc_Id(int ac_Id) {
        Ac_Id = ac_Id;
    }

    public int getCourse_Id() {
        return Course_Id;
    }

    public void setCourse_Id(int course_Id) {
        Course_Id = course_Id;
    }

    public String Name;
    public String Credit_Hour;
    public String Username;
    public String Semester;
    public String Section;
    public String Allocate;
    public String Teacher_Name;
    public int Program_Id;
}
