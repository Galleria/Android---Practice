package com.example.galleria.helloworld;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SocialCreateEventTabFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SocialCreateEventTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SocialCreateEventTabFragment extends Fragment implements ObservableScrollViewCallbacks {

    View rootView;
    ActionBar ab;

    private OnFragmentInteractionListener mListener;

    public SocialCreateEventTabFragment() {
        // Required empty public constructor
    }

    public static SocialCreateEventTabFragment newInstance() {
        SocialCreateEventTabFragment fragment = new SocialCreateEventTabFragment();
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
        rootView = inflater.inflate(R.layout.social_create_event_tab, container, false);

        ArrayList<String> listItems=new ArrayList<String>();
        for(int i=0; i<20 ; i++ ){
            listItems.add("Hello "+i);
            System.out.println( "Hello "+i );
        }


        ListView li = (ListView) rootView.findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(rootView.getContext(),
                R.layout.item_todo,
                listItems);

        li.setAdapter( adapter );

        return rootView;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //ab = ((ActionBarActivity)getActivity()).getSupportActionBar();
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
/*
        //System.out.println( ab );
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
        */
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
