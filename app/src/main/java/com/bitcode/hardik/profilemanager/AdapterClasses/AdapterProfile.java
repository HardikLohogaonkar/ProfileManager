package com.bitcode.hardik.profilemanager.AdapterClasses;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bitcode.hardik.profilemanager.DbClasses.Dbutil;
import com.bitcode.hardik.profilemanager.DbClasses.ProfileData;
import com.bitcode.hardik.profilemanager.ModelClasses.Profile;
import com.bitcode.hardik.profilemanager.R;

import java.util.ArrayList;

/**
 * Created by hardik on 6/12/17.
 */

public class AdapterProfile extends RecyclerView.Adapter<AdapterProfile.ViewHolderProfile> {

    private Context mContext;
    private ArrayList<ProfileData> mProfileList;

    public AdapterProfile(Context Context) {
        this.mContext = Context;


        Dbutil dbutil = Dbutil.getInstance(mContext);
        mProfileList =  dbutil.getAllProfileList();
        dbutil.close();


    }

    @Override
    public ViewHolderProfile onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.lay_adapter_profile,null);
        ViewHolderProfile viewHolderProfile=new ViewHolderProfile(view);
        return viewHolderProfile;
    }

    @Override
    public void onBindViewHolder(final ViewHolderProfile holder, int position) {

        ProfileData profileData = mProfileList.get(position);
        holder.mTxtCustomProfile.setText(profileData.getProfileName());
        holder.mTxtOnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu=new PopupMenu(mContext,holder.mTxtOnOptions);
                popupMenu.inflate(R.menu.menu_edit_profile);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.editLocation:

                                Toast.makeText(mContext,"Edit Clicked",Toast.LENGTH_LONG).show();

                            case R.id.deleteLocation:

                                Toast.makeText(mContext,"Delete Clicked",Toast.LENGTH_LONG).show();

                        }

                        return false;
                    }
                });


                popupMenu.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mProfileList.size();
    }

    public class ViewHolderProfile extends RecyclerView.ViewHolder {

        private TextView mTxtCustomProfile;
        private TextView mTxtOnOptions;

        public ViewHolderProfile(View itemView) {
            super(itemView);

            mTxtCustomProfile= (TextView) itemView.findViewById(R.id.txtCustomProfile);
            mTxtOnOptions= (TextView) itemView.findViewById(R.id.txtViewOptions);
        }
    }

}
