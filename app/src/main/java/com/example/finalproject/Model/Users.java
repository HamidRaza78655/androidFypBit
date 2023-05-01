package com.example.finalproject.Model;

public class Users {
  String[] Allocates;
  String[]  Attempts;
   String        Username;

    public String[] getAllocates() {
        return Allocates;
    }

    public void setAllocates(String[] allocates) {
        Allocates = allocates;
    }

    public String[] getAttempts() {
        return Attempts;
    }

    public void setAttempts(String[] attempts) {
        Attempts = attempts;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    String     Role;
       String    Password;
}
