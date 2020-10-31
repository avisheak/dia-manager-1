package com.assignment.diamanager.modules.add_doctor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.assignment.diamanager.R;
import com.assignment.diamanager.entity.Doctor;

import java.util.ArrayList;


public class DoctorFragment extends Fragment{

    int iPageCounter;
    ArrayList<Doctor> mList;
    DoctorAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public static Context mContext;
    public static AppCompatActivity mParentActivity;

    public DoctorFragment() {
        // Required empty public constructor
        int i=0;

    }

    public static DoctorFragment newInstance(Context context)
    {
        DoctorFragment fragment = new DoctorFragment();

        mContext = context;
        mParentActivity = (AppCompatActivity) context;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent = inflater.inflate(R.layout.fragment_doctor, container, false);
        Button bAddDoctor = (Button) parent.findViewById(R.id.bAddDoctor);

        bAddDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(getActivity(), AddDoctorActivity.class);
                getActivity().startActivity(in);
            }
        });
        //-------------------------------------

        DoctorManager  manager= new DoctorManager(getActivity());
        //ArrayList<Category> clist;




        //Begin -- Load Data in List--------------------------------
        mRecyclerView = (RecyclerView) parent.findViewById(R.id.itemlist);
        iPageCounter=1;
        mList = manager.getAll(iPageCounter);
        mAdapter = new DoctorAdapter(getActivity(), mList);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //End -- Load Data in List--------------------------------
        //------------------------------------------

        return  parent;
    }

    @Override
    public void onResume() {
        super.onResume();
        DoctorManager  manager= new DoctorManager(getActivity());
        //ArrayList<Category> clist;




        //Begin -- Load Data in List--------------------------------

        iPageCounter=1;
        mList = manager.getAll(iPageCounter);
        mAdapter = new DoctorAdapter(getActivity(), mList);


   //     RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
     //   mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
