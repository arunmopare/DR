package com.example.dr;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Events_list extends ArrayAdapter<MyRegistration> {
    //int i =0;
    private Activity context;
    private List<MyRegistration> registrationList;
    public Events_list(Activity context, List<MyRegistration> registrationList) {
        super(context, R.layout.list_events_layout, registrationList);
        this.context = context;
        this.registrationList = registrationList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewIteam = inflater.inflate(R.layout.list_events_layout, null, true);

        TextView textViewname = (TextView) listViewIteam.findViewById(R.id.textView_lis_name);
        TextView textViewmob = (TextView) listViewIteam.findViewById(R.id.textView_lis_mobileno);
        TextView textViewevent = (TextView) listViewIteam.findViewById(R.id.textView_lis_eventname);
        //TextView textViewcount =(TextView) listViewIteam.findViewById(R.id.textView17);

        MyRegistration registration = registrationList.get(position);
        textViewname.setText(registration.getNames());
        textViewmob.setText(registration.getMobile_Number());
        textViewevent.setText(registration.getEvent_name());
       // i=i+1;
       // String j=Integer.toString(i);
       // textViewcount.setText(j);


        return listViewIteam;


    }
}
