package com.example.finalproject.Model;

public class PLOs {
    public int   Plo_Id;
    public String   Name;
    public String Title;
    public int Value;

    public int getPlo_Id() {
        return Plo_Id;
    }

    public String getName() {
        return Name;
    }

    public int getProgram_Id() {
        return Program_Id;
    }

    public void setProgram_Id(int program_Id) {
        Program_Id = program_Id;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPlo_Id(int plo_Id) {
        Plo_Id = plo_Id;
    }

    public   String        Description;
    public    int       Program_Id;
}
