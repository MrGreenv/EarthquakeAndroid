package com.green.test1;
//how authentication in json android
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10";
    ArrayList<DataModel> data;
    ListView datalist;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data=new ArrayList<>();
        datalist=(ListView)findViewById(R.id.ll);
        context=this;
        new GetData().execute();
    }

    private class GetData extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Toast.makeText(context,"Downloading",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            Adapt adapter=new Adapt(context,data);
            datalist.setAdapter(adapter);
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            HttpHandler httpHandler=new HttpHandler();
            String jsonStr=httpHandler.getData(url);
            if(jsonStr!=null)
            {
                try
                {
                    JSONObject baseObject=new JSONObject(jsonStr);
                    JSONArray eArray=baseObject.getJSONArray("features");

                    for(int i=0;i<eArray.length();i++)
                    {
                        JSONObject cEQ=eArray.getJSONObject(i);
                        JSONObject prop=cEQ.getJSONObject("properties");

                        data.add(new DataModel(prop.getString("mag"),prop.getString("place"),prop.getString("time")));
                    }

                } catch (Exception e) {

                }

            }
            return null;
        }
    }
}
