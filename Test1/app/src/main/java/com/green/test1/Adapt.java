package com.green.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapt extends ArrayAdapter<DataModel>
{
    public Adapt(Context context, List<DataModel> dataModel)
    {
        super(context,0,dataModel);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent)
    {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.listlayout,parent,false);
        }
        DataModel current=getItem(position);
        TextView mag=(TextView)listItemView.findViewById(R.id.mag);
        mag.setText(current.getMag());
        TextView location=(TextView)listItemView.findViewById(R.id.location);
        location.setText(current.getLocation());
        TextView date=(TextView)listItemView.findViewById(R.id.date);
        date.setText(current.getDate());
        return listItemView;
    }
}
