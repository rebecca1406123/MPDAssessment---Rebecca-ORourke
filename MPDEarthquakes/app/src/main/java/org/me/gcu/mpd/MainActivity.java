package org.me.gcu.mpd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;
import androidx.constraintlayout.solver.ArrayLinkedVariables;

import android.app.ActionBar;
import android.content.ClipData;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
    private TextView rawDataDisplay;
    private Button startButton;
    private String result = "";
    private String url1="";
    private String urlSource="http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";
    private ListView listView;
    private Object pubDate;
    private Object lat;
    private Object lon;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MyTag","in onCreate");
        // Set up the raw links to the graphical components
        rawDataDisplay = (TextView)findViewById(R.id.rawDataDisplay);
        startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
        Log.e("MyTag","after startButton");
        // More Code goes here


        listView = (ListView) findViewById(R.id.list);


        //
        //
        //
        //
        //  adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, values)
        // listView.setAdapter(adapter);

    }

    public void onClick(View aview)
    {
        Log.e("MyTag","in onClick");
        startProgress();
        Log.e("MyTag","after startProgress");
    }

    public void startProgress()
    {
        // Run network access on a separate thread;
        new Thread(new Task(urlSource)).start();
    } //

    // Need separate thread to access the internet resource over network
    // Other neater solutions should be adopted in later iterations.
    private class Task implements Runnable
    {
        private String url;

        public Task(String aurl)
        {
            url = aurl;
        }
        @Override
        public void run()
        {

            URL aurl;
            URLConnection yc;
            BufferedReader in = null;
            String inputLine = "";


            Log.e("MyTag","in run");

            try
            {
                Log.e("MyTag","in try");
                aurl = new URL(url);
                yc = aurl.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                Log.e("MyTag","after ready");
                //
                // Now read the data. Make sure that there are no specific hedrs
                // in the data file that you need to ignore.
                // The useful data that you need is in each of the item entries
                //
                while ((inputLine = in.readLine()) != null)
                {
                    result = result + inputLine;
                    Log.e("MyTag",inputLine);

                }
                in.close();
            }
            catch (IOException ae)
            {
                Log.e("MyTag", "ioexception in run");
            }

            //
            // Now that you have the xml data you can parse it
            //

            // Now update the TextView to display raw XML data
            // Probably not the best way to update TextView
            // but we are just getting started !




            MainActivity.this.runOnUiThread(new Runnable()
            {
                public void run() {
                    Log.d("UI thread", "I am the UI thread");
                    rawDataDisplay.setText(result);
                    parseData(result);
                }


                public void parseData(String result)
                {

                    try {
                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                        factory.setNamespaceAware(true);
                        XmlPullParser xpp = factory.newPullParser();
                        xpp.setInput( new StringReader(result) );


                        int eventType = xpp.getEventType();


                        while (eventType != XmlPullParser.END_DOCUMENT) {
                            // Found a start tag
                            ArrayLinkedVariables link;
                            ArrayLinkedVariables description = null;

                            ArrayLinkedVariables pubDate;
                            ArrayLinkedVariables category;
                            ArrayLinkedVariables lat;
                            ArrayLinkedVariables lon;

                            if (eventType == XmlPullParser.START_TAG) {
                                if (xpp.getName().equalsIgnoreCase("item")) {
                                    // Check which Tag we have
                                    Log.e("MyTag", "info Start Tag found");
                                    InfoClass infor = new InfoClass();
                                } else if (xpp.getName().equalsIgnoreCase("title")) {
                                    // Now just get the associated text
                                    String temp = xpp.nextText();
                                    // Do something with text
                                    Log.e("MyTag", "title " + temp);
       
                                    info.setTitle(temp);
                                    //title.add(temp);

                                } else
                                    // Check which Tag we have
                                    if (xpp.getName().equalsIgnoreCase("description")) {
                                        // Now just get the associated text
                                        String temp = xpp.nextText();
                                        // Do something with text
                                        Log.e("MyTag", "description " + temp);
                                        description.add(temp);
                                    } else
                                        // Check which Tag we have
                                        if (xpp.getName().equalsIgnoreCase("link")) {
                                            // Now just get the associated text
                                            String temp = xpp.nextText();
                                            // Do something with text
                                            Log.e("MyTag", "link " + temp);
                                            link.add(temp);
                                        } else
                                            // Check which Tag we have
                                            if (xpp.getName().equalsIgnoreCase("pubDate")) {
                                                // Now just get the associated text
                                                String temp = xpp.nextText();
                                                // Do something with text
                                                Log.e("MyTag", "pubDate " + temp);
                                                pubDate.add(temp);
                                            } else
                                                // Check which Tag we have
                                                if (xpp.getName().equalsIgnoreCase("category")) {
                                                    // Now just get the associated text
                                                    String temp = xpp.nextText();
                                                    // Do something with text
                                                    Log.e("MyTag", "category " + temp);
                                                    category.add(temp);
                                                } else
                                                    // Check which Tag we have
                                                    if (xpp.getName().equalsIgnoreCase("lat")) {
                                                        // Now just get the associated text
                                                        String temp = xpp.nextText();
                                                        // Do something with text
                                                        Log.e("MyTag", "lat " + temp);
                                                        lat.add(temp);
                                                    }
                                                    else
                                                        // Check which Tag we have
                                                        if (xpp.getName().equalsIgnoreCase("long")) {
                                                            // Now just get the associated text
                                                            String temp = xpp.nextText();
                                                            // Do something with text
                                                            Log.e("MyTag", "long " + temp);
                                                            lon.add(temp);
                                                        }

                            }
                            else if (eventType == XmlPullParser.END_TAG) {
                                if (xpp.getName().equalsIgnoreCase("item"))
                                {
                                    ArraySet item;
                                    Log.e("MyTag", "item is " + item.toString());
                                    item.add(item.toString());
                                }


                                String outputting = title + " " + description + " " + link + " " + pubDate + " " + category + " " + lat + " " + lon + " ";
                                //  run(outputting);




                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activitylist, );
                            listView.setAdapter(adapter);

                            // Get the next event
                            eventType = xpp.next();

                        } // End of while
                    } catch (XmlPullParserException ae1) {
                        Log.e("MyTag", "Parsing error" + ae1.toString());
                    } catch (IOException ae1) {
                        Log.e("MyTag", "IO error during parsing");
                    }

                    Log.e("MyTag", "End document");



                }

            });
        }


    }


}