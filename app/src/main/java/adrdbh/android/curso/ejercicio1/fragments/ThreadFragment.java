package adrdbh.android.curso.ejercicio1.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

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
                // WEEEEEEE, we getting in that withoutThread method!!, see you there!
                withoutThread();
            }
        });
        // Listener for threaded button
        mButtonWithThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withThreadOption2();

            }
        });

        return v;
    }

    private void withThreadOption2() {
        AsynTaskProgressBar ATP = new AsynTaskProgressBar();
        ATP.execute();
    }

    // Creating a new asynchronous class from the android-made, way...
    private class AsynTaskProgressBar extends AsyncTask<Void, Integer, Boolean> {

        @Override
        // Adding a background thread
        protected Boolean doInBackground(Void... params) {
            // Iteration though 0-100%
            for (int i = 0; i <= 10; i++) {
                SleepProcess();
                // Icrement by multiples of 10
                publishProgress(i * 10);
                // If user leaves, or switches from fragment/activity, exit.
                if (isCancelled()) {
                    break;
                }
            }
            return true;
        }

        @Override

        protected void onProgressUpdate(Integer... values) {
            int Progress = values[0].intValue();
            mProgressBar.setProgress(Progress);
        }

        @Override
        protected void onPreExecute() {
            mProgressBar.setMax(100);
            mProgressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                Toast.makeText(getActivity(), "Hilo finalizado", Toast.LENGTH_SHORT).show();
            }
        }

        protected void onCancelled() {
            Toast.makeText(getActivity(), "Hilo finalizado :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void withThreadOption1() {
        // Creates a new thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                // post action for threaded progress bar needs a runnable
                mProgressBar.post(new Runnable() {
                    @Override
                    public void run() {
                        // Set the progress again at 0
                        mProgressBar.setProgress(0);
                    }
                });
                // Iterate through 0-100% in the progress bar
                for (int i = 0; i <= 10; i++) {
                    // Sleep the program 1000 millis
                    SleepProcess();
                    // post method again, so new runnable needed
                    mProgressBar.post(new Runnable() {
                        @Override
                        public void run() {
                            //Increment the progress bar for each iteration
                            mProgressBar.incrementProgressBy(10);
                        }
                    });
                }
                // runs activity on the current window
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Sheesh, finally got out of Runnable-ception D:
                        Toast.makeText(getActivity(), "Finished up wasting time :}", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            // Finally we can start it...
        }).start();
    }

    // Yo, you just ended up here!!
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
