package com.green.test1;

public class DataModel
{
    private String mag;
    private String location;
    private String date;

    public DataModel(){}
    public DataModel(String mag,String location,String date)
    {
        this.mag=mag;
        this.location=location;
        this.date=date;
    }
    public String getMag()
    {
        return mag;
    }
    public String getLocation()
    {
        return location;
    }
    public String getDate()
    {
        return date;
    }
}
