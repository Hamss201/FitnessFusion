package com.example.tester;

public class UserModel06 {
    public String full;
    public String pho;
    public String pas;
    public String ema;

    public UserModel06() {

    }

    public UserModel06(String full, String pho, String pas, String ema) {
        this.full = full;
        this.pho = pho;
        this.pas = pas;
        this.ema = ema;
    }

    public String getName() {
        return full;
    }

    public String getEmail() {
        return ema;
    }

    public String getPassword() {
        return pas;
    }

    public String getPhone() {
        return pho;
    }
}