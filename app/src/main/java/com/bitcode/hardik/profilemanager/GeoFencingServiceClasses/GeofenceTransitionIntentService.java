package com.bitcode.hardik.profilemanager.GeoFencingServiceClasses;

import android.Manifest;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import android.util.Log;

import com.bitcode.hardik.profilemanager.DbClasses.Dbutil;
import com.bitcode.hardik.profilemanager.DbClasses.ProfileData;
import com.bitcode.hardik.profilemanager.MainActivity;
import com.bitcode.hardik.profilemanager.Map.MapsActivity;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hardik on 6/12/17.
 */

public class GeofenceTransitionIntentService extends IntentService {

    protected static final String TAG = "GeofenceTransitionsIS";

    Dbutil dbutil ;
    ProfileData profileData;


    public GeofenceTransitionIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e(TAG, "service 0");
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            String errorMessage = GeofenceErrorMessage.getErrorString(this,
                    geofencingEvent.getErrorCode());
            Log.e(TAG, errorMessage);
            return;
        }

        Log.e(TAG, "service 1");

        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        Log.e(TAG, "service 2");


        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER || geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            // Get the geofences that were triggered. A single event can trigger multiple geofences.
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            // Get the transition details as a String.
            String geofenceTransitionDetails = getGeofenceTransitionDetails(this, geofenceTransition, triggeringGeofences);

            dbutil = Dbutil.getInstance(getApplicationContext());
            Geofence geo1 = triggeringGeofences.get(0);
            String LocationName = geo1.getRequestId();
            String ProfileName = dbutil.searchLocationData(LocationName);
            profileData = dbutil.searchProfileData("Code");
            dbutil.close();




            if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER)
            {

                setProfile();
            }
            else
            {

                //   setProfle();
            }




            // Send notification and log the transition details.
            sendNotification(geofenceTransitionDetails);
            Log.i(TAG, geofenceTransitionDetails);
        } else {
            // Log the error.
            Log.e(TAG, "getString(R.string.geofence_transition_invalid_type, geofenceTransition)");
        }
    }

    private String getGeofenceTransitionDetails(Context context, int geofenceTransition, List<Geofence> triggeringGeofences) {

        String geofenceTransitionString = getTransitionString(geofenceTransition);

        ArrayList triggeringGeofencesIdsList = new ArrayList();
        for (Geofence geofence : triggeringGeofences) {
            triggeringGeofencesIdsList.add(geofence.getRequestId());
        }
        String triggeringGeofencesIdsString = TextUtils.join(", ", triggeringGeofencesIdsList);

        return geofenceTransitionString + ": " + triggeringGeofencesIdsString;
    }

    private void sendNotification(String notificationDetails) {
        // Create an explicit content Intent that starts the main Activity.
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);

        // Construct a task stack.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Add the main Activity to the task stack as the parent.
        stackBuilder.addParentStack(MapsActivity.class);

        // Push the content Intent onto the stack.
        stackBuilder.addNextIntent(notificationIntent);

        // Get a PendingIntent containing the entire back stack.
        PendingIntent notificationPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get a notification builder that's compatible with platform versions >= 4
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // Define the notification settings.
        builder.setSmallIcon(com.bitcode.hardik.profilemanager.R.mipmap.ic_launcher)
                // In a real app, you may want to use a library like Volley
                // to decode the Bitmap.
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        com.bitcode.hardik.profilemanager.R.mipmap.ic_launcher))
                .setColor(Color.RED)
                .setContentTitle(notificationDetails)
                .setContentText("Geofence_transition")
                .setContentIntent(notificationPendingIntent);

        // Dismiss notification once the user touches it.
        builder.setAutoCancel(true);

        // Get an instance of the Notification manager
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Issue the notification
        mNotificationManager.notify(0, builder.build());
    }

    private String getTransitionString(int transitionType) {
        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                return "Geofence entered";
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                return "Geofence exited";
            default:
                return "unknown_geofence_transition";
        }
    }




    public void setWallpaper(String Wallper)
    {


        String c = Wallper;




        String con = "content://media";
        String  s= c;
        String ur = con+s;


        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // Toast.makeText(MainActivity.this, " "+s, Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse(ur);

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {

            wallpaperManager.setBitmap(bitmap);
            //    Toast.makeText(MainActivity.this, "WallPaper Sucseefully Changed..", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public  void setRingtone(String Ringtone)
    {


        String con = "content://media";
        String  s= Ringtone;
        String ur = con+s;


        Uri uri = Uri.parse(ur);
        settingPermision();
        RingtoneManager.setActualDefaultRingtoneUri(getApplicationContext(),RingtoneManager.TYPE_RINGTONE,uri);

    }

    public void settingPermision()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {

            if (!Settings.System.canWrite(getApplicationContext()))
            {

                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,Uri.parse("package:"+getPackageName()));
                // startActivityForResult(intent,200);

            }
        }


    }


    public void setAlrmtone(String AlrmTone)
    {



        String con = "content://media";
        String  s= AlrmTone;
        String ur = con+s;



        Uri uri = Uri.parse(ur);
        settingPermision();
        RingtoneManager.setActualDefaultRingtoneUri(getApplicationContext(),RingtoneManager.TYPE_ALARM,uri);



    }

    public  void setNotificationTone(String NotificationTone)
    {


        String con = "content://media";
        String  s= NotificationTone;
        String ur = con+s;



        Uri uri = Uri.parse(ur);
        settingPermision();
        RingtoneManager.setActualDefaultRingtoneUri(getApplicationContext(),RingtoneManager.TYPE_NOTIFICATION,uri);


    }

    public void setMediaVolume(int MediaVollume)
    {
        AudioManager audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, MediaVollume, 0);

    }

    public void setAlarmVolume(int AlrmVollume)
    {
        AudioManager audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_ALARM,AlrmVollume,0);

    }

    public void setNotificationVolume(int NotificationVolumr)
    {
        AudioManager audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION,NotificationVolumr,0);

    }

    public void setRingerVolume(int RingerVolume)
    {
        AudioManager audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_RING,RingerVolume,0);

    }

    public  void setWifi(int Wifi)
    {

        if (Wifi==1) {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(true);
        }
        else
        {
            WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(false);

        }
    }


    public void setBluetooth(int Bluetooth)
    {

        if (Bluetooth == 1)
        {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            bluetoothAdapter.enable();

        }

        else{


            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            bluetoothAdapter.disable();


        }



    }




    public  void setProfile(){

        setWallpaper(profileData.getWallpaper());
       // setRingtone(profileData.getRingtone());
        setAlarmVolume(profileData.getAlramVolume());
      //   setAlrmtone(profileData.getAlarmTone());
        setNotificationVolume(profileData.getNotificationVolume());
       // setNotificationTone(profileData.getNotificationTone());
        setMediaVolume(profileData.getMediaVolume());
        setRingerVolume(profileData.getRingtoneVolume());
        setWifi(profileData.getWifi());
        setBluetooth(profileData.getBluetooth());



    }














    @Override
    public void onCreate() {
        super.onCreate();
    }
}
