package com.bitcode.hardik.profilemanager.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bitcode.hardik.profilemanager.R;

import java.util.ArrayList;

public class FragmentAddEvent extends Fragment {

    private TextView mTxtDate,mTxtFromTime,mTxtToTime,mTxtProfile,mTxtViewDate,mTxtViewFromTime,mTxtViewToTime;
    private Spinner mSpinner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.fragment_add_event,null);


        mTxtDate= (TextView) view.findViewById(R.id.txtDate);
        mTxtFromTime= (TextView) view.findViewById(R.id.txtFromTime);
        mTxtToTime= (TextView) view.findViewById(R.id.txtToTime);
        mTxtProfile= (TextView) view.findViewById(R.id.txtprofileEvent);
        mTxtViewDate= (TextView) view.findViewById(R.id.txtViewDate);

        mTxtViewFromTime= (TextView) view.findViewById(R.id.txtViewFromTime);
        mTxtViewToTime= (TextView) view.findViewById(R.id.txtViewToTime);

        mSpinner= (Spinner) view.findViewById(R.id.spinnerEvent);


        final ArrayList<String> profiles =new ArrayList<>();
        profiles.add("Normal");
        profiles.add("Silent");
        profiles.add("Vibrate");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, profiles);
        mSpinner.setAdapter(adapter);

        setHasOptionsMenu(true);

        mTxtViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        mTxtViewDate.setText(day+"/"+(month+1)+"/"+year);
                    }
                },2017,10,05);
                datePickerDialog.show();
            }
        });

        mTxtViewFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {

                        String timeSet = "";
                        if (hour > 12) {
                            hour -= 12;
                            timeSet = "PM";
                        } else if (hour == 0) {
                            hour += 12;
                            timeSet = "AM";
                        } else if (hour == 12)
                            timeSet = "PM";
                        else
                            timeSet = "AM";

                        String minutes="";
                        if (minute < 10)
                            minutes = "0" + minute;
                        else
                            minutes = String.valueOf(minute);

                        mTxtViewFromTime.setText(hour + ":" + minutes + " " + timeSet);

                    }
                },10,00,false);
                timePickerDialog.show();

            }
        });

        mTxtViewToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {

                        String timeSet = "";
                        if (hour > 12) {
                            hour -= 12;
                            timeSet = "PM";
                        } else if (hour == 0) {
                            hour += 12;
                            timeSet = "AM";
                        } else if (hour == 12)
                            timeSet = "PM";
                        else
                            timeSet = "AM";

                        String minutes="";
                        if (minute < 10)
                            minutes = "0" + minute;
                        else
                            minutes = String.valueOf(minute);

                        mTxtViewToTime.setText(hour + ":" + minutes + " " + timeSet);

                    }
                },10,00,false);
                timePickerDialog.show();
            }
        });



        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_event,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.saveEvent:





        }
        return true;
    }
}
