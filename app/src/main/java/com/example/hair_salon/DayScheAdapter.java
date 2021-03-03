package com.example.hair_salon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DayScheAdapter extends ArrayAdapter<DaySchedule> {

    public DayScheAdapter(@NonNull Context context, int resource, @NonNull List<DaySchedule> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_schedule,parent,false);
        }
        DaySchedule dSche = getItem(position);
        TextView textView1 = convertView.findViewById(R.id.li_LBL_startHour);
        TextView textView2 = convertView.findViewById(R.id.li_LBL_finishHour);
        TextView textView3 = convertView.findViewById(R.id.li_LBL_name);
        Button btn = convertView.findViewById(R.id.li_BTN_set);
        if (dSche.getAvailable() == true){
            btn.setText("Open");
            btn.setBackgroundResource(R.color.color4);
        }
        else{
            btn.setText("Close");
            btn.setBackgroundResource(R.color.color5);
        }
        textView1.setText(dSche.getHourStart());
        textView2.setText(dSche.getHourFinish());
        textView3.setText(dSche.getName());

        return  convertView;
    }
}
