package com.bitcode.hardik.profilemanager.Fragments;

import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.bitcode.hardik.profilemanager.DbClasses.Dbutil;
import com.bitcode.hardik.profilemanager.DbClasses.LocationData;
import com.bitcode.hardik.profilemanager.R;

import java.util.Random;

public class FragmentCustomProfile extends Fragment {

    private TextView mTxtMedia,mTxtAlarm,mTxtNotification,mTxtRing,mTxtMessage,mTxtPhoneRingtone,mTxtNotifcationRingtone,
            mTxtMessageRingtone,mTxtWallpaper,mTxtVibrate,mTxtWifi,mTxtBluetooth,mTxtMobileData,mTxtProfileName;
    private EditText mEdtProfileName;
    private Switch mSwitchVibrate,mSwitchWifi,mSwitchBluetooth,mSwitchMobileData;
    private SeekBar mSeekBarMedia,mSeekBarAlarm,mSeekBarNotification,mSeekBarRing,mSeekBarMessage;


    int MediaVolume,AlarmVolume,NotificationVolume,RingVolume;
    int Vibrate;
    int Wifi;
    int Bluetooth;
    String RingTone,NotificationRingtone,AlarmRingtone,WallPaper;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_custom_profile,container,false);

        mTxtMedia= (TextView) view.findViewById(R.id.txtViewMediaVolume);
        mTxtAlarm= (TextView) view.findViewById(R.id.txtViewAlarmVolume);
        mTxtNotification= (TextView) view.findViewById(R.id.txtNotificationVolume);
        mTxtRing= (TextView) view.findViewById(R.id.txtViewRingVolume);
        mTxtMessage= (TextView) view.findViewById(R.id.txtViewMessageVolume);
        mTxtPhoneRingtone= (TextView) view.findViewById(R.id.txtViewPhoneRingtone);
        mTxtNotifcationRingtone= (TextView) view.findViewById(R.id.txtViewNotificationRingtone);
        mTxtMessageRingtone= (TextView) view.findViewById(R.id.txtViewMessageRingtone);
        mTxtWallpaper= (TextView) view.findViewById(R.id.txtViewWallpaper);
        mTxtVibrate= (TextView) view.findViewById(R.id.txtVibrate);
        mTxtWifi= (TextView) view.findViewById(R.id.txtViewWifi);
        mTxtBluetooth= (TextView) view.findViewById(R.id.txtViewBluetooth);
//        mTxtMobileData= (TextView) view.findViewById(R.id.txtViewMobileData);
        mTxtProfileName= (TextView) view.findViewById(R.id.txtCustomProfileName);
        mSwitchVibrate= (Switch) view.findViewById(R.id.switchVibrateForCalls);
        mSwitchWifi= (Switch) view.findViewById(R.id.switchWifi);
        mSwitchBluetooth= (Switch) view.findViewById(R.id.switchBluetooth);
//        mSwitchMobileData= (Switch) view.findViewById(R.id.switchMobileData);

        mEdtProfileName= (EditText) view.findViewById(R.id.edtTxtProfileName);

        mSeekBarMedia= (SeekBar) view.findViewById(R.id.seekBarMediaVolume);
        mSeekBarAlarm= (SeekBar) view.findViewById(R.id.seekBarAlarmVolume);
        mSeekBarNotification= (SeekBar) view.findViewById(R.id.seeBarNotification);
        mSeekBarRing= (SeekBar) view.findViewById(R.id.seeBarRingVolume);
        mSeekBarMessage= (SeekBar) view.findViewById(R.id.seeBarMessageVolume);




        mSeekBarMedia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                MediaVolume = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekBarAlarm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                AlarmVolume = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekBarNotification.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                NotificationVolume = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekBarRing.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                RingVolume = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mSwitchVibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b==true)
                {
                    Vibrate = 1;
                }
                else
                {
                    Vibrate = 0;
                }
            }
        });


        mSwitchWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b==true)
                {
                    Wifi = 1;
                }
                else
                {
                    Wifi = 0;
                }
            }
        });


        mSwitchBluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b==true)
                {
                    Bluetooth = 1;
                }
                else
                {
                    Bluetooth = 0;
                }
            }
        });






        mTxtPhoneRingtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                startActivityForResult(intent,66);

            }
        });

        mTxtNotifcationRingtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,RingtoneManager.TYPE_NOTIFICATION);

                startActivityForResult(intent,55);

            }
        });

        mTxtMessageRingtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,RingtoneManager.TYPE_ALARM);

                startActivityForResult(intent,65);


            }
        });

        mTxtWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Re"),1);

            }
        });



        setHasOptionsMenu(true);


        Bundle bundle=getArguments();
        setHasOptionsMenu(true);


        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_custom_profile,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.saveProfile:

                Dbutil dbutil1= Dbutil.getInstance(getActivity());

                Random random = new Random();
                int i = random.nextInt(900-10)+10;

                dbutil1.AddProfile(i,mEdtProfileName.getText().toString(),Wifi,Bluetooth,MediaVolume,NotificationVolume,AlarmVolume,RingTone,WallPaper,NotificationRingtone,AlarmRingtone,Vibrate,RingVolume);

                dbutil1.showProfileData();
                dbutil1.close();



                FragmentProfile fragmentProfile=new FragmentProfile();

//                Bundle bundle=new Bundle();
//                bundle.putSerializable("profile",mEdtProfileName.getText().toString());

                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,fragmentProfile).commit();
        }

        return true;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (data != null)

        {
            if (requestCode == 66) {


                Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

                RingTone = uri.getPath();




            }


            if (requestCode == 55)

            {

                Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

                NotificationRingtone = uri.getPath();


            }

            if (requestCode==65)
            {

                Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

                AlarmRingtone = uri.getPath();
            }

            if(requestCode == 1)
            {
                Uri uri = data.getData();
                WallPaper = uri.getPath();




            }


        }


    }

}
