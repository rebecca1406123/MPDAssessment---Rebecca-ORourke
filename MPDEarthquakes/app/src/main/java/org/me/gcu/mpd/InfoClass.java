package org.me.gcu.mpd;

import android.util.Log;

import java.util.ArrayList;

public class InfoClass {
    ArrayList<String> item = new ArrayList<String>();
    String title = new ArrayList<String>().toArray(new String[0]);
    String[] description = new ArrayList<String>().toArray(new String[0]);
    String[] link = new ArrayList<String>();
    String[] pubDate = new ArrayList<String>();
    String[] category = new ArrayList<String>();
    String[] lat = new ArrayList<String>();
    String[] lon = new ArrayList<String>();

    void infoclass()
    {
        for(int i = 0; i < item.length; i++) {
            title.get(i);
            description.get(i);
            link.get(i);
            pubDate.get(i);
            category.get(i);
            lat.get(i);
            lon.get(i);
        }
    }


    public InfoClass(String[] atitle,String[] adescription,String[] alink, String[] apubDate, String[] acategory, String[] alat, String[] alon)
    {
        title = atitle;
        description = adescription;
        link = alink;
        pubDate = apubDate;
        category = acategory;
        lat = alat;
        lon = alon;
    }

    public String[] getTitle()
    {


        return title;

    }

    public void setTitle(String atitle)
    {
        title = atitle;
        Log.d("MyTag", "Item Title Stored: " + title);
    }

    public String[] getDescription()
    {
        return description;
    }

    public void setDescription(String adescription) {
        description = adescription;
        Log.d("MyTag", "Item description Stored: " + description);
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String alink)
    {
        link = alink;
        Log.d("MyTag", "Item Link Stored: " + link);
    }

    public String getPubDate()
    {
        return pubDate;
    }

    public void setPubDate(String apubdate) {
        pubDate = apubdate;
        Log.d("MyTag", "Item pubDate Stored: " + pubDate);
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String acategory)
    {
        category = acategory;
        Log.d("MyTag", "Item category Stored: " + category);
    }

    public String getLat()
    {
        return lat;
    }

    public void setLat(String alat)
    {
        lat = alat;
        Log.d("MyTag", "Item Lat Stored: " + lat);
    }

    public String getLon() { return lon; }

    public void setLon(String alon)
    {
        lon = alon;
        Log.d("MyTag", "Item Lon Stored: " + lon);
    }

    public String toString()
    {
        // String temp;

        // temp = title + " " + description + " " + link + " " + pubDate + " " + category + " " + lat + " " + lon + " ";

        //return temp;
        return null;
    }

}
