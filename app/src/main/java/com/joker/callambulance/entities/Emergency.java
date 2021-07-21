package com.joker.callambulance.entities;

public class Emergency
{
    private int img;
    private  String EmergencyName;
    private String emergencyType;

    public Emergency(int img, String emergencyName, String emergencyType)
    {
        this.img = img;
        EmergencyName = emergencyName;
        this.emergencyType = emergencyType;
    }

    public int getImg() {
        return img;
    }

    public String getEmergencyName()
    {
        return EmergencyName;
    }

    public String getEmergencyType()
    {
        return emergencyType;
    }
}
