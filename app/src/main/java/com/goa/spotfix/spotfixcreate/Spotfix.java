package com.goa.spotfix.spotfixcreate;

import java.util.Date;

/**
 * Created by N B Panth on 10-06-2018.
 */

public class Spotfix {
    private String username;
    private String uid;
    private String ImageUrl;
    //private String Description;
    private int no_of_vol;
    private Date date;
    boolean isComplete;

    public Spotfix()
    {

    }
public Spotfix(String username,String uid, String ImageUrl, int no_of_vol, Date date,boolean isComplete)
{
    this.username=username;
    this.ImageUrl = ImageUrl;
    this.no_of_vol = no_of_vol;
    this.date = date;
    this.isComplete = isComplete;
}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getUid() {return uid;}

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImageUrl() {return ImageUrl;}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNo_of_vol() {
        return no_of_vol;
    }

    public void setNo_of_vol(int no_of_vol) {
        this.no_of_vol = no_of_vol;
    }

    public boolean getComplete(){
        return isComplete;
    }
    public void setComplete(boolean isComplete)
    {
        this.isComplete = isComplete;
    }
}
