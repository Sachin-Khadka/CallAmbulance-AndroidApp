package com.joker.callambulance.entities;

public class User
{
    private  int _id; //auto increment
    private String fullName;
    private  String email;
    private String password;
    private String repeatPassword;


    public User()
    {

    }

    public User(String fullName)
    {
        this.fullName = fullName;
    }

    public User(int _id, String fullName, String email, String password, String repeatPassword)
    {
        this._id = _id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public User(String fullName, String email, String password, String repeatPassword)
    {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}




