package adrdbh.android.curso.ejercicio1.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import adrdbh.android.curso.ejercicio1.R;

/*
 set ups the new threadfragment
* */



public class ThreadFragment extends Fragment {

    // Declarates buttons and progressbar
    private ProgressBar mProgressBar;
    private ProgressDialog mProgressDialog;
    private Button mButtonWithoutThread;
    private Button mButtonWithThread;


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
        // initializes progress bar
        mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        // initializes button "withThread" id in thread_fragment_layout.xml
        mButtonWithoutThread = (Button) v.findViewById(R.id.button);
        // initializes button "WithoutThread" id in thread_fragment_layout.xml
        mButtonWithThread = (Button) v.findViewById(R.id.button2);


        // Listener for non-threaded button
        mButtonWithoutThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withoutThread();
            }
        });
        // Listener for threaded button
        mButtonWithThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //withThread();
            }
        });

        return v;
    }

    private void withoutThread() {
        // Max value of progress bar (%)
        mProgressBar.setMax(100);
        // Progress start value (%)
        mProgressBar.setProgress(0);

        // Iterates through 0-100% and sleeps 1000millis each time
        for (int i = 0; i <= 10; i++) {
            SleepProcess();
            // Increments previous value by 10
            mProgressBar.incrementProgressBy(10);
        }
    }

    private void SleepProcess() {
        try {
            // Pauses for 1000millis
            Thread.sleep(1000);
        } catch (Exception e) {
            // Trace error log
            Log.e("Error: ", e.getMessage());
        }
    }
}
