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
import com.bitcode.hardik.profilemanager.DbClasses.LocationData;
import com.bitcode.hardik.profilemanager.ModelClasses.Location;
import com.bitcode.hardik.profilemanager.R;

import java.util.ArrayList;

/**
 * Created by hardik on 5/12/17.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolderLocation> {

    private Context mContext;
    private ArrayList<LocationData> mArrLocationList;


    public AdapterRecyclerView(Context context, ArrayList<LocationData> ArrLocationList) {
        mContext = context;
        Dbutil dbutil = Dbutil.getInstance(mContext);
        mArrLocationList =  dbutil.getAllLocationList();
        dbutil.close();

    }

    @Override
    public ViewHolderLocation onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.lay_adapter_location,null);
        ViewHolderLocation viewHolderLocation=new ViewHolderLocation(view);
        return viewHolderLocation;
    }




    @Override
    public void onBindViewHolder(final ViewHolderLocation holder, int position) {

        final LocationData location=mArrLocationList.get(position);

        holder.mLocationName.setText(location.getLocationName());
//        holder.mLocationProfileName.setText(location.getPrifileName());

        holder.mTxtOptionSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu=new PopupMenu(mContext,holder.mTxtOptionSelected);
                popupMenu.inflate(R.menu.menu_edit_location);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {

                            case R.id.editLocation:

                                Toast.makeText(mContext,"Edit Clicked",Toast.LENGTH_LONG).show();

                            case R.id.deleteLocation:

                                Toast.makeText(mContext,"Edit Clicked",Toast.LENGTH_LONG).show();

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
        return mArrLocationList.size();
    }



    public class ViewHolderLocation extends RecyclerView.ViewHolder {

        TextView mLocationName,mLocationProfileName,mTxtOptionSelected;

        public ViewHolderLocation(View itemView) {
            super(itemView);

            mLocationName= (TextView) itemView.findViewById(R.id.txtLocName);
           // mLocationProfileName= (TextView) itemView.findViewById(R.id.txtProfName);
            mTxtOptionSelected= (TextView) itemView.findViewById(R.id.txtViewLocationOptions);

        }
    }
}
