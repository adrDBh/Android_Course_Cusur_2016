package adrdbh.android.curso.ejercicio1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adrdbh.android.curso.ejercicio1.R;

/*
 set ups the new threadfragment
* */


public class ThreadFragment extends Fragment {

    // instances ThreadFragment and initializes it
    public static ThreadFragment newInstance() {
        ThreadFragment tf = new ThreadFragment();
        return tf;
    }

    @Nullable
    @Override
    // TBC
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.thread_fragment_layout, container, false);
        return v;
    }
}
