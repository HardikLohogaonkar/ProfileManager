package com.bitcode.hardik.profilemanager.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitcode.hardik.profilemanager.AdapterClasses.AdapterProfile;
import com.bitcode.hardik.profilemanager.MainActivity;
import com.bitcode.hardik.profilemanager.ModelClasses.Profile;
import com.bitcode.hardik.profilemanager.R;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class FragmentProfile extends Fragment {

    private TextView mTxtDefaultProfile;
    private TextView mTxtNormal,mTxtSilent, mTxtVibrate;
    private FloatingActionButton fab;
    private LinearLayout linearLayout;
    private int counter = 0;
    private TextView textView;
    private RecyclerView mRecyclerView;
    private AdapterProfile mAdapterProfile;
    private ArrayList<Profile>mProfileList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile,null);
        mTxtDefaultProfile= (TextView) view.findViewById(R.id.txtViewDefaultProfile);
        mTxtNormal= (TextView) view.findViewById(R.id.txtNormal);
        mTxtSilent= (TextView) view.findViewById(R.id.txtSilent);
        mTxtVibrate= (TextView) view.findViewById(R.id.txtSilent);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerProfile);
        mProfileList=new ArrayList<>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapterProfile=new AdapterProfile(getActivity());
        mRecyclerView.setAdapter(mAdapterProfile);

        Bundle bundle=new Bundle();
        bundle.getSerializable("profile");


        mTxtDefaultProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity mainActivity= (MainActivity) getContext();
                FragmentDefaultProfile fragmentDefaultProfile=new FragmentDefaultProfile();
                fragmentDefaultProfile.setEnterTransition(new Slide(Gravity.RIGHT));
                mainActivity.fragmentManager.beginTransaction().replace(R.id.content_frame,fragmentDefaultProfile).commit();


            }
        });



        fab= (FloatingActionButton) view.findViewById(R.id.floatingBtn);


                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity mainActivity= (MainActivity) getContext();
                        FragmentCustomProfile fragmentCustomProfile=new FragmentCustomProfile();
                        Bundle bundle=new Bundle();
                        fragmentCustomProfile.setEnterTransition(new Slide(Gravity.RIGHT));
                        fragmentCustomProfile.setArguments(bundle);
                        mainActivity.fragmentManager.beginTransaction().replace(R.id.content_frame,fragmentCustomProfile).commit();
                    }
                });


        registerForContextMenu(mRecyclerView);

        return view;
        }

    public interface OnFragmentInteractionListener {
    }
}


