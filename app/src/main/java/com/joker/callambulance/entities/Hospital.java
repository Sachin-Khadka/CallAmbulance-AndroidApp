package com.joker.callambulance.entities;

public class Hospital
{
    private int hospitalImage;
    private String hospitalName;
    private String hospitalType;
    private String hospitalFor;
    private  String hospitalPhone;


    public Hospital(int hospitalImage, String hospitalName, String hospitalType, String hospitalFor, String hospitalPhone)
    {
        this.hospitalName = hospitalName;
        this.hospitalType = hospitalType;
        this.hospitalFor = hospitalFor;
        this.hospitalPhone = hospitalPhone;
        this.hospitalImage = hospitalImage;
    }


    public String getHospitalName()
    {
        return hospitalName;
    }

    public String getHospitalType()
    {
        return hospitalType;
    }

    public String getHospitalFor()
    {
        return hospitalFor;
    }

    public String getHospitalPhone()
    {
        return hospitalPhone;
    }

    public int getHospitalImage() {
        return hospitalImage;
    }
}
