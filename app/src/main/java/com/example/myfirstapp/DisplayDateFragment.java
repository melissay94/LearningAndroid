package com.example.myfirstapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayDateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayDateFragment extends Fragment {

    public DisplayDateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment DisplayDateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayDateFragment newInstance() {
        DisplayDateFragment fragment = new DisplayDateFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_display_date, container, false);
        ButterKnife.bind(getActivity(), rootView); // Needs to specify sources of view
        return rootView;
    }
}
