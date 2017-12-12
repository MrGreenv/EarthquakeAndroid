package com.green.test1;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler
{
    public String getData(String requrl)
    {
        String response=null;
        try
        {
            URL url=new URL(requrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in= new BufferedInputStream(conn.getInputStream());
            response=convertString(in);
        }catch(Exception e)
        {
            Log.d("GetData",e.toString());
        }
        return response;
    }
    private String convertString(InputStream in)
    {
        StringBuilder sb=new StringBuilder();
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        String line;
        try
        {
            while((line=reader.readLine())!=null)
            {
                sb.append(line).append('\n');
            }
        }catch(Exception e)
        {
            Log.d("convertString",e.toString());
        }finally{
            try{
                in.close();
            }catch(Exception e)
            {
                Log.d("",e.toString());
            }
        }
        return sb.toString();
    }
}
